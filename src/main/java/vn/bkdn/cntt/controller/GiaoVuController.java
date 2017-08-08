package vn.bkdn.cntt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
//import vn.bkdn.cntt.common.GeneticAlgorithmUtils;
import vn.bkdn.cntt.controller.APIEntity.LopMonHoc;
import vn.bkdn.cntt.controller.APIEntity.LopMonHoc_ViPham;
import vn.bkdn.cntt.controller.APIEntity.ViPham;
import vn.bkdn.cntt.entity.*;
import vn.bkdn.cntt.entity.geneticAlgorithm.CaThe;
import vn.bkdn.cntt.entity.geneticAlgorithm.ChosenCondition;
import vn.bkdn.cntt.entity.geneticAlgorithm.GenerationSocketMessage;
import vn.bkdn.cntt.entity.geneticAlgorithm.Setting;

import java.util.*;

/**
 * Created by Tri on 4/4/2017.
 */

@RestController
@RequestMapping(value = "api/giaovu")
public class GiaoVuController {

    private int numberOfInviduals = 20;
    private int parentsPercentage = 40;
    private int crossOverPercentage = 50;
    private int mutatePercentage = 50;

    private int tkbtuan_index;
    private int theHe;

    private List<LopMonHoc_ViPham> lopMonHoc_viPhams = new ArrayList<>();

    @Autowired
    private NamHocService namHocService;

    @Autowired
    private TKB_LichHocTheoTuanService tkb_lichHocTheoTuanService;

    @Autowired
    private DMGiangDuongService giangDuongService;

    @Autowired
    private TKB_ThuService tkb_thuService;

    @Autowired
    private TKB_TietService tkb_tietService;

    @Autowired
    private DMLopMonHocService lopMonHocService;

    @Autowired
    private TKB_Khoa_KhoaHocService khoa_khoaHocService;

    @Autowired
    private TKB_KiHoc_NamHocService kiHoc_namHocService;

    @Autowired
    private DMKhoaService khoaService;

    @Autowired
    private TKB_ThoiGianDangKyService thoiGianDangKyService;

    @Autowired
    private SimpMessagingTemplate template;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public GiaoVuController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/calendar/year-not-end")
    public ResponseEntity<List<TKB_NamHoc>> getNamHocsNotEnd() {
        List<TKB_NamHoc> tkb_namHocsNotEnd = namHocService.getYearsNotEnd();
        tkb_namHocsNotEnd.sort(Comparator.comparing(TKB_NamHoc::getNgayBatDau));

        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocsCuaNamDauTien = new ArrayList<>();
        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_namHocsNotEnd.get(0).getTkb_kiHoc_namHocs()) {
            tkb_kiHoc_namHocsCuaNamDauTien.add(tkb_kiHoc_namHoc);
        }
        tkb_kiHoc_namHocsCuaNamDauTien.sort(Comparator.comparing(TKB_KiHoc_NamHoc::getNgayBatDau));

        TKB_KiHoc_NamHoc tkb_kiHoc_namHocSauCung = tkb_kiHoc_namHocsCuaNamDauTien.get(tkb_kiHoc_namHocsCuaNamDauTien.size() - 1);
        if (sqlDate.compareTo(tkb_kiHoc_namHocSauCung.getNgayBatDau()) > 0) {
            tkb_namHocsNotEnd.remove(0);
        }

        return new ResponseEntity<List<TKB_NamHoc>>(tkb_namHocsNotEnd, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/calendar/{yearId}/semester-not-end")
    public ResponseEntity<List<TKB_KiHoc>> getKiHocNotEndOfYear(@PathVariable int yearId) {
        TKB_NamHoc tkb_namHoc = namHocService.findOne(yearId);
        Set<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = tkb_namHoc.getTkb_kiHoc_namHocs();

        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        tkb_kiHoc_namHocs.removeIf(kiHoc_namHoc -> sqlDate.compareTo(kiHoc_namHoc.getNgayBatDau()) > 0);

        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocsArrayList = new ArrayList<>();
        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocs) {
            tkb_kiHoc_namHocsArrayList.add(tkb_kiHoc_namHoc);
        }
        tkb_kiHoc_namHocsArrayList.sort(Comparator.comparing(TKB_KiHoc_NamHoc::getNgayBatDau));

        List<TKB_KiHoc> tkb_kiHocs = new ArrayList<>();
        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocsArrayList) {
            tkb_kiHocs.add(tkb_kiHoc_namHoc.getTkb_kiHoc());
        }

