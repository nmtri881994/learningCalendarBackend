package vn.bkdn.cntt.controller;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.common.GeneticAlgorithmUtils;
import vn.bkdn.cntt.entity.*;

import java.util.*;

/**
 * Created by Tri on 4/4/2017.
 */

@RestController
@RequestMapping(value = "api/giaovu")
public class GiaoVuController {

    private int numberOfInviduals = 20;
    private int numberOfMaximumIterations = 100;
    private int bestAdaptationPoint = 20;

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
    @GetMapping(value = "/generate-random-calendar/{semesterId}")
    public ResponseEntity<String> generateRandomCalendarForSemester(@PathVariable int semesterId) {
        List<LopMonHoc> lopMonHocs = lopMonHocService.findByKiHoc_NamHocId(semesterId);
        lopMonHocs.sort(Comparator.comparing(LopMonHoc::getId));
        List<List<LopMonHoc>> quanThe = new ArrayList<>();
        for (int i = 0; i < numberOfInviduals; i++) {
            List<LopMonHoc> lopMonHocsTemp = new ArrayList<>();
            for (LopMonHoc lopMonHoc :
                    lopMonHocs) {
                lopMonHocsTemp.add(new LopMonHoc(lopMonHoc));
            }
            quanThe.add(lopMonHocsTemp);
        }
        if (this.checkLopMonHocsAllFree(lopMonHocs)) {

            //khoi tao quan the ban dau
            for (List<LopMonHoc> lopMonHocList :
                    quanThe) {
                for (LopMonHoc lopMonHoc :
                        lopMonHocList) {
                    String result = randomCalendarForClass(lopMonHoc);
                    if (result != "Thành công") {
                        return new ResponseEntity<String>(result, HttpStatus.OK);
                    }
                }
            }

            //Tien hanh tien hoa
//            for (int i = 1; i < this.numberOfMaximumIterations; i++) {
//
//            }

            int i = 1;
            for (List<LopMonHoc> lopMonHocList :
                    quanThe) {
                System.out.println("----------Ca the " + i + ": " + this.getDiemThichNghiCuaCaThe(lopMonHocList) + "---------");
                i++;
                for (LopMonHoc lopMonHoc :
                        lopMonHocList) {
                    System.out.println("lop mon hoc " + lopMonHoc.getId() + " - " + lopMonHoc.getMonHoc().getTen());
                    for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                            lopMonHoc.getTkb_lichHocTheoTuans()) {
                        System.out.println(tkb_lichHocTheoTuan.getTkb_thu().getTen() + " - " + tkb_lichHocTheoTuan.getGiangDuong().getTen()
                                + " - " + tkb_lichHocTheoTuan.getTuanBatDau() + " toi " + tkb_lichHocTheoTuan.getTuanKetThuc() + " - "
                                + tkb_lichHocTheoTuan.getTkb_tietDauTien().getTen() + " toi " + tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getTen());
                    }
                }
            }


            return new ResponseEntity<String>("Sinh thời khóa biểu tự động thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Có ít nhất một lớp đã có thời khóa biểu, hãy xóa hết thời khóa biểu để có thể tiến hành sinh thời khóa biểu tự động", HttpStatus.OK);
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

    public int getDiemThichNghiCuaCaThe(List<LopMonHoc> lopMonHocs) {
        int diem = 0;
        for (LopMonHoc lopMonHoc :
                lopMonHocs) {
            diem += this.getDiemThichNghiCuaLopMonHoc(lopMonHoc);
        }

        return diem;
    }

    public int getDiemThichNghiCuaLopMonHoc(LopMonHoc lopMonHoc) {
        int diem = 0;
        diem += this.dk1(lopMonHoc);
        System.out.println(diem);
        diem += this.dk2(lopMonHoc);
        System.out.println(diem);
        diem += this.dk3(lopMonHoc);
        System.out.println(diem);
        diem += this.dk4(lopMonHoc);
        System.out.println(diem);
        diem += this.dk5(lopMonHoc);
        System.out.println(diem);
        return diem;
    }

    public int dk1(LopMonHoc lopMonHoc) {
        int diem = 0;
        List<LopMonHoc> lopMonHocsCuaGiaoVien = lopMonHocService.findByGiaoVienIdAndNamHocKiHocId(lopMonHoc.getGiaoVien().getId(), lopMonHoc.getKiHoc_namHoc().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (LopMonHoc lopMonHocCuaGiaoVien :
                lopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(lopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = lopMonHoc.getTkb_lichHocTheoTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVienTemp = lichDaysCuaGiaoVien;
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

        return diem * 5;
    }

    public int dk2(LopMonHoc lopMonHoc) {
        int diem = 0;
        List<LopMonHoc> lopMonHocsCuaGiaoVien = lopMonHocService.findByGiaoVienIdAndNamHocKiHocId(lopMonHoc.getGiaoVien().getId(), lopMonHoc.getKiHoc_namHoc().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (LopMonHoc lopMonHocCuaGiaoVien :
                lopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(lopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = lopMonHoc.getTkb_lichHocTheoTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVienTemp = lichDaysCuaGiaoVien;
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

        return diem * 5;
    }

    public int dk3(LopMonHoc lopMonHoc) {
        int diem = 0;
        List<LopMonHoc> lopMonHocsCuaGiaoVien = lopMonHocService.findByGiaoVienIdAndNamHocKiHocId(lopMonHoc.getGiaoVien().getId(), lopMonHoc.getKiHoc_namHoc().getId());
        List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVien = new ArrayList<>();
        for (LopMonHoc lopMonHocCuaGiaoVien :
                lopMonHocsCuaGiaoVien) {
            lichDaysCuaGiaoVien.addAll(new ArrayList<TKB_LichHocTheoTuan>(lopMonHocCuaGiaoVien.getTkb_lichHocTheoTuans()));
        }

        Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = lopMonHoc.getTkb_lichHocTheoTuans();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_LichHocTheoTuan> lichDaysCuaGiaoVienTemp = lichDaysCuaGiaoVien;
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

        return diem * 5;
    }

    public int dk4(LopMonHoc lopMonHoc) {
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

        return diem * 10;
    }

    public int dk5(LopMonHoc lopMonHoc) {
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

        return diem * 15;
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
            index = random.nextInt(1);
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
            index = random.nextInt(1);
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
