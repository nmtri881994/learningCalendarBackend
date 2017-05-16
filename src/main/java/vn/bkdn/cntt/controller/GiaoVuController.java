package vn.bkdn.cntt.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.common.GeneticAlgorithmUtils;
import vn.bkdn.cntt.entity.*;
import vn.bkdn.cntt.entity.geneticAlgorithm.CaThe;
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


    @Autowired
    private NamHocService namHocService;

    @Autowired
    private TKB_LichHocTheoTuanService tkb_lichHocTheoTuanService;

    @Autowired
    private GiangDuongService giangDuongService;

    @Autowired
    private TKB_ThuService tkb_thuService;

    @Autowired
    private TKB_TietService tkb_tietService;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private Khoa_KhoaHocService khoa_khoaHocService;

    @Autowired
    private KiHoc_NamHocService kiHoc_namHocService;

    @Autowired
    private KhoaService khoaService;

    @Autowired
    private RegisterTimeService registerTimeService;

    @Autowired
    private LopMonHoc_SinhVienService lopMonHoc_sinhVienService;

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private DieuKienService dieuKienService;

    private GeneticAlgorithmUtils geneticAlgorithmUtils;

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/calendar/year-not-end")
    public ResponseEntity<List<NamHoc>> getNamHocsNotEnd() {
        List<NamHoc> namHocsNotEnd = namHocService.getYearsNotEnd();
        namHocsNotEnd.sort(Comparator.comparing(NamHoc::getNgayBatDau));

        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

        List<KiHoc_NamHoc> kiHoc_namHocsCuaNamDauTien = new ArrayList<>();
        for (KiHoc_NamHoc kiHoc_namHoc :
                namHocsNotEnd.get(0).getKiHoc_namHocs()) {
            kiHoc_namHocsCuaNamDauTien.add(kiHoc_namHoc);
        }
        kiHoc_namHocsCuaNamDauTien.sort(Comparator.comparing(KiHoc_NamHoc::getNgayBatDau));

        KiHoc_NamHoc kiHoc_namHocSauCung = kiHoc_namHocsCuaNamDauTien.get(kiHoc_namHocsCuaNamDauTien.size() - 1);
        if (sqlDate.compareTo(kiHoc_namHocSauCung.getNgayBatDau()) > 0) {
            namHocsNotEnd.remove(0);
        }

        return new ResponseEntity<List<NamHoc>>(namHocsNotEnd, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/calendar/{yearId}/semester-not-end")
    public ResponseEntity<List<KiHoc>> getKiHocNotEndOfYear(@PathVariable int yearId) {
        NamHoc namHoc = namHocService.findOne(yearId);
        Set<KiHoc_NamHoc> kiHoc_namHocs = namHoc.getKiHoc_namHocs();

        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        kiHoc_namHocs.removeIf(kiHoc_namHoc -> sqlDate.compareTo(kiHoc_namHoc.getNgayBatDau()) > 0);

        List<KiHoc_NamHoc> kiHoc_namHocsArrayList = new ArrayList<>();
        for (KiHoc_NamHoc kiHoc_namHoc :
                kiHoc_namHocs) {
            kiHoc_namHocsArrayList.add(kiHoc_namHoc);
        }
        kiHoc_namHocsArrayList.sort(Comparator.comparing(KiHoc_NamHoc::getNgayBatDau));

        List<KiHoc> kiHocs = new ArrayList<>();
        for (KiHoc_NamHoc kiHoc_namHoc :
                kiHoc_namHocsArrayList) {
            kiHocs.add(kiHoc_namHoc.getKiHoc());
        }

        return new ResponseEntity<List<KiHoc>>(kiHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/edit-calendar/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}/{nganhId}")
    public ResponseEntity<TKB_LichHocTheoTuan> updateWeekCalendar(@RequestBody TKB_LichHocTheoTuan tkb_lichHocTheoTuan, @PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId, @PathVariable int nganhId) {
        Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        KiHoc_NamHoc kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        LopMonHoc currentLopMonHoc = lopMonHocService.findOne(tkb_lichHocTheoTuanService.getLopMonHocIdByTKB_LichHocTheoTuanId(tkb_lichHocTheoTuan.getId()));

        List<LopMonHoc> lopMonHocs;
        if (nganhId != 0) {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId(), nganhId);
        } else {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId());
        }

        lopMonHocs.remove(currentLopMonHoc);

        tkb_lichHocTheoTuan.setTkb_tietDauTien(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietDauTien().getId()));
        tkb_lichHocTheoTuan.setTkb_tietCuoiCung(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId()));

        boolean canUpdate = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, lopMonHocs);

        GiaoVien giaoVien = currentLopMonHoc.getGiaoVien();
        List<LopMonHoc> lopMonHocsCuaGiaoVien = lopMonHocService.findByGiaoVienIdAndNamHocKiHocId(giaoVien.getId(), kiHoc_namHoc.getId());
        lopMonHocsCuaGiaoVien.remove(currentLopMonHoc);

        boolean canUpdate2 = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, lopMonHocsCuaGiaoVien);

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
    @PostMapping(value = "/add-calendar/{lopMonHocId}/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}/{nganhId}")
    public ResponseEntity<TKB_LichHocTheoTuan> addCalendar(@RequestBody TKB_LichHocTheoTuan tkb_lichHocTheoTuan, @PathVariable int lopMonHocId, @PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId, @PathVariable int nganhId) {

        Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        KiHoc_NamHoc kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        List<LopMonHoc> lopMonHocs;
        if (nganhId != 0) {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId(), nganhId);
        } else {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId());
        }

        tkb_lichHocTheoTuan.setTkb_tietDauTien(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietDauTien().getId()));
        tkb_lichHocTheoTuan.setTkb_tietCuoiCung(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId()));

        boolean canAdd = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, lopMonHocs);

        GiaoVien giaoVien = lopMonHocService.findOne(lopMonHocId).getGiaoVien();
        List<LopMonHoc> lopMonHocsCuaGiaoVien = lopMonHocService.findByGiaoVienIdAndNamHocKiHocId(giaoVien.getId(), kiHoc_namHoc.getId());
        boolean canAdd2 = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, lopMonHocsCuaGiaoVien);

        if (canAdd && canAdd2) {
            tkb_lichHocTheoTuan.setGiangDuong(giangDuongService.findOne(tkb_lichHocTheoTuan.getGiangDuong().getId()));
            tkb_lichHocTheoTuan.setTkb_thu(tkb_thuService.findOne(tkb_lichHocTheoTuan.getTkb_thu().getId()));
            tkb_lichHocTheoTuan.setLopMonHoc(lopMonHocService.findOne(lopMonHocId));
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
    public ResponseEntity<List<Khoa>> getKhoa_KhoaHoc(@PathVariable int namHocId, @PathVariable int kiHocId) {
        KiHoc_NamHoc kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        List<Khoa> khoas = khoaService.findAll();
        for (Khoa khoa :
                khoas) {
            khoa.getKhoa_khoaHocs().removeIf(khoa_khoaHoc -> !checkKhoaFitable(khoa_khoaHoc, kiHoc_namHoc));
        }
        khoas.removeIf(khoa -> khoa.getKhoa_khoaHocs().size() == 0);

        for (Khoa khoa :
                khoas) {
            for (Khoa_KhoaHoc khoa_khoaHoc :
                    khoa.getKhoa_khoaHocs()) {
                khoa_khoaHoc.getRegisterTimes().removeIf(registerTime -> registerTime.getKiHoc_namHoc().getId() != kiHoc_namHoc.getId());
            }
        }

        khoas.sort(Comparator.comparing(Khoa::getTen));

        for (Khoa khoa :
                khoas) {
            List<Khoa_KhoaHoc> khoa_khoaHocs = new ArrayList<>(khoa.getKhoa_khoaHocs());
            khoa_khoaHocs.sort(Comparator.comparing(Khoa_KhoaHoc::getId));
            Set<Khoa_KhoaHoc> khoa_khoaHocsSet = new LinkedHashSet<>();
            for (Khoa_KhoaHoc khoa_khoaHoc :
                    khoa_khoaHocs) {
                khoa_khoaHocsSet.add(khoa_khoaHoc);
            }

            khoa.setKhoa_khoaHocs(khoa_khoaHocsSet);
        }

        return new ResponseEntity<List<Khoa>>(khoas, HttpStatus.OK);
    }

    boolean checkKhoaFitable(Khoa_KhoaHoc khoa_khoaHoc, KiHoc_NamHoc kiHoc_namHoc) {
        List<LopMonHoc> lopMonHocs = lopMonHocService.findByKhoa_KhoaHoc(khoa_khoaHoc.getId());
        lopMonHocs.removeIf(lopMonHoc -> lopMonHoc.getKiHoc_namHoc().getId() != kiHoc_namHoc.getId());
        if (lopMonHocs.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/open-registering/{registerTimeId}")
    public ResponseEntity<?> openRegistering(@PathVariable int registerTimeId) {
        registerTimeService.udpateRegistering(registerTimeId, true);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/close-registering/{registerTimeId}")
    public ResponseEntity<Object> closeRegistering(@PathVariable int registerTimeId) {
        registerTimeService.udpateRegistering(registerTimeId, false);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/generate-calendar/all-conditions")
    public ResponseEntity<List<DieuKien>> getAllDieuKien(){
        return new ResponseEntity<List<DieuKien>>(dieuKienService.findAll(), HttpStatus.OK);
    }

    private int tkbtuan_index;

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/generate-random-calendar")
    public ResponseEntity<String> generateRandomCalendarForSemester(@RequestBody Setting setting) {
        KiHoc_NamHoc kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(setting.getKyHocId(), setting.getNamHocId());
        List<LopMonHoc> lopMonHocs = lopMonHocService.findByKiHoc_NamHocId(kiHoc_namHoc.getId());
        lopMonHocs.sort(Comparator.comparing(LopMonHoc::getId));
        List<CaThe> quanThe = new ArrayList<>();
        for (int i = 0; i < numberOfInviduals; i++) {
            List<LopMonHoc> lopMonHocsTemp = new ArrayList<>();
            for (LopMonHoc lopMonHoc :
                    lopMonHocs) {
                lopMonHocsTemp.add(new LopMonHoc(lopMonHoc));
            }
            quanThe.add(new CaThe(lopMonHocsTemp));
        }
        if (this.checkLopMonHocsAllFree(lopMonHocs)) {

            //khoi tao quan the ban dau
            for (CaThe caThe : quanThe) {
                for (LopMonHoc lopMonHoc :
                        caThe.getLopMonHocList()) {
                    String result = randomCalendarForClass(lopMonHoc);
                    if (result != "Thành công") {
                        return new ResponseEntity<String>(result, HttpStatus.OK);
                    }
                }
                caThe.setDiemThichNghi(this.getDiemThichNghiCuaCaThe(caThe.getLopMonHocList(), setting));
            }
            this.danhSoTKB_TuanId(quanThe);
            for (CaThe caThe : quanThe) {
                caThe.setDiemThichNghi(this.getDiemThichNghiCuaCaThe(caThe.getLopMonHocList(), setting));
            }

            quanThe.sort(Comparator.comparing(CaThe::getDiemThichNghi));
            System.out.println("------------------------------------------Thế hệ 1------------------------------------------");
            printQuanThe(quanThe);

            if (checkSuccess(quanThe, setting.getDiemThichNghiToiUu())) {
                return new ResponseEntity<String>("Sinh thời khóa biểu tự động thành công", HttpStatus.OK);
            } else {

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
                quanThe.get(0).setDiemThichNghi(this.getDiemThichNghiCuaCaThe(quanThe.get(0).getLopMonHocList(), setting));
//                System.out.println(quanThe.get(0).getDiemThichNghi());

                List<CaThe> crossOvers = this.crossOverGeneration(parents, numberOfCrossOver);
                quanThe.get(0).setDiemThichNghi(this.getDiemThichNghiCuaCaThe(quanThe.get(0).getLopMonHocList(), setting));
//                System.out.println(quanThe.get(0).getDiemThichNghi());

                List<CaThe> mutations = this.mutateGeneration(parents, numberOfMutation, setting);
                quanThe.get(0).setDiemThichNghi(this.getDiemThichNghiCuaCaThe(quanThe.get(0).getLopMonHocList(), setting));
//                System.out.println(quanThe.get(0).getDiemThichNghi());

                quanTheTemp.addAll(parents);
                quanTheTemp.addAll(crossOvers);
                quanTheTemp.addAll(mutations);

                this.danhSoTKB_TuanId(quanTheTemp);
                for (CaThe caThe : quanTheTemp) {
                    caThe.setDiemThichNghi(this.getDiemThichNghiCuaCaThe(caThe.getLopMonHocList(), setting));
                }

                quanTheTemp.sort(Comparator.comparing(CaThe::getDiemThichNghi));
                System.out.println("------------------------------------------Thế hệ " + (i + 1) + "------------------------------------------");
                printQuanThe(quanTheTemp);

                if (checkSuccess(quanTheTemp, setting.getDiemThichNghiToiUu())) {
                    return new ResponseEntity<String>("Sinh thời khóa biểu tự động thành công", HttpStatus.OK);
                }else{
                    quanThe.clear();
                    quanThe.addAll(quanTheTemp);
                }
            }
            return new ResponseEntity<String>("Sinh thời khóa biểu tự động thất bại", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Có ít nhất một lớp đã có thời khóa biểu, hãy xóa hết thời khóa biểu để có thể tiến hành sinh thời khóa biểu tự động", HttpStatus.OK);
        }
    }

    public List<CaThe> chooseParents(List<CaThe> quanThe, int numberOfParents) {
        List<CaThe> parents = new ArrayList<>();
        for (int i = 0; i < numberOfParents; i++) {
            parents.add(new CaThe(quanThe.get(i).getLopMonHocList()));
        }
        return parents;
    }

    public List<CaThe> crossOverGeneration(List<CaThe> parents, int numberOfCrossOver) {
        List<CaThe> crossOvers = new ArrayList<>();
        Random random = new Random();
        int index1, index2;
        int crossOverPoint = (parents.get(0).getLopMonHocList().size()) / 2;
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
//            for (LopMonHoc lopMonHoc :
//                    caThe.getLopMonHocList()) {
//                System.out.println("Mon hoc: " + lopMonHoc.getMonHoc().getTen() + " - giao vien: " + lopMonHoc.getGiaoVien().getTen());
//                for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
//                        lopMonHoc.getTkb_lichHocTheoTuans()) {
//                    System.out.println(tkb_lichHocTheoTuan.getId() + " - " + tkb_lichHocTheoTuan.getTkb_thu().getTen() + " - " + tkb_lichHocTheoTuan.getGiangDuong().getTen()
//                            + " - " + tkb_lichHocTheoTuan.getTuanBatDau() + " toi " + tkb_lichHocTheoTuan.getTuanKetThuc() + " - "
//                            + tkb_lichHocTheoTuan.getTkb_tietDauTien().getTen() + " toi " + tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getTen());
//                }
//            }
//        }
    }

    public boolean checkSuccess(List<CaThe> quanThe, int diemThichNghiToiUu) {
        if (quanThe.get(0).getDiemThichNghi() <= diemThichNghiToiUu) {
            return true;
        } else {
            return false;
        }
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/delete-all-calendars/{semesterId}")
    public ResponseEntity<String> deleteAllCalendarsOfSemester(@PathVariable int semesterId) {
        List<LopMonHoc> lopMonHocs = lopMonHocService.findByKiHoc_NamHocId(semesterId);
        for (LopMonHoc lopMonHoc :
                lopMonHocs) {
            if (!lopMonHoc.getTkb_lichHocTheoTuans().isEmpty()) {
                tkb_lichHocTheoTuanService.deleteWeekCalendarOfLopMonHoc(lopMonHoc.getId());
            }
        }

        return new ResponseEntity<String>("Reset thời khóa biểu thành công", HttpStatus.OK);
    }

    public void danhSoTKB_TuanId(List<CaThe> quanThe) {
        for (CaThe caThe :
                quanThe) {
            int id = 1;
            for (LopMonHoc lopMonHoc :
                    caThe.getLopMonHocList()) {
                for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                        lopMonHoc.getTkb_lichHocTheoTuans()) {
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

    public int getDiemThichNghiCuaCaThe(List<LopMonHoc> lopMonHocs, Setting setting) {
        int diem = 0;
        for (LopMonHoc lopMonHoc :
                lopMonHocs) {
            diem += this.getDiemThichNghiCuaLopMonHoc(lopMonHoc, lopMonHocs, setting);
        }

        return diem;
    }

    public int getDiemThichNghiCuaLopMonHoc(LopMonHoc lopMonHoc, List<LopMonHoc> lopMonHocs, Setting setting) {
//        System.out.println("----**Size: " + lopMonHocs.size() + "**----");
        int diem = 0;
        if (setting.isDk1()) {
            diem += this.dk1(lopMonHoc, this.cloneListLopMonHoc(lopMonHocs), setting.getDk1Value());
        }
//        System.out.println(diem);
        if (setting.isDk2()) {
            diem += this.dk2(lopMonHoc, this.cloneListLopMonHoc(lopMonHocs), setting.getDk2Value());
        }
//        System.out.println(diem);
        if (setting.isDk3()) {
            diem += this.dk3(lopMonHoc, this.cloneListLopMonHoc(lopMonHocs), setting.getDk3Value());
        }
//        System.out.println(diem);
        if (setting.isDk4()) {
            diem += this.dk4(lopMonHoc, setting.getDk4Value());

        }
//        System.out.println(diem);
        if (setting.isDk5()) {
            diem += this.dk5(lopMonHoc, setting.getDk5Value());

        }
//        System.out.println(diem);
        if (setting.isDk6()) {
            diem += this.dk6(lopMonHoc, this.cloneListLopMonHoc(lopMonHocs), setting.getDk6Value());
        }
//        System.out.println(diem);
        if (setting.isDk7()) {
            diem += this.dk7(lopMonHoc, this.cloneListLopMonHoc(lopMonHocs), setting.getDk7Value());

        }
//        System.out.println(diem);
        if (setting.isDk8()) {
            diem += this.dk8(lopMonHoc, this.cloneListLopMonHoc(lopMonHocs), setting.getDk8Value());

        }
//        System.out.println(diem);
        if (setting.isDk9()) {
            diem += this.dk9(lopMonHoc, this.cloneListLopMonHoc(lopMonHocs), setting.getDk9Value());

        }
//        System.out.println(diem);
        return diem;
    }

    public List<LopMonHoc> cloneListLopMonHoc(List<LopMonHoc> lopMonHocs) {
        List<LopMonHoc> clone = new ArrayList<>();
        for (LopMonHoc lopMonHoc :
                lopMonHocs) {
            clone.add(new LopMonHoc(lopMonHoc));
        }

        return clone;
    }

    public int dk1(LopMonHoc lopMonHoc, List<LopMonHoc> lopMonHocs, int dkValue) {
        int diem = 0;
//        System.out.println(lopMonHocs.size());
        List<LopMonHoc> lopMonHocsCuaGiaoVien = lopMonHocs;
        lopMonHocsCuaGiaoVien.removeIf(lopMonHoc1 -> lopMonHoc1.getGiaoVien().getId() != lopMonHoc.getGiaoVien().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (LopMonHoc lopMonHocCuaGiaoVien :
                lopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(lopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = lopMonHoc.getTkb_lichHocTheoTuans();
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

    public int dk2(LopMonHoc lopMonHoc, List<LopMonHoc> lopMonHocs, int dkValue) {
        int diem = 0;
//        System.out.println(lopMonHocs.size());
        List<LopMonHoc> lopMonHocsCuaGiaoVien = lopMonHocs;
        lopMonHocsCuaGiaoVien.removeIf(lopMonHoc1 -> lopMonHoc1.getGiaoVien().getId() != lopMonHoc.getGiaoVien().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (LopMonHoc lopMonHocCuaGiaoVien :
                lopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(lopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = lopMonHoc.getTkb_lichHocTheoTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVienTemp = lichDaysCuaGiaoVien;
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> (lichDayCuaGiaoVien.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (lichDayCuaGiaoVien.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> lichDayCuaGiaoVien.getTkb_thu().getId() != tkb_lichHocTheoTuan.getTkb_thu().getId());
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> !"Dãy nhà lý thuyết".equals(lichDayCuaGiaoVien.getGiangDuong().getDayNha().getTen()));
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

    public int dk3(LopMonHoc lopMonHoc, List<LopMonHoc> lopMonHocs, int dkValue) {
        int diem = 0;
//        System.out.println(lopMonHocs.size());
        List<LopMonHoc> lopMonHocsCuaGiaoVien = lopMonHocs;
        lopMonHocsCuaGiaoVien.removeIf(lopMonHoc1 -> lopMonHoc1.getGiaoVien().getId() != lopMonHoc.getGiaoVien().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (LopMonHoc lopMonHocCuaGiaoVien :
                lopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(lopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = lopMonHoc.getTkb_lichHocTheoTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVienTemp = lichDaysCuaGiaoVien;
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> (lichDayCuaGiaoVien.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (lichDayCuaGiaoVien.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            lichDaysCuaGiaoVienTemp.removeIf(lichDayCuaGiaoVien -> !"Dãy nhà lý thuyết".equals(lichDayCuaGiaoVien.getGiangDuong().getDayNha().getTen()));
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

    public int dk4(LopMonHoc lopMonHoc, int dkValue) {
        int diem = 0;

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = lopMonHoc.getTkb_lichHocTheoTuans();
        Set<TKB_GiaoVien_NgayNghiTrongTuan> tkb_giaoVien_ngayNghiTrongTuans = lopMonHoc.getGiaoVien().getTkb_giaoVien_ngayNghiTrongTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            for (TKB_GiaoVien_NgayNghiTrongTuan tkb_giaoVien_ngayNghiTrongTuan :
                    tkb_giaoVien_ngayNghiTrongTuans) {
                if (tkb_lichHocTheoTuan.getTkb_thu().getId() == tkb_giaoVien_ngayNghiTrongTuan.getTkb_thu().getId()) {
                    diem++;
                }
            }
        }

        return diem * dkValue;
    }

    public int dk5(LopMonHoc lopMonHoc, int dkValue) {
        int diem = 0;

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = lopMonHoc.getTkb_lichHocTheoTuans();
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

    public int dk6(LopMonHoc lopMonHoc, List<LopMonHoc> lopMonHocsCuaGiaoVien, int dkValue) {
        int diem = 0;
        lopMonHocsCuaGiaoVien.removeIf(lopMonHoc1 -> lopMonHoc1.getGiaoVien().getId() != lopMonHoc.getGiaoVien().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (LopMonHoc lopMonHocCuaGiaoVien :
                lopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(lopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = lopMonHoc.getTkb_lichHocTheoTuans();
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

    public int dk7(LopMonHoc lopMonHoc, List<LopMonHoc> lopMonHocs, int dkValue) {
        int diem = 0;
        if (lopMonHoc.getNganh() == null) {
            lopMonHocs.removeIf(lopMonHoc1 -> lopMonHoc1.getKhoa_khoaHoc().getId() != lopMonHoc.getKhoa_khoaHoc().getId());
        } else {
            lopMonHocs.removeIf(lopMonHoc1 -> lopMonHoc1.getNganh() == null);
            lopMonHocs.removeIf(lopMonHoc1 -> (lopMonHoc1.getKhoa_khoaHoc().getId() != lopMonHoc.getKhoa_khoaHoc().getId()) && (lopMonHoc.getNganh().getId() != lopMonHoc1.getNganh().getId()));
//            lopMonHocs.removeIf(lopMonHoc1 -> lopMonHoc1.getKhoa_khoaHoc().getId() != lopMonHoc.getKhoa_khoaHoc().getId() && lopMonHoc1.getNganh().getId() != lopMonHoc.getNganh().getId());
        }

        List<TKB_LichHocTheoTuan> lichHocCuaKhoaKhoaHocNganh = new ArrayList<>();
        for (LopMonHoc lopMonHoc1 :
                lopMonHocs) {
            lichHocCuaKhoaKhoaHocNganh.addAll(lopMonHoc1.getTkb_lichHocTheoTuans());
        }

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                lopMonHoc.getTkb_lichHocTheoTuans()) {
            List<TKB_LichHocTheoTuan> lichHocCuaKhoaKhoaHocNganhTemp = this.cloneTKBTuan(lichHocCuaKhoaKhoaHocNganh);
            lichHocCuaKhoaKhoaHocNganhTemp.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getId() == tkb_lichHocTheoTuan.getId());
            lichHocCuaKhoaKhoaHocNganhTemp.removeIf(tkb_lichHocTheoTuan1 -> (tkb_lichHocTheoTuan1.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (tkb_lichHocTheoTuan1.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            if (this.checkTrungLichKhoaKhoaHocNganh(tkb_lichHocTheoTuan, lichHocCuaKhoaKhoaHocNganhTemp)) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public int dk8(LopMonHoc lopMonHoc, List<LopMonHoc> lopMonHocs, int dkValue) {
        int diem = 0;
        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = new ArrayList<>();
        for (LopMonHoc lopMonHoc1 :
                lopMonHocs) {
            tkb_lichHocTheoTuans.addAll(lopMonHoc1.getTkb_lichHocTheoTuans());
        }

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                lopMonHoc.getTkb_lichHocTheoTuans()) {
            List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuansTemp = this.cloneTKBTuan(tkb_lichHocTheoTuans);
            tkb_lichHocTheoTuansTemp.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getId() == tkb_lichHocTheoTuan.getId());
            tkb_lichHocTheoTuansTemp.removeIf(tkb_lichHocTheoTuan1 -> (tkb_lichHocTheoTuan1.getTuanKetThuc() < tkb_lichHocTheoTuan.getTuanBatDau()) || (tkb_lichHocTheoTuan1.getTuanBatDau() > tkb_lichHocTheoTuan.getTuanKetThuc()));
            if (checkTrungLichPhong(tkb_lichHocTheoTuan, tkb_lichHocTheoTuansTemp)) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public int dk9(LopMonHoc lopMonHoc, List<LopMonHoc> lopMonHocs, int dkValue) {
        int diem = 0;
        if (lopMonHoc.getNganh() == null) {
            lopMonHocs.removeIf(lopMonHoc1 -> lopMonHoc1.getKhoa_khoaHoc().getId() != lopMonHoc.getKhoa_khoaHoc().getId());
        } else {
            lopMonHocs.removeIf(lopMonHoc1 -> lopMonHoc1.getNganh() == null);
            lopMonHocs.removeIf(lopMonHoc1 -> (lopMonHoc1.getKhoa_khoaHoc().getId() != lopMonHoc.getKhoa_khoaHoc().getId()) && (lopMonHoc.getNganh().getId() != lopMonHoc1.getNganh().getId()));
        }
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                lopMonHoc.getTkb_lichHocTheoTuans()) {
            if (this.checkQuaNhieuBuoiHocTrongCungKhoangThoiGian(tkb_lichHocTheoTuan, lopMonHocs)) {
                diem++;
            }
        }

        return diem * dkValue;
    }

    public CaThe mutate(CaThe caTheParameter) {
        CaThe caThe = new CaThe(caTheParameter.getLopMonHocList());
        Random random = new Random();
        int caTheMutate1 = random.nextInt(caThe.getLopMonHocList().size());
        caThe.getLopMonHocList().get(caTheMutate1).setTkb_lichHocTheoTuans(null);
        this.randomCalendarForClass(caThe.getLopMonHocList().get(caTheMutate1));

        int caTheMutate2 = random.nextInt(caThe.getLopMonHocList().size());
        while (caTheMutate2 == caTheMutate1) {
            caTheMutate2 = random.nextInt(caThe.getLopMonHocList().size());
        }
        caThe.getLopMonHocList().get(caTheMutate2).setTkb_lichHocTheoTuans(null);
        this.randomCalendarForClass(caThe.getLopMonHocList().get(caTheMutate2));

        return caThe;
    }

    public CaThe crossOver1_1(CaThe caThe1, CaThe caThe2, int crossOverPoint) {
        List<LopMonHoc> lopMonHocs = new ArrayList<>();
        for (int i = 0; i < crossOverPoint; i++) {
            lopMonHocs.add(caThe1.getLopMonHocList().get(i));
        }
        for (int i = crossOverPoint; i < caThe1.getLopMonHocList().size(); i++) {
            lopMonHocs.add(caThe2.getLopMonHocList().get(i));
        }

        return new CaThe(lopMonHocs);
    }

    public CaThe crossOver1_2(CaThe caThe1, CaThe caThe2, int crossOverPoint) {
        List<LopMonHoc> lopMonHocs = new ArrayList<>();
        for (int i = 0; i < crossOverPoint; i++) {
            lopMonHocs.add(caThe2.getLopMonHocList().get(i));
        }
        for (int i = crossOverPoint; i < caThe1.getLopMonHocList().size(); i++) {
            lopMonHocs.add(caThe1.getLopMonHocList().get(i));
        }

        return new CaThe(lopMonHocs);
    }

    public CaThe crossOver2_1(CaThe caThe1, CaThe caThe2, int crossOverPoint1, int crossOverPoint2) {
        List<LopMonHoc> lopMonHocs = new ArrayList<>();
        for (int i = 0; i < crossOverPoint1; i++) {
            lopMonHocs.add(caThe1.getLopMonHocList().get(i));
        }
        for (int i = crossOverPoint1; i < crossOverPoint2; i++) {
            lopMonHocs.add(caThe2.getLopMonHocList().get(i));
        }
        for (int i = 0; i < caThe1.getLopMonHocList().size(); i++) {
            lopMonHocs.add(caThe1.getLopMonHocList().get(i));
        }

        return new CaThe(lopMonHocs);
    }

    public CaThe crossOver2_2(CaThe caThe1, CaThe caThe2, int crossOverPoint1, int crossOverPoint2) {
        List<LopMonHoc> lopMonHocs = new ArrayList<>();
        for (int i = 0; i < crossOverPoint1; i++) {
            lopMonHocs.add(caThe2.getLopMonHocList().get(i));
        }
        for (int i = crossOverPoint1; i < crossOverPoint2; i++) {
            lopMonHocs.add(caThe1.getLopMonHocList().get(i));
        }
        for (int i = 0; i < caThe1.getLopMonHocList().size(); i++) {
            lopMonHocs.add(caThe2.getLopMonHocList().get(i));
        }

        return new CaThe(lopMonHocs);
    }

    public List<TKB_LichHocTheoTuan> cloneTKBTuan(List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans) {
        List<TKB_LichHocTheoTuan> clone = new ArrayList<>();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            clone.add(new TKB_LichHocTheoTuan(tkb_lichHocTheoTuan));
        }

        return clone;
    }

    public boolean checkQuaNhieuBuoiHocTrongCungKhoangThoiGian(TKB_LichHocTheoTuan tkb_lichHocTheoTuan, List<LopMonHoc> lopMonHocs) {
        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = new ArrayList<>();
        for (LopMonHoc lopMonHoc :
                lopMonHocs) {
            tkb_lichHocTheoTuans.addAll(lopMonHoc.getTkb_lichHocTheoTuans());
        }
        tkb_lichHocTheoTuans.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getTkb_thu().getId() != tkb_lichHocTheoTuan.getTkb_thu().getId());
        tkb_lichHocTheoTuans.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getId() == tkb_lichHocTheoTuan.getId());

        int weekCalendarSameTime = 0;
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan1 :
                tkb_lichHocTheoTuans) {
            boolean condition1 = tkb_lichHocTheoTuan1.getTkb_tietCuoiCung().getThuTu() < tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu();
            boolean condition2 = tkb_lichHocTheoTuan1.getTkb_tietDauTien().getThuTu() > tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu();
            if (!(condition1 || condition2)) {
                weekCalendarSameTime++;
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
        lichHocCuaPhong.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getGiangDuong().getId() != tkb_lichHocTheoTuan.getGiangDuong().getId());
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

    public int soTietChuaCoLichConLaiCuaLopMonHoc(LopMonHoc lopMonHoc) {
        int totalLessons = lopMonHoc.getSoTietLyThuyet() + lopMonHoc.getSoTietThucHanh();
        int haveClassLessons = 0;

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                lopMonHoc.getTkb_lichHocTheoTuans()) {
            int numberOfWeeks = tkb_lichHocTheoTuan.getTuanKetThuc() - tkb_lichHocTheoTuan.getTuanBatDau() + 1;
            haveClassLessons += numberOfWeeks * (tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu() - tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu() + 1);
        }

        return totalLessons - haveClassLessons;
    }

    public boolean checkLopMonHocsAllFree(List<LopMonHoc> lopMonHocs) {
        for (LopMonHoc lopMonHoc :
                lopMonHocs) {
            if (!lopMonHoc.getTkb_lichHocTheoTuans().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String randomCalendarForClass(LopMonHoc lopMonHoc) {
        int numberOfTheoryLessons = lopMonHoc.getSoTietLyThuyet();
        int numberOfPracticeLessons = lopMonHoc.getSoTietThucHanh();

        int totalWeek = lopMonHoc.getGioiHanTuanKetThuc() - lopMonHoc.getGioiHanTuanBatDau() + 1;

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
                return "lớp môn học " + lopMonHoc.getId() + " yêu cầu nhiều hơn 2 buổi ly thuyet 1 tuần";
            }
        }

        if (numberOfPracticeLessonsPerWeek != 0) {
            if (numberOfPracticeLessonsPerWeek < 6) {
                soBuoiThucHanhMotTuan = 1;
            } else if (numberOfPracticeLessonsPerWeek < 11) {
                soBuoiThucHanhMotTuan = 2;
            } else {
                return "lớp môn học " + lopMonHoc.getId() + " yêu cầu nhiều hơn 2 buổi thuc hanh 1 tuần";
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
        List<GiangDuong> giangDuongs = new ArrayList<>();
        for (MonHoc_GiangDuong monHoc_giangDuong :
                lopMonHoc.getMonHoc().getMonHoc_giangDuongs()) {
            giangDuongs.add(monHoc_giangDuong.getGiangDuong());
        }

        List<GiangDuong> giangDuongLyThuyets = new ArrayList<>();
        List<GiangDuong> giangDuongThucHanhs = new ArrayList<>();
        for (GiangDuong giangDuong :
                giangDuongs) {
            if (giangDuong.getSoLuong() >= 1.2 * lopMonHoc.getSoLuongToiDa()) {
                if ("Dãy nhà lý thuyết".equals(giangDuong.getDayNha().getTen())) {
                    giangDuongLyThuyets.add(giangDuong);
                } else {
                    giangDuongThucHanhs.add(giangDuong);
                }
            }
        }

        if (numberOfTheoryLessons > 0 && giangDuongLyThuyets.isEmpty()) {
            return "Lop hoc " + lopMonHoc.getId() + " co tiet ly thuyet nhung khong co phong ly thuyet";
        }

        if (numberOfPracticeLessons > 0 && giangDuongThucHanhs.isEmpty()) {
            return "Lop hoc " + lopMonHoc.getId() + " co tiet thuc hanh nhung khong co phong thuc hanh";
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
            index = random.nextInt(giangDuongLyThuyets.size());
            tkb_lichHocTheoTuan.setGiangDuong(giangDuongLyThuyets.get(index));

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
            tkb_lichHocTheoTuan.setTuanBatDau(lopMonHoc.getGioiHanTuanBatDau());
            tkb_lichHocTheoTuan.setTuanKetThuc(lopMonHoc.getGioiHanTuanBatDau() + theoryWeeksNeeded - 1);
        }

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                danhSachBuoiHocThucHanh) {
            //Random thu
            index = random.nextInt(tkb_thus.size());
            tkb_lichHocTheoTuan.setTkb_thu(tkb_thus.get(index));

            //Random giangDuong
            index = random.nextInt(giangDuongThucHanhs.size());
            tkb_lichHocTheoTuan.setGiangDuong(giangDuongThucHanhs.get(index));

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
            tkb_lichHocTheoTuan.setTuanBatDau(lopMonHoc.getGioiHanTuanBatDau());
            tkb_lichHocTheoTuan.setTuanKetThuc(lopMonHoc.getGioiHanTuanBatDau() + theoryWeeksNeeded - 1);
        }

        danhSachBuoiHocLyThuyet.addAll(danhSachBuoiHocThucHanh);
        lopMonHoc.setTkb_lichHocTheoTuans(new HashSet<>(danhSachBuoiHocLyThuyet));

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


    public List<TKB_Tiet> getDanhSachTietBanCuaGiaoVien(TKB_Thu tkb_thu, GiaoVien giaoVien, KiHoc_NamHoc kiHoc_namHoc) {
        List<TKB_Tiet> tkb_tiets = tkb_tietService.findAll();
        List<TKB_Tiet> tkb_tietsTemp = tkb_tiets;

        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = new ArrayList<>();

        List<LopMonHoc> lopMonHocs = lopMonHocService.findByGiaoVienIdAndNamHocKiHocId(giaoVien.getId(), kiHoc_namHoc.getId());
        for (LopMonHoc lopMonHoc :
                lopMonHocs) {
            for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                    lopMonHoc.getTkb_lichHocTheoTuans()) {
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