        return new ResponseEntity<List<TKB_KiHoc>>(tkb_kiHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/edit-calendar/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}/{nganhId}")
    public ResponseEntity<TKB_LichHocTheoTuan> updateWeekCalendar(@RequestBody TKB_LichHocTheoTuan tkb_lichHocTheoTuan, @PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId, @PathVariable int nganhId) {
        TKB_Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        DMLopMonHoc currentDMLopMonHoc = lopMonHocService.findOne(tkb_lichHocTheoTuanService.getDMLopMonHocIdByTKB_LichHocTheoTuanId(tkb_lichHocTheoTuan.getId()));

        List<DMLopMonHoc> dmLopMonHocs;
        if (nganhId != 0) {
            dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(tkb_kiHoc_namHoc.getId(), khoa_khoaHoc.getId(), nganhId);
        } else {
            dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(tkb_kiHoc_namHoc.getId(), khoa_khoaHoc.getId());
        }

        dmLopMonHocs.remove(currentDMLopMonHoc);

        tkb_lichHocTheoTuan.setTkb_tietDauTien(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietDauTien().getId()));
        tkb_lichHocTheoTuan.setTkb_tietCuoiCung(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId()));

        boolean canUpdate = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, dmLopMonHocs);

        DMNhanVien giaoVien = currentDMLopMonHoc.getDmNhanVien();
        List<DMLopMonHoc> dmLopMonHocsCuaGiaoVien = lopMonHocService.findByGiaoVienIdAndNamHocKiHocId(giaoVien.getId(), tkb_kiHoc_namHoc.getId());
        dmLopMonHocsCuaGiaoVien.remove(currentDMLopMonHoc);

        boolean canUpdate2 = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, dmLopMonHocsCuaGiaoVien);

        if (canUpdate && canUpdate2) {
            tkb_lichHocTheoTuanService.updateWeekCalendar(tkb_lichHocTheoTuan);
            return new ResponseEntity<TKB_LichHocTheoTuan>(tkb_lichHocTheoTuanService.findOne(tkb_lichHocTheoTuan.getId()), HttpStatus.OK);
        } else {
            if (!canUpdate) {
                return new ResponseEntity<TKB_LichHocTheoTuan>(HttpStatus.FORBIDDEN);
            } else {
                return new ResponseEntity<TKB_LichHocTheoTuan>(HttpStatus.CONFLICT);
            }
        }
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/add-calendar/{DMLopMonHocId}/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}/{nganhId}")
    public ResponseEntity<TKB_LichHocTheoTuan> addCalendar(@RequestBody TKB_LichHocTheoTuan tkb_lichHocTheoTuan, @PathVariable int DMLopMonHocId, @PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId, @PathVariable int nganhId) {

        TKB_Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        List<DMLopMonHoc> dmLopMonHocs;
        if (nganhId != 0) {
            dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(tkb_kiHoc_namHoc.getId(), khoa_khoaHoc.getId(), nganhId);
        } else {
            dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(tkb_kiHoc_namHoc.getId(), khoa_khoaHoc.getId());
        }

        tkb_lichHocTheoTuan.setTkb_tietDauTien(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietDauTien().getId()));
        tkb_lichHocTheoTuan.setTkb_tietCuoiCung(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId()));

        boolean canAdd = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, dmLopMonHocs);

        DMNhanVien giaoVien = lopMonHocService.findOne(DMLopMonHocId).getDmNhanVien();
        List<DMLopMonHoc> dmLopMonHocsCuaGiaoVien = lopMonHocService.findByGiaoVienIdAndNamHocKiHocId(giaoVien.getId(), tkb_kiHoc_namHoc.getId());
        boolean canAdd2 = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, dmLopMonHocsCuaGiaoVien);

        if (canAdd && canAdd2) {
            tkb_lichHocTheoTuan.setDmGiangDuong(giangDuongService.findOne(tkb_lichHocTheoTuan.getDmGiangDuong().getId()));
            tkb_lichHocTheoTuan.setTkb_thu(tkb_thuService.findOne(tkb_lichHocTheoTuan.getTkb_thu().getId()));
            tkb_lichHocTheoTuan.setDmLopMonHoc(lopMonHocService.findOne(DMLopMonHocId));
            tkb_lichHocTheoTuanService.addWeekCalendar(tkb_lichHocTheoTuan);
            return new ResponseEntity<TKB_LichHocTheoTuan>(tkb_lichHocTheoTuanService.findOne(tkb_lichHocTheoTuan.getId()), HttpStatus.OK);
        } else {
            if (!canAdd) {
                return new ResponseEntity<TKB_LichHocTheoTuan>(tkb_lichHocTheoTuan, HttpStatus.FORBIDDEN);
            } else {
                return new ResponseEntity<TKB_LichHocTheoTuan>(HttpStatus.CONFLICT);
            }
        }


    }

    @PreAuthorize("hasRole('GIAOVU')")
    @DeleteMapping(value = "/delete-calendar/{calendarId}")
    public void deleteCalendar(@PathVariable int calendarId) {
        tkb_lichHocTheoTuanService.deleteCalendar(calendarId);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-khoa-khoahoc/{namHocId}/{kiHocId}")
    public ResponseEntity<List<DMDonVi>> getKhoa_KhoaHoc(@PathVariable int namHocId, @PathVariable int kiHocId) {
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        List<DMDonVi> dMDonVis = khoaService.findAll();
        for (DMDonVi dMDonVi :
                dMDonVis) {
            dMDonVi.getTkb_khoa_KhoaHocs().removeIf(khoa_khoaHoc -> !checkKhoaFitable(khoa_khoaHoc, tkb_kiHoc_namHoc));
        }
        dMDonVis.removeIf(khoa -> khoa.getTkb_khoa_KhoaHocs().size() == 0);

        for (DMDonVi dMDonVi :
                dMDonVis) {
            for (TKB_Khoa_KhoaHoc khoa_khoaHoc :
                    dMDonVi.getTkb_khoa_KhoaHocs()) {
                khoa_khoaHoc.getTkbThoiGianDangKies().removeIf(registerTime -> registerTime.getTkb_kiHoc_namHoc().getId() != tkb_kiHoc_namHoc.getId());
            }
        }

        dMDonVis.sort(Comparator.comparing(DMDonVi::getTen));

        for (DMDonVi dmDonVi :
                dMDonVis) {
            List<TKB_Khoa_KhoaHoc> khoa_khoaHocs = new ArrayList<>(dmDonVi.getTkb_khoa_KhoaHocs());
            khoa_khoaHocs.sort(Comparator.comparing(TKB_Khoa_KhoaHoc::getId));
            Set<TKB_Khoa_KhoaHoc> khoa_khoaHocsSet = new LinkedHashSet<>();
            for (TKB_Khoa_KhoaHoc khoa_khoaHoc :
                    khoa_khoaHocs) {
                khoa_khoaHocsSet.add(khoa_khoaHoc);
            }

            dmDonVi.setTkb_khoa_KhoaHocs(khoa_khoaHocsSet);
        }

        return new ResponseEntity<List<DMDonVi>>(dMDonVis, HttpStatus.OK);
    }

    boolean checkKhoaFitable(TKB_Khoa_KhoaHoc khoa_khoaHoc, TKB_KiHoc_NamHoc tkb_kiHoc_namHoc) {
        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByKhoa_KhoaHoc(khoa_khoaHoc.getId());
        dmLopMonHocs.removeIf(DMLopMonHoc -> DMLopMonHoc.getTkb_kiHoc_namHoc().getId() != tkb_kiHoc_namHoc.getId());
        if (dmLopMonHocs.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/open-registering/{registerTimeId}")
    public ResponseEntity<?> openRegistering(@PathVariable int registerTimeId) {
        thoiGianDangKyService.udpateRegistering(registerTimeId, true);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/close-registering/{registerTimeId}")
    public ResponseEntity<Object> closeRegistering(@PathVariable int registerTimeId) {
        thoiGianDangKyService.udpateRegistering(registerTimeId, false);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/generate-calendar/get-conditions/{termId}/{yearId}")
    public ResponseEntity<List<TKB_DieuKien_TuDong>> getAllDieuKien(@PathVariable int termId, @PathVariable int yearId) {
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(termId, yearId);
        List<TKB_DieuKien_TuDong> tkb_dieuKien_tuDongs = new ArrayList<>();
        for (TKB_KiHoc_NamHoc_DieuKien tkb_kiHoc_namHoc_dieuKien :
                tkb_kiHoc_namHoc.getTkb_kiHoc_namHoc_dieuKiens()) {
            tkb_dieuKien_tuDongs.add(tkb_kiHoc_namHoc_dieuKien.getTkb_dieuKien_tuDong());
        }

        tkb_dieuKien_tuDongs.sort(Comparator.comparing(TKB_DieuKien_TuDong::getId));

        return new ResponseEntity<List<TKB_DieuKien_TuDong>>(tkb_dieuKien_tuDongs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/generate-random-calendar")
    public ResponseEntity<Object> generateRandomCalendarForSemester(@RequestBody Setting setting) throws JsonProcessingException {
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(setting.getKyHocId(), setting.getNamHocId());
        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocId(tkb_kiHoc_namHoc.getId());
        dmLopMonHocs.sort(Comparator.comparing(DMLopMonHoc::getId));
        List<CaThe> quanThe = new ArrayList<>();
        for (int i = 0; i < numberOfInviduals; i++) {
            List<DMLopMonHoc> dmLopMonHocsTemp = new ArrayList<>();
            for (DMLopMonHoc DMLopMonHoc :
                    dmLopMonHocs) {
                dmLopMonHocsTemp.add(new DMLopMonHoc(DMLopMonHoc));
            }
            quanThe.add(new CaThe(dmLopMonHocsTemp));
        }
        theHe = 1;
        if (this.checkdmLopMonHocsAllFree(dmLopMonHocs)) {

            //khoi tao quan the ban dau
            for (CaThe caThe : quanThe) {
                for (DMLopMonHoc DMLopMonHoc :
                        caThe.getDMLopMonHocList()) {
                    String result = randomCalendarForClass(DMLopMonHoc);
                    if (result != "Thành công") {
                        return new ResponseEntity<>(result, HttpStatus.OK);
                    }
                }
                caThe.setDiemThichNghi(this.getDiemThichNghiCuaCaThe(caThe.getDMLopMonHocList(), setting,false, false));
            }
            this.danhSoTKB_TuanId(quanThe);
            for (CaThe caThe : quanThe) {
                caThe.setDiemThichNghi(this.getDiemThichNghiCuaCaThe(caThe.getDMLopMonHocList(), setting,false, false));
            }

            quanThe.sort(Comparator.comparing(CaThe::getDiemThichNghi));
            System.out.println("------------------------------------------Thế hệ 1------------------------------------------");
            printQuanThe(quanThe);

            if (checkSuccess(setting.getKyHocId(), setting.getNamHocId(), quanThe, setting.getDiemThichNghiToiUu())) {
                for (DMLopMonHoc DMLopMonHoc :
                        quanThe.get(0).getDMLopMonHocList()) {
                    for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                            DMLopMonHoc.getTkb_lichHocTheoTuans()) {
                        tkb_lichHocTheoTuan.setDmLopMonHoc(lopMonHocService.findOne(DMLopMonHoc.getId()));
                        tkb_lichHocTheoTuanService.addWeekCalendar(tkb_lichHocTheoTuan);
                    }
                }
                return new ResponseEntity<>("Sinh thời khóa biểu tự động thành công", HttpStatus.OK);
            }
//            Tien hanh tien hoa
            int numberOfParents = this.numberOfInviduals * this.parentsPercentage / 100;
            int numberOfCrossOver = this.numberOfInviduals * this.crossOverPercentage / 100;
            if (numberOfCrossOver % 2 != 0) {
                numberOfCrossOver--;
            }
            int numberOfMutation = this.numberOfInviduals - numberOfParents - numberOfCrossOver;
            List<CaThe> quanTheTemp = new ArrayList<>();
            for (int i = 1; i < setting.getSoTheHe(); i++) {
                quanTheTemp.clear();
//                System.out.println(quanThe.get(0).getDiemThichNghi());

                List<CaThe> parents = this.chooseParents(quanThe, numberOfParents);
                quanThe.get(0).setDiemThichNghi(this.getDiemThichNghiCuaCaThe(quanThe.get(0).getDMLopMonHocList(), setting, false, false));
//                System.out.println(quanThe.get(0).getDiemThichNghi());

                List<CaThe> crossOvers = this.crossOverGeneration(parents, numberOfCrossOver);
                quanThe.get(0).setDiemThichNghi(this.getDiemThichNghiCuaCaThe(quanThe.get(0).getDMLopMonHocList(), setting, false, false));
//                System.out.println(quanThe.get(0).getDiemThichNghi());

                List<CaThe> mutations = this.mutateGeneration(parents, numberOfMutation, setting);
                quanThe.get(0).setDiemThichNghi(this.getDiemThichNghiCuaCaThe(quanThe.get(0).getDMLopMonHocList(), setting, false, false));
//                System.out.println(quanThe.get(0).getDiemThichNghi());

                quanTheTemp.addAll(parents);
                quanTheTemp.addAll(crossOvers);
                quanTheTemp.addAll(mutations);

                this.danhSoTKB_TuanId(quanTheTemp);
                for (CaThe caThe : quanTheTemp) {
                    caThe.setDiemThichNghi(this.getDiemThichNghiCuaCaThe(caThe.getDMLopMonHocList(), setting, false, false));
                }

                quanTheTemp.sort(Comparator.comparing(CaThe::getDiemThichNghi));
                System.out.println("------------------------------------------Thế hệ " + (i + 1) + "------------------------------------------");
                printQuanThe(quanTheTemp);

                if (checkSuccess(setting.getKyHocId(), setting.getNamHocId(), quanTheTemp, setting.getDiemThichNghiToiUu())) {
                    for (DMLopMonHoc DMLopMonHoc :
                            quanThe.get(0).getDMLopMonHocList()) {
                        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                                DMLopMonHoc.getTkb_lichHocTheoTuans()) {
                            tkb_lichHocTheoTuan.setDmLopMonHoc(lopMonHocService.findOne(DMLopMonHoc.getId()));
                            tkb_lichHocTheoTuanService.addWeekCalendar(tkb_lichHocTheoTuan);
                        }
                    }
                    return new ResponseEntity<>("Sinh thời khóa biểu tự động thành công", HttpStatus.OK);
                } else {
                    quanThe.clear();
                    quanThe.addAll(quanTheTemp);
                }
            }
            this.getDiemThichNghiCuaCaThe(quanThe.get(0).getDMLopMonHocList(), setting, true, true);
            return new ResponseEntity<>(lopMonHoc_viPhams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Có ít nhất một lớp đã có thời khóa biểu, hãy xóa hết thời khóa biểu để có thể tiến hành sinh thời khóa biểu tự động", HttpStatus.OK);
        }
    }

    public List<CaThe> chooseParents(List<CaThe> quanThe, int numberOfParents) {
        List<CaThe> parents = new ArrayList<>();
        for (int i = 0; i < numberOfParents; i++) {
            parents.add(new CaThe(quanThe.get(i).getDMLopMonHocList()));
        }
        return parents;
    }

    public List<CaThe> crossOverGeneration(List<CaThe> parents, int numberOfCrossOver) {
        List<CaThe> crossOvers = new ArrayList<>();
        Random random = new Random();
        int index1, index2;
        int crossOverPoint = (parents.get(0).getDMLopMonHocList().size()) / 2;
        for (int i = 0; i < numberOfCrossOver / 2; i++) {
            index1 = random.nextInt(parents.size());
            index2 = random.nextInt(parents.size());
            while (index2 == index1) {
                index2 = random.nextInt(parents.size());
            }
            crossOvers.add(this.crossOver1_1(parents.get(index1), parents.get(index2), crossOverPoint));
            crossOvers.add(this.crossOver1_2(parents.get(index1), parents.get(index2), crossOverPoint));
        }

        return crossOvers;
    }

    public List<CaThe> mutateGeneration(List<CaThe> parents, int numberOfMutations, Setting setting) {
        List<CaThe> mutations = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfMutations; i++) {
            int index = random.nextInt(parents.size());
            mutations.add(this.mutate(parents.get(index)));
        }

        return mutations;
    }

    public void printQuanThe(List<CaThe> quanThe) {
        int i = 1;
        System.out.println("Điểm thích nghi thấp nhất: " + quanThe.get(0).getDiemThichNghi());
//        for (CaThe caThe :
//                quanThe) {
//            System.out.println("--------------------------");
//            System.out.println("Ca the " + i + ": " + caThe.getDiemThichNghi());
//            i++;
//            for (DMLopMonHoc DMLopMonHoc :
//                    caThe.getDMLopMonHocList()) {
//                System.out.println("Mon hoc: " + DMLopMonHoc.getMonHoc().getTen() + " - giao vien: " + DMLopMonHoc.getGiaoVien().getTen());
//                for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
//                        DMLopMonHoc.getTkb_lichHocTheoTuans()) {
//                    System.out.println(tkb_lichHocTheoTuan.getId() + " - " + tkb_lichHocTheoTuan.getTkb_thu().getTen() + " - " + tkb_lichHocTheoTuan.getGiangDuong().getTen()
//                            + " - " + tkb_lichHocTheoTuan.getTuanBatDau() + " toi " + tkb_lichHocTheoTuan.getTuanKetThuc() + " - "
//                            + tkb_lichHocTheoTuan.getTkb_tietDauTien().getTen() + " toi " + tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getTen());
//                }
//            }
//        }
    }


    public boolean checkSuccess(int kyHocId, int namHocId, List<CaThe> quanThe, int diemThichNghiToiUu) throws JsonProcessingException {
        GenerationSocketMessage generationSocketMessage = new GenerationSocketMessage(kyHocId, namHocId, theHe, quanThe.get(0).getDiemThichNghi());
        String mess = this.mapper.writeValueAsString(generationSocketMessage);
        this.template.convertAndSend("/socket/calendar/auto-generate", mess);
        this.theHe++;
        if (quanThe.get(0).getDiemThichNghi() <= diemThichNghiToiUu) {
            return true;
        } else {
            return false;
        }
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/delete-all-calendars/{semesterId}/{yearId}")
    public ResponseEntity<String> deleteAllCalendarsOfSemester(@PathVariable int semesterId, @PathVariable int yearId) {
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(semesterId, yearId);
        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocId(tkb_kiHoc_namHoc.getId());
        for (DMLopMonHoc DMLopMonHoc :
                dmLopMonHocs) {
            if (!DMLopMonHoc.getTkb_lichHocTheoTuans().isEmpty()) {
                tkb_lichHocTheoTuanService.deleteWeekCalendarOfDMLopMonHoc(DMLopMonHoc.getId());
            }
        }

        return new ResponseEntity<String>("Reset thời khóa biểu thành công", HttpStatus.OK);
    }

    public void danhSoTKB_TuanId(List<CaThe> quanThe) {
        for (CaThe caThe :
                quanThe) {
            int id = 1;
            for (DMLopMonHoc DMLopMonHoc :
                    caThe.getDMLopMonHocList()) {
                for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                        DMLopMonHoc.getTkb_lichHocTheoTuans()) {
                    tkb_lichHocTheoTuan.setId(id);
                    id++;
                }
            }
        }
    }

    public boolean checkCaThePerfect(List<CaThe> quanThe) {
        for (CaThe caThe :
                quanThe) {
            if (caThe.getDiemThichNghi() < 30) {
                return true;
            }
        }

        return false;
    }

    public int getDiemThichNghiCuaCaThe(List<DMLopMonHoc> dmLopMonHocs, Setting setting, boolean theHeCuoiCung, boolean caTheDauTien) {
        int diem = 0;
        lopMonHoc_viPhams.clear();
        for (DMLopMonHoc DMLopMonHoc :
                dmLopMonHocs) {
            LopMonHoc_ViPham lopMonHoc_viPham = new LopMonHoc_ViPham();
            diem += this.getDiemThichNghiCuaDMLopMonHoc(DMLopMonHoc, dmLopMonHocs, setting, theHeCuoiCung, caTheDauTien, lopMonHoc_viPham);
            if(theHeCuoiCung&&caTheDauTien){
                lopMonHoc_viPham.setLopMonHoc(new LopMonHoc(DMLopMonHoc));
                lopMonHoc_viPham.setTkb_lichHocTheoTuans(new ArrayList<>(DMLopMonHoc.getTkb_lichHocTheoTuans()));
                lopMonHoc_viPhams.add(lopMonHoc_viPham);
            }
        }

        return diem;
    }


    public int getDiemThichNghiCuaDMLopMonHoc(DMLopMonHoc DMLopMonHoc, List<DMLopMonHoc> dmLopMonHocs, Setting setting, boolean theHeCuoiCung, boolean caTheDauTien, LopMonHoc_ViPham lopMonHoc_viPham) {
//        System.out.println("----**Size: " + dmLopMonHocs.size() + "**----");
        int diem = 0;

        for (ChosenCondition chosenCondition :
                setting.getChosenConditions()) {
            ViPham viPham = new ViPham();
            int diemTemp;
            switch (chosenCondition.getId()) {
                case 1:
                    diemTemp= this.dk1(DMLopMonHoc, this.cloneListDMLopMonHoc(dmLopMonHocs), chosenCondition.getValue(), theHeCuoiCung);
                    if(theHeCuoiCung&&caTheDauTien){
                        viPham.setDkNumber(1);
                        viPham.setDiem(diemTemp);
                        lopMonHoc_viPham.getViPhams().add(viPham);
                    }
                    diem+=diemTemp;
                    break;
                case 2:
                    diemTemp= this.dk2(DMLopMonHoc, this.cloneListDMLopMonHoc(dmLopMonHocs), chosenCondition.getValue(), theHeCuoiCung);
                    if(theHeCuoiCung&&caTheDauTien){
                        viPham.setDkNumber(2);
                        viPham.setDiem(diemTemp);
                        lopMonHoc_viPham.getViPhams().add(viPham);
                    }
                    diem += diemTemp;
                    break;
                case 3:
                    diemTemp= this.dk3(DMLopMonHoc, this.cloneListDMLopMonHoc(dmLopMonHocs), chosenCondition.getValue(), theHeCuoiCung);
                    if(theHeCuoiCung&&caTheDauTien){
                        viPham.setDkNumber(3);
                        viPham.setDiem(diemTemp);
                        lopMonHoc_viPham.getViPhams().add(viPham);
                    }
                    break;
                case 4:
                    diemTemp= this.dk4(DMLopMonHoc, chosenCondition.getValue(), theHeCuoiCung);
                    if(theHeCuoiCung&&caTheDauTien){
                        viPham.setDkNumber(4);
                        viPham.setDiem(diemTemp);
                        lopMonHoc_viPham.getViPhams().add(viPham);
                    }
                    diem += diemTemp;
                    break;
                case 5:
                    diemTemp= this.dk5(DMLopMonHoc, chosenCondition.getValue(), theHeCuoiCung);
                    if(theHeCuoiCung&&caTheDauTien){
                        viPham.setDkNumber(5);
                        viPham.setDiem(diemTemp);
                        lopMonHoc_viPham.getViPhams().add(viPham);
                    }
                    diem += diemTemp;
                    break;
                case 6:
                    diemTemp= this.dk6(DMLopMonHoc, this.cloneListDMLopMonHoc(dmLopMonHocs), chosenCondition.getValue(), theHeCuoiCung);
                    if(theHeCuoiCung&&caTheDauTien){
                        viPham.setDkNumber(6);
                        viPham.setDiem(diemTemp);
                        lopMonHoc_viPham.getViPhams().add(viPham);
                    }
                    diem += diemTemp;
                    break;
                case 7:
                    diemTemp= this.dk7(DMLopMonHoc, this.cloneListDMLopMonHoc(dmLopMonHocs), chosenCondition.getValue(), theHeCuoiCung);
                    if(theHeCuoiCung&&caTheDauTien){
                        viPham.setDkNumber(7);
                        viPham.setDiem(diemTemp);
                        lopMonHoc_viPham.getViPhams().add(viPham);
                    }
                    diem += diemTemp;
                    break;
                case 8:
                    diemTemp= this.dk8(DMLopMonHoc, this.cloneListDMLopMonHoc(dmLopMonHocs), chosenCondition.getValue(), theHeCuoiCung);
                    if(theHeCuoiCung&&caTheDauTien){
                        viPham.setDkNumber(8);
                        viPham.setDiem(diemTemp);
                        lopMonHoc_viPham.getViPhams().add(viPham);
                    }
                    diem += diemTemp;
                    diem += diemTemp;
                    break;
                case 9:
                    diemTemp= this.dk9(DMLopMonHoc, this.cloneListDMLopMonHoc(dmLopMonHocs), chosenCondition.getValue(), theHeCuoiCung);
                    if(theHeCuoiCung&&caTheDauTien){
                        viPham.setDkNumber(9);
                        viPham.setDiem(diemTemp);
                        lopMonHoc_viPham.getViPhams().add(viPham);
                    }
                    diem += diemTemp;
                    break;
                default:
                    diem += 0;
            }
        }
        return diem;
    }

    public List<DMLopMonHoc> cloneListDMLopMonHoc(List<DMLopMonHoc> dmLopMonHocs) {
        List<DMLopMonHoc> clone = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHoc :
                dmLopMonHocs) {
            clone.add(new DMLopMonHoc(DMLopMonHoc));
        }

        return clone;
    }

    public int dk1(DMLopMonHoc DMLopMonHoc, List<DMLopMonHoc> dmLopMonHocs, int dkValue, boolean theHeCuoiCung) {
        int diem = 0;
//        System.out.println(dmLopMonHocs.size());
        List<DMLopMonHoc> dmLopMonHocsCuaGiaoVien = dmLopMonHocs;
        dmLopMonHocsCuaGiaoVien.removeIf(DMLopMonHoc1 -> DMLopMonHoc1.getDmNhanVien().getId() != DMLopMonHoc.getDmNhanVien()
                .getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHocCuaGiaoVien :
                dmLopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(DMLopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = DMLopMonHoc.getTkb_lichHocTheoTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVienTemp = lichDaysCuaGiaoVien;
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> (lichDayCuaGiaoVien.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (lichDayCuaGiaoVien.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> lichDayCuaGiaoVien.getTkb_thu().getId() != tkb_lichHocTheoTuan.getTkb_thu().getId());
            int tongSoTiet = 0;
            for (TKB_LichHocTheoTuan lichDayCuaGiaoVien :
                    lichDaysCuaGiaoVienTemp) {
                tongSoTiet += lichDayCuaGiaoVien.getSoTiet();
            }
            if (tongSoTiet > 8) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public int dk2(DMLopMonHoc dmLopMonHoc, List<DMLopMonHoc> dmLopMonHocs, int dkValue, boolean theHeCuoiCung) {
        int diem = 0;
//        System.out.println(dmLopMonHocs.size());
        List<DMLopMonHoc> dmLopMonHocsCuaGiaoVien = dmLopMonHocs;
        dmLopMonHocsCuaGiaoVien.removeIf(DMLopMonHoc1 -> DMLopMonHoc1.getDmNhanVien().getId() != dmLopMonHoc.getDmNhanVien().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHocCuaGiaoVien :
                dmLopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(DMLopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = dmLopMonHoc.getTkb_lichHocTheoTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVienTemp = lichDaysCuaGiaoVien;
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> (lichDayCuaGiaoVien.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (lichDayCuaGiaoVien.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> lichDayCuaGiaoVien.getTkb_thu().getId() != tkb_lichHocTheoTuan.getTkb_thu().getId());
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> !"Dãy nhà lý thuyết".equals(lichDayCuaGiaoVien.getDmGiangDuong().getDmLoaiPhong().getTen()));
            int tongSoTiet = 0;
            for (TKB_LichHocTheoTuan lichDayCuaGiaoVien :
                    lichDaysCuaGiaoVienTemp) {
                tongSoTiet += lichDayCuaGiaoVien.getSoTiet();
            }
            if (tongSoTiet > 6) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public int dk3(DMLopMonHoc dmLopMonHoc, List<DMLopMonHoc> dmLopMonHocs, int dkValue, boolean theHeCuoiCung) {
        int diem = 0;
//        System.out.println(dmLopMonHocs.size());
        List<DMLopMonHoc> dmLopMonHocsCuaGiaoVien = dmLopMonHocs;
        dmLopMonHocsCuaGiaoVien.removeIf(DMLopMonHoc1 -> DMLopMonHoc1.getDmNhanVien().getId() != dmLopMonHoc.getDmNhanVien().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHocCuaGiaoVien :
                dmLopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(DMLopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = dmLopMonHoc.getTkb_lichHocTheoTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVienTemp = lichDaysCuaGiaoVien;
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> (lichDayCuaGiaoVien.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (lichDayCuaGiaoVien.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> !"Dãy nhà lý thuyết".equals(lichDayCuaGiaoVien.getDmGiangDuong().getDmLoaiPhong().getTen()));
            int tongSoTiet = 0;
            for (TKB_LichHocTheoTuan lichDayCuaGiaoVien :
                    lichDaysCuaGiaoVienTemp) {
                tongSoTiet += lichDayCuaGiaoVien.getSoTiet();
            }
            if (tongSoTiet > 30) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public int dk4(DMLopMonHoc DMLopMonHoc, int dkValue, boolean theHeCuoiCung) {
        int diem = 0;

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = DMLopMonHoc.getTkb_lichHocTheoTuans();
        Set<TKB_NhanVien_NgayNghiTrongTuan> tkb_nhanVien_ngayNghiTrongTuans = DMLopMonHoc.getDmNhanVien().getTkb_nhanVien_ngayNghiTrongTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            for (TKB_NhanVien_NgayNghiTrongTuan tkb_nhanVien_ngayNghiTrongTuan :
                    tkb_nhanVien_ngayNghiTrongTuans) {
                if (tkb_lichHocTheoTuan.getTkb_thu().getId() == tkb_nhanVien_ngayNghiTrongTuan.getTkb_thu().getId()) {
                    diem++;
                }
            }
        }

        return diem * dkValue;
    }

    public int dk5(DMLopMonHoc DMLopMonHoc, int dkValue, boolean theHeCuoiCung) {
        int diem = 0;

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = DMLopMonHoc.getTkb_lichHocTheoTuans();
        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuanList = new ArrayList<>(tkb_lichHocTheoTuans);
        if (tkb_lichHocTheoTuanList.size() > 1) {
            for (int i = 0; i < tkb_lichHocTheoTuanList.size() - 1; i++) {
                for (int j = i + 1; j < tkb_lichHocTheoTuanList.size(); j++) {
                    if (tkb_lichHocTheoTuanList.get(i).getTkb_thu().getId() == tkb_lichHocTheoTuanList.get(j).getTkb_thu().getId()) {
                        diem++;
                    }
                }
            }
        }

        return diem * dkValue;
    }

    public int dk6(DMLopMonHoc DMLopMonHoc, List<DMLopMonHoc> dmLopMonHocsCuaGiaoVien, int dkValue, boolean theHeCuoiCung) {
        int diem = 0;
        dmLopMonHocsCuaGiaoVien.removeIf(DMLopMonHoc1 -> DMLopMonHoc1.getDmNhanVien().getId() != DMLopMonHoc.getDmNhanVien().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHocCuaGiaoVien :
                dmLopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(DMLopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = DMLopMonHoc.getTkb_lichHocTheoTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVienTemp = this.cloneTKBTuan(lichDaysCuaGiaoVien);
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> lichDayCuaGiaoVien.getId() == tkb_lichHocTheoTuan.getId());
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> (lichDayCuaGiaoVien.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (lichDayCuaGiaoVien.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> lichDayCuaGiaoVien.getTkb_thu().getId() != tkb_lichHocTheoTuan.getTkb_thu().getId());
            List<TKB_Tiet> tietsDayCuaGiaoVien = this.getTietNotFree(lichDaysCuaGiaoVienTemp);
            if (this.checkTrungLich(tietsDayCuaGiaoVien, tkb_lichHocTheoTuan)) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public int dk7(DMLopMonHoc dmLopMonHoc, List<DMLopMonHoc> dmLopMonHocs, int dkValue, boolean theHeCuoiCung) {
        int diem = 0;
        if (dmLopMonHoc.getDmNganh() == null) {
            dmLopMonHocs.removeIf(DMLopMonHoc1 -> DMLopMonHoc1.getTkb_khoa_khoaHoc().getId() != dmLopMonHoc.getTkb_khoa_khoaHoc().getId());
        } else {
            dmLopMonHocs.removeIf(DMLopMonHoc1 -> DMLopMonHoc1.getDmNganh() == null);
            dmLopMonHocs.removeIf(DMLopMonHoc1 -> (DMLopMonHoc1.getTkb_khoa_khoaHoc().getId() != dmLopMonHoc.getTkb_khoa_khoaHoc().getId()) && (dmLopMonHoc.getDmNganh().getId() != DMLopMonHoc1.getDmNganh().getId()));
//            dmLopMonHocs.removeIf(DMLopMonHoc1 -> DMLopMonHoc1.getKhoa_khoaHoc().getId() != DMLopMonHoc.getKhoa_khoaHoc().getId() && DMLopMonHoc1.getNganh().getId() != DMLopMonHoc.getNganh().getId());
        }

        List<TKB_LichHocTheoTuan> lichHocCuaKhoaKhoaHocNganh = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHoc1 :
                dmLopMonHocs) {
            lichHocCuaKhoaKhoaHocNganh.addAll(DMLopMonHoc1.getTkb_lichHocTheoTuans());
        }

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                dmLopMonHoc.getTkb_lichHocTheoTuans()) {
            List<TKB_LichHocTheoTuan> lichHocCuaKhoaKhoaHocNganhTemp = this.cloneTKBTuan(lichHocCuaKhoaKhoaHocNganh);
            lichHocCuaKhoaKhoaHocNganhTemp.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getId() == tkb_lichHocTheoTuan.getId());
            lichHocCuaKhoaKhoaHocNganhTemp.removeIf(tkb_lichHocTheoTuan1 -> (tkb_lichHocTheoTuan1.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (tkb_lichHocTheoTuan1.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            if (this.checkTrungLichKhoaKhoaHocNganh(tkb_lichHocTheoTuan, lichHocCuaKhoaKhoaHocNganhTemp)) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public int dk8(DMLopMonHoc dmLopMonHoc, List<DMLopMonHoc> dmLopMonHocs, int dkValue, boolean theHeCuoiCung) {
        int diem = 0;
        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHoc1 :
                dmLopMonHocs) {
            tkb_lichHocTheoTuans.addAll(DMLopMonHoc1.getTkb_lichHocTheoTuans());
        }

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                dmLopMonHoc.getTkb_lichHocTheoTuans()) {
            List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuansTemp = this.cloneTKBTuan(tkb_lichHocTheoTuans);
            tkb_lichHocTheoTuansTemp.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getId() == tkb_lichHocTheoTuan.getId());
            tkb_lichHocTheoTuansTemp.removeIf(tkb_lichHocTheoTuan1 -> (tkb_lichHocTheoTuan1.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (tkb_lichHocTheoTuan1.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            if (checkTrungLichPhong(tkb_lichHocTheoTuan, tkb_lichHocTheoTuansTemp)) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public int dk9(DMLopMonHoc dmLopMonHoc, List<DMLopMonHoc> dmLopMonHocs, int dkValue, boolean theHeCuoiCung) {
        int diem = 0;
        if (dmLopMonHoc.getDmNganh() == null) {
            dmLopMonHocs.removeIf(DMLopMonHoc1 -> DMLopMonHoc1.getTkb_khoa_khoaHoc().getId() != dmLopMonHoc.getTkb_khoa_khoaHoc().getId());
        } else {
            dmLopMonHocs.removeIf(DMLopMonHoc1 -> DMLopMonHoc1.getDmNganh() == null);
            dmLopMonHocs.removeIf(DMLopMonHoc1 -> (DMLopMonHoc1.getTkb_khoa_khoaHoc().getId() != dmLopMonHoc.getTkb_khoa_khoaHoc().getId()) && (dmLopMonHoc.getDmNganh().getId() != DMLopMonHoc1.getDmNganh().getId()));
        }
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                dmLopMonHoc.getTkb_lichHocTheoTuans()) {
            if (this.checkQuaNhieuBuoiHocTrongCungKhoangThoiGian(tkb_lichHocTheoTuan, dmLopMonHocs)) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public CaThe mutate(CaThe caTheParameter) {
        CaThe caThe = new CaThe(caTheParameter.getDMLopMonHocList());
        int numberOfNSTMutate = mutatePercentage * caThe.getDMLopMonHocList().size() / 100;

        Random random = new Random();

        ArrayList<Integer> nstMutateds = new ArrayList<>();
        for (int i = 0; i < numberOfNSTMutate; i++) {
            int caTheMutate = random.nextInt(caThe.getDMLopMonHocList().size());
            while (nstMutateds.contains(caTheMutate)) {
                caTheMutate = random.nextInt(caThe.getDMLopMonHocList().size());
            }
            caThe.getDMLopMonHocList().get(caTheMutate).setTkb_lichHocTheoTuans(null);
            this.randomCalendarForClass(caThe.getDMLopMonHocList().get(caTheMutate));
            nstMutateds.add(caTheMutate);
        }
        return caThe;
    }

    public CaThe crossOver1_1(CaThe caThe1, CaThe caThe2, int crossOverPoint) {
        List<DMLopMonHoc> dmLopMonHocs = new ArrayList<>();
        for (int i = 0; i < crossOverPoint; i++) {
            dmLopMonHocs.add(caThe1.getDMLopMonHocList().get(i));
        }
        for (int i = crossOverPoint; i < caThe1.getDMLopMonHocList().size(); i++) {
            dmLopMonHocs.add(caThe2.getDMLopMonHocList().get(i));
        }

        return new CaThe(dmLopMonHocs);
    }

    public CaThe crossOver1_2(CaThe caThe1, CaThe caThe2, int crossOverPoint) {
        List<DMLopMonHoc> dmLopMonHocs = new ArrayList<>();
        for (int i = 0; i < crossOverPoint; i++) {
            dmLopMonHocs.add(caThe2.getDMLopMonHocList().get(i));
        }
        for (int i = crossOverPoint; i < caThe1.getDMLopMonHocList().size(); i++) {
            dmLopMonHocs.add(caThe1.getDMLopMonHocList().get(i));
        }

        return new CaThe(dmLopMonHocs);
    }

    public CaThe crossOver2_1(CaThe caThe1, CaThe caThe2, int crossOverPoint1, int crossOverPoint2) {
        List<DMLopMonHoc> dmLopMonHocs = new ArrayList<>();
        for (int i = 0; i < crossOverPoint1; i++) {
            dmLopMonHocs.add(caThe1.getDMLopMonHocList().get(i));
        }
        for (int i = crossOverPoint1; i < crossOverPoint2; i++) {
            dmLopMonHocs.add(caThe2.getDMLopMonHocList().get(i));
        }
        for (int i = 0; i < caThe1.getDMLopMonHocList().size(); i++) {
            dmLopMonHocs.add(caThe1.getDMLopMonHocList().get(i));
        }

        return new CaThe(dmLopMonHocs);
    }

    public CaThe crossOver2_2(CaThe caThe1, CaThe caThe2, int crossOverPoint1, int crossOverPoint2) {
        List<DMLopMonHoc> dmLopMonHocs = new ArrayList<>();
        for (int i = 0; i < crossOverPoint1; i++) {
            dmLopMonHocs.add(caThe2.getDMLopMonHocList().get(i));
        }
        for (int i = crossOverPoint1; i < crossOverPoint2; i++) {
            dmLopMonHocs.add(caThe1.getDMLopMonHocList().get(i));
        }
        for (int i = 0; i < caThe1.getDMLopMonHocList().size(); i++) {
            dmLopMonHocs.add(caThe2.getDMLopMonHocList().get(i));
        }

        return new CaThe(dmLopMonHocs);
    }

    public List<TKB_LichHocTheoTuan> cloneTKBTuan(List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans) {
        List<TKB_LichHocTheoTuan> clone = new ArrayList<>();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            clone.add(new TKB_LichHocTheoTuan(tkb_lichHocTheoTuan));
        }

        return clone;
    }

    public boolean checkQuaNhieuBuoiHocTrongCungKhoangThoiGian(TKB_LichHocTheoTuan tkb_lichHocTheoTuan, List<DMLopMonHoc> dmLopMonHocs) {
        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHoc :
                dmLopMonHocs) {
            tkb_lichHocTheoTuans.addAll(DMLopMonHoc.getTkb_lichHocTheoTuans());
        }
        tkb_lichHocTheoTuans.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getTkb_thu().getId() != tkb_lichHocTheoTuan.getTkb_thu().getId());
        tkb_lichHocTheoTuans.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getId() == tkb_lichHocTheoTuan.getId());

        int weekCalendarSameTime = 0;
        if (tkb_lichHocTheoTuans != null && !tkb_lichHocTheoTuans.isEmpty()) {
            for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan1 :
                    tkb_lichHocTheoTuans) {
                if (tkb_lichHocTheoTuan1.getTkb_tietDauTien() != null && tkb_lichHocTheoTuan1.getTkb_tietCuoiCung() != null && tkb_lichHocTheoTuan.getTkb_tietDauTien() != null && tkb_lichHocTheoTuan.getTkb_tietCuoiCung() != null) {
                    boolean condition1 = tkb_lichHocTheoTuan1.getTkb_tietCuoiCung().getThuTu() < tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu();
                    boolean condition2 = tkb_lichHocTheoTuan1.getTkb_tietDauTien().getThuTu() > tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu();
                    if (!(condition1 || condition2)) {
                        weekCalendarSameTime++;
                    }
                }
            }
        }


        if (weekCalendarSameTime < 3) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkTrungLichPhong(TKB_LichHocTheoTuan tkb_lichHocTheoTuan, List<TKB_LichHocTheoTuan> lichHocCuaPhong) {
//        System.out.println("-----" + tkb_lichHocTheoTuan.getId() + "-------");
//        System.out.println(tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu() + " - " + tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu());
//        System.out.println("bat dau: " + lichHocCuaPhong.size());
        lichHocCuaPhong.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getDmGiangDuong().getId() != tkb_lichHocTheoTuan.getDmGiangDuong().getId());
        lichHocCuaPhong.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getTkb_thu().getId() != tkb_lichHocTheoTuan.getTkb_thu().getId());
//        System.out.println("ket thuc: " + lichHocCuaPhong.size());
        List<TKB_Tiet> tietBanCuaKhoaPhong = this.getTietNotFree(lichHocCuaPhong);
        for (TKB_Tiet tkb_tiet :
                tietBanCuaKhoaPhong) {
//            System.out.println(tkb_tiet.getTen());
        }
        return this.checkTrungLich(tietBanCuaKhoaPhong, tkb_lichHocTheoTuan);
    }

    public boolean checkTrungLichKhoaKhoaHocNganh(TKB_LichHocTheoTuan tkb_lichHocTheoTuan, List<TKB_LichHocTheoTuan> lichHocCuaKhoaKhoaHocNganh) {
        lichHocCuaKhoaKhoaHocNganh.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getTkb_thu().getId() != tkb_lichHocTheoTuan.getTkb_thu().getId());
        List<TKB_Tiet> tietBanCuaKhoaKhoaHocNganh = this.getTietNotFree(lichHocCuaKhoaKhoaHocNganh);
        return this.checkTrungLich(tietBanCuaKhoaKhoaHocNganh, tkb_lichHocTheoTuan);
    }


    public boolean checkTrungLich(List<TKB_Tiet> tkb_tiets, TKB_LichHocTheoTuan tkb_lichHocTheoTuan) {
        for (TKB_Tiet tkb_tiet :
                tkb_tiets) {
            if (tkb_tiet.getThuTu() >= tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu() && tkb_tiet.getThuTu() <= tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu()) {
                return true;
            }
        }

        return false;
    }

    public List<TKB_Tiet> getTietNotFree(List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans) {
        List<TKB_Tiet> tkb_tiets = tkb_tietService.findAll();
        List<TKB_Tiet> tkb_tietsTemp = tkb_tietService.findAll();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
//            System.out.println("Dsadsa"+tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu()+"-"+tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu());
            tkb_tietsTemp.removeIf(tkb_tiet -> tkb_tiet.getThuTu() >= tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu() && tkb_tiet.getThuTu() <= tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu());
        }

        tkb_tiets.removeAll(tkb_tietsTemp);
//        System.out.println("--+=="+tkb_tiets.size());
        return tkb_tiets;
    }

    public int soTietChuaCoLichConLaiCuaDMLopMonHoc(DMLopMonHoc DMLopMonHoc) {
        int totalLessons = DMLopMonHoc.getSoTietLyThuyet() + DMLopMonHoc.getSoTietThucHanh();
        int haveClassLessons = 0;

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                DMLopMonHoc.getTkb_lichHocTheoTuans()) {
            int numberOfWeeks = tkb_lichHocTheoTuan.getTuanKetThuc() - tkb_lichHocTheoTuan.getTuanBatDau() + 1;
            haveClassLessons += numberOfWeeks * (tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu() - tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu() + 1);
        }

        return totalLessons - haveClassLessons;
    }

    public boolean checkdmLopMonHocsAllFree(List<DMLopMonHoc> dmLopMonHocs) {
        for (DMLopMonHoc DMLopMonHoc :
                dmLopMonHocs) {
            if (!DMLopMonHoc.getTkb_lichHocTheoTuans().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String randomCalendarForClass(DMLopMonHoc DMLopMonHoc) {
        int numberOfTheoryLessons = DMLopMonHoc.getSoTietLyThuyet();
        int numberOfPracticeLessons = DMLopMonHoc.getSoTietThucHanh();

        int totalWeek = DMLopMonHoc.getGioiHanTuanKetThuc() - DMLopMonHoc.getGioiHanTuanBatDau() + 1;

        int numberOfTheoryLessonsPerWeek = (numberOfTheoryLessons + totalWeek - 1) / totalWeek;
        int numberOfPracticeLessonsPerWeek = (numberOfPracticeLessons + totalWeek - 1) / totalWeek;

        int theoryWeeksNeeded = numberOfTheoryLessonsPerWeek != 0 ? (numberOfTheoryLessons + numberOfTheoryLessonsPerWeek - 1) / numberOfTheoryLessonsPerWeek : 0;
        int practiceWeeksNeeded = numberOfPracticeLessonsPerWeek != 0 ? (numberOfPracticeLessons + numberOfPracticeLessonsPerWeek - 1) / numberOfPracticeLessonsPerWeek : 0;

        int soBuoiLyThuyetMotTuan = 0;
        int soBuoiThucHanhMotTuan = 0;

        if (numberOfTheoryLessonsPerWeek != 0) {
            if (numberOfTheoryLessonsPerWeek < 6) {
                soBuoiLyThuyetMotTuan = 1;
            } else if (numberOfTheoryLessonsPerWeek < 11) {
                soBuoiLyThuyetMotTuan = 2;
            } else {
                return "lớp môn học " + DMLopMonHoc.getId() + " yêu cầu nhiều hơn 2 buổi ly thuyet 1 tuần";
            }
        }

        if (numberOfPracticeLessonsPerWeek != 0) {
            if (numberOfPracticeLessonsPerWeek < 6) {
                soBuoiThucHanhMotTuan = 1;
            } else if (numberOfPracticeLessonsPerWeek < 11) {
                soBuoiThucHanhMotTuan = 2;
            } else {
                return "lớp môn học " + DMLopMonHoc.getId() + " yêu cầu nhiều hơn 2 buổi thuc hanh 1 tuần";
            }
        }

        List<TKB_LichHocTheoTuan> danhSachBuoiHocLyThuyet = new ArrayList<>();
        if (soBuoiLyThuyetMotTuan == 1) {
            danhSachBuoiHocLyThuyet.add(new TKB_LichHocTheoTuan(numberOfTheoryLessonsPerWeek));
        } else if (soBuoiLyThuyetMotTuan == 2) {
            int soTietLyThuyetBuoiMot = numberOfTheoryLessonsPerWeek / 2;
            danhSachBuoiHocLyThuyet.add(new TKB_LichHocTheoTuan(soTietLyThuyetBuoiMot));
            danhSachBuoiHocLyThuyet.add(new TKB_LichHocTheoTuan(numberOfTheoryLessonsPerWeek - soTietLyThuyetBuoiMot));
        }

        List<TKB_LichHocTheoTuan> danhSachBuoiHocThucHanh = new ArrayList<>();
        if (soBuoiThucHanhMotTuan == 1) {
            danhSachBuoiHocThucHanh.add(new TKB_LichHocTheoTuan(numberOfPracticeLessonsPerWeek));
        } else if (soBuoiThucHanhMotTuan == 2) {
            int soTietThucHanhBuoiMot = numberOfPracticeLessonsPerWeek / 2;
            danhSachBuoiHocThucHanh.add(new TKB_LichHocTheoTuan(soTietThucHanhBuoiMot));
            danhSachBuoiHocThucHanh.add(new TKB_LichHocTheoTuan(numberOfPracticeLessonsPerWeek - soTietThucHanhBuoiMot));
        }

        //Get list of rooms
        List<DMGiangDuong> dmGiangDuongs = new ArrayList<>();
        for (DMMonHoc_GiangDuong dm_monHoc_giangDuong :
                DMLopMonHoc.getDmMonHoc().getDm_monHoc_giangDuong()) {
            dmGiangDuongs.add(dm_monHoc_giangDuong.getDmGiangDuong());
        }

        List<DMGiangDuong> dmGiangDuongLyThuyets = new ArrayList<>();
        List<DMGiangDuong> dmGiangDuongThucHanhs = new ArrayList<>();
        for (DMGiangDuong dmGiangDuong :
                dmGiangDuongs) {
            if (dmGiangDuong.getSoLuong() >= DMLopMonHoc.getSoLuongToiDa()) {
                if (dmGiangDuong.getDmLoaiPhong().getId() == 1) {
                    dmGiangDuongLyThuyets.add(dmGiangDuong);
                } else if(dmGiangDuong.getDmLoaiPhong().getId() == 2) {
                    dmGiangDuongThucHanhs.add(dmGiangDuong);
                }
            }
        }

        if (numberOfTheoryLessons > 0 && dmGiangDuongLyThuyets.isEmpty()) {
            return "Lop hoc " + DMLopMonHoc.getId() + " co tiet ly thuyet nhung khong co phong ly thuyet";
        }

        if (numberOfPracticeLessons > 0 && dmGiangDuongThucHanhs.isEmpty()) {
            return "Lop hoc " + DMLopMonHoc.getId() + " co tiet thuc hanh nhung khong co phong thuc hanh";
        }


        Random random = new Random();
        //random weekday
        List<TKB_Thu> tkb_thus = tkb_thuService.findAll();
        tkb_thus.removeIf(tkb_thu -> "Thứ 7".equals(tkb_thu.getTen()) || "Chủ nhật".equals(tkb_thu.getTen()));

        int index;

        List<TKB_Tiet> tkb_tiets = tkb_tietService.findAll();

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                danhSachBuoiHocLyThuyet) {
            //Random thu
            index = random.nextInt(tkb_thus.size());
            tkb_lichHocTheoTuan.setTkb_thu(tkb_thus.get(index));

            //Random giangDuong
            index = random.nextInt(dmGiangDuongLyThuyets.size());
            tkb_lichHocTheoTuan.setDmGiangDuong(dmGiangDuongLyThuyets.get(index));

            //random tiet
            index = random.nextInt(2);
            if (index == 0) {
                index = random.nextInt(6 - tkb_lichHocTheoTuan.getSoTiet()) + 1;
            } else {
                index = random.nextInt(6 - tkb_lichHocTheoTuan.getSoTiet()) + 6;
            }
            tkb_lichHocTheoTuan.setTkb_tietDauTien(this.findTietByThuTu(tkb_tiets, index));
            tkb_lichHocTheoTuan.setTkb_tietCuoiCung(this.findTietByThuTu(tkb_tiets, index + tkb_lichHocTheoTuan.getSoTiet() - 1));

            //Set tuan
            tkb_lichHocTheoTuan.setTuanBatDau(DMLopMonHoc.getGioiHanTuanBatDau());
            tkb_lichHocTheoTuan.setTuanKetThuc(DMLopMonHoc.getGioiHanTuanBatDau() + theoryWeeksNeeded - 1);
        }

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                danhSachBuoiHocThucHanh) {
            //Random thu
            index = random.nextInt(tkb_thus.size());
            tkb_lichHocTheoTuan.setTkb_thu(tkb_thus.get(index));

            //Random giangDuong
            index = random.nextInt(dmGiangDuongThucHanhs.size());
            tkb_lichHocTheoTuan.setDmGiangDuong(dmGiangDuongThucHanhs.get(index));

            //random tiet
            index = random.nextInt(2);
            if (index == 0) {
                index = random.nextInt(6 - tkb_lichHocTheoTuan.getSoTiet()) + 1;
            } else {
                index = random.nextInt(6 - tkb_lichHocTheoTuan.getSoTiet()) + 6;
            }
            tkb_lichHocTheoTuan.setTkb_tietDauTien(this.findTietByThuTu(tkb_tiets, index));
            tkb_lichHocTheoTuan.setTkb_tietCuoiCung(this.findTietByThuTu(tkb_tiets, index + tkb_lichHocTheoTuan.getSoTiet() - 1));

            //Set tuan
            tkb_lichHocTheoTuan.setTuanBatDau(DMLopMonHoc.getGioiHanTuanBatDau());
            tkb_lichHocTheoTuan.setTuanKetThuc(DMLopMonHoc.getGioiHanTuanBatDau() + practiceWeeksNeeded - 1);
        }

        danhSachBuoiHocLyThuyet.addAll(danhSachBuoiHocThucHanh);
        DMLopMonHoc.setTkb_lichHocTheoTuans(new HashSet<>(danhSachBuoiHocLyThuyet));

        return "Thành công";
    }

    public TKB_Tiet findTietByThuTu(List<TKB_Tiet> tkb_tiets, int thuTu) {
        for (TKB_Tiet tkb_tiet :
                tkb_tiets) {
            if (tkb_tiet.getThuTu() == thuTu) {
                return tkb_tiet;
            }
        }

        return null;
    }


    public List<TKB_Tiet> getDanhSachTietBanCuaGiaoVien(TKB_Thu tkb_thu, DMNhanVien giaoVien, TKB_KiHoc_NamHoc tkb_kiHoc_namHoc) {
        List<TKB_Tiet> tkb_tiets = tkb_tietService.findAll();
        List<TKB_Tiet> tkb_tietsTemp = tkb_tiets;

        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = new ArrayList<>();

        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByGiaoVienIdAndNamHocKiHocId(giaoVien.getId(), tkb_kiHoc_namHoc.getId());
        for (DMLopMonHoc DMLopMonHoc :
                dmLopMonHocs) {
            for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                    DMLopMonHoc.getTkb_lichHocTheoTuans()) {
                if (tkb_lichHocTheoTuan.getTkb_thu().getId() == tkb_thu.getId()) {
                    tkb_lichHocTheoTuans.add(tkb_lichHocTheoTuan);
                }
            }
        }

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            tkb_tiets.removeIf(tkb_tiet -> tkb_tiet.getThuTu() >= tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu() && tkb_tiet.getThuTu() <= tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu());
        }
        tkb_tietsTemp.removeAll(tkb_tiets);
        return tkb_tietsTemp;
    }

}
