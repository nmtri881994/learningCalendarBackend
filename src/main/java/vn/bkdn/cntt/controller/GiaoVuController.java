package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.common.GeneticAlgorithmUtils;
import vn.bkdn.cntt.entity.*;
import vn.bkdn.cntt.entity.geneticAlgorithm.TKB_Tuan;

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
        if (this.checkLopMonHocsAllFree(lopMonHocs)) {

//            for (int i = 1; i < this.numberOfMaximumIterations; i++) {
//
//            }
            for (LopMonHoc lopMonHoc :
                    lopMonHocs) {
                this.randomCalendarForClass(lopMonHoc);
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
                return "Có lớp môn học yêu cầu nhiều hơn 2 buổi 1 tuần";
            }
        }

        if (numberOfPracticeLessonsPerWeek != 0) {
            if (numberOfPracticeLessonsPerWeek < 6) {
                soBuoiThucHanhMotTuan = 1;
            } else if (numberOfPracticeLessonsPerWeek < 11) {
                soBuoiThucHanhMotTuan = 2;
            } else {
                return "Có lớp môn học yêu cầu nhiều hơn 2 buổi 1 tuần";
            }
        }

        List<TKB_Tuan> danhSachBuoiHocLyThuyet = new ArrayList<>();
        if (soBuoiLyThuyetMotTuan == 1) {
            danhSachBuoiHocLyThuyet.add(new TKB_Tuan(numberOfTheoryLessonsPerWeek));
        } else if (soBuoiLyThuyetMotTuan == 2) {
            int soTietLyThuyetBuoiMot = numberOfTheoryLessonsPerWeek / 2;
            danhSachBuoiHocLyThuyet.add(new TKB_Tuan(soTietLyThuyetBuoiMot));
            danhSachBuoiHocLyThuyet.add(new TKB_Tuan(numberOfTheoryLessonsPerWeek - soTietLyThuyetBuoiMot));
        }

        List<TKB_Tuan> danhSachBuoiHocThucHanh = new ArrayList<>();
        if (soBuoiThucHanhMotTuan == 1) {
            danhSachBuoiHocThucHanh.add(new TKB_Tuan(numberOfPracticeLessonsPerWeek));
        } else if (soBuoiThucHanhMotTuan == 2) {
            int soTietThucHanhBuoiMot = numberOfPracticeLessonsPerWeek / 2;
            danhSachBuoiHocThucHanh.add(new TKB_Tuan(soTietThucHanhBuoiMot));
            danhSachBuoiHocThucHanh.add(new TKB_Tuan(numberOfPracticeLessonsPerWeek - soTietThucHanhBuoiMot));
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
            if ("Dãy nhà lý thuyết".equals(giangDuong.getDayNha().getTen())) {
                giangDuongLyThuyets.add(giangDuong);
            } else {
                giangDuongThucHanhs.add(giangDuong);
            }
        }


        Random random = new Random();
        //random weekday
        List<TKB_Thu> tkb_thus = tkb_thuService.findAll();
        tkb_thus.removeIf(tkb_thu -> "Thứ 7".equals(tkb_thu.getTen()) || "Chủ nhật".equals(tkb_thu.getTen()));

        for (TKB_Thu tkb_thu :
                tkb_thus) {

        }

        return "Thành công";
    }

    public boolean kiemTraThuCoTheXepLich(TKB_Thu tkb_thu, List<GiangDuong> giangDuongs, int soTietToiDa, LopMonHoc lopMonHoc) {
        List<TKB_Tiet> tkb_tiets = tkb_tietService.findAll();

        List<TKB_Tiet> danhSachTietBanCuaGiaoVien = this.getDanhSachTietBanCuaGiaoVien(tkb_thu, lopMonHoc.getGiaoVien(), lopMonHoc.getKiHoc_namHoc());
        tkb_tiets.removeAll(danhSachTietBanCuaGiaoVien);
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
