package vn.bkdn.cntt.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.controller.APIEntity.TermWeekTime;
import vn.bkdn.cntt.entity.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Tri on 3/24/2017.
 */

@RestController
@RequestMapping(value = "/api/calendar")
public class CalendarController {

    @Autowired
    private NamHocService namHocService;

    @Autowired
    private KhoaService khoaService;

    @Autowired
    private KiHoc_NamHocService kiHoc_namHocService;

    @Autowired
    private Khoa_KhoaHocService khoa_khoaHocService;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private TKB_ThuService tkb_thuService;

    @Autowired
    private DayNhaService dayNhaService;

    @Autowired
    private TKB_LichHocTheoTuanService tkb_lichHocTheoTuanService;

    @Autowired
    private TKB_TietService tkb_tietService;

    @Autowired
    private TKB_LichHocTheoNgayService tkb_lichHocTheoNgayService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/learning-year/{date}")
    public ResponseEntity<TKB_NamHoc> getNamHocByDate(@PathVariable String date) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = dateFormat.parse(date);

        //GetLearningYear of input date
        List<TKB_NamHoc> tkb_namHocs = namHocService.findAll();
        TKB_NamHoc tkb_namHocOfDate = new TKB_NamHoc();
        for (TKB_NamHoc tkb_namHoc :
                tkb_namHocs) {
            DateTime inputDateTime = new DateTime(utilDate);
            DateTime startLearningYearDateTime = new DateTime(dateFormat.parse(tkb_namHoc.getNgayBatDau().toString()));
            DateTime endLearningYearDateTime = new DateTime(dateFormat.parse(tkb_namHoc.getNgayKetThuc().toString()));

            if (Days.daysBetween(startLearningYearDateTime, inputDateTime).getDays() >= 0 && Days.daysBetween(inputDateTime, endLearningYearDateTime).getDays() >= 0) {
                tkb_namHocOfDate = tkb_namHoc;
                break;
            }
        }

        return new ResponseEntity<TKB_NamHoc>(tkb_namHocOfDate, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/week-number/{date}")
    public int getNumberOfWeek(@PathVariable String date) throws ParseException {
        return this.getWeekNumberOfYearByDate(date);
    }

    public int getWeekNumberOfYearByDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = dateFormat.parse(date);

        //GetLearningYear of input date
        List<TKB_NamHoc> tkb_namHocs = namHocService.findAll();
        int days = 0;
        for (TKB_NamHoc tkb_namHoc :
                tkb_namHocs) {
            DateTime inputDateTime = new DateTime(utilDate);
            DateTime startLearningYearDateTime = new DateTime(dateFormat.parse(tkb_namHoc.getNgayBatDau().toString()));
            DateTime endLearningYearDateTime = new DateTime(dateFormat.parse(tkb_namHoc.getNgayKetThuc().toString()));

            if (Days.daysBetween(startLearningYearDateTime, inputDateTime).getDays() >= 0 && Days.daysBetween(inputDateTime, endLearningYearDateTime).getDays() >= 0) {
                days = Days.daysBetween(startLearningYearDateTime, inputDateTime).getDays();
                break;
            }
        }

        return ((days / 7) + 1);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/term/week-time/{termId}/{yearId}")
    public ResponseEntity<TermWeekTime> getTermWeekTime(@PathVariable int termId, @PathVariable int yearId) throws ParseException {
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(termId, yearId);
        int startWeek = this.getWeekNumberOfYearByDate(tkb_kiHoc_namHoc.getNgayBatDau().toString());
        int endWeek = this.getWeekNumberOfYearByDate(tkb_kiHoc_namHoc.getNgayKetThuc().toString());

        return new ResponseEntity<TermWeekTime>(new TermWeekTime(startWeek, endWeek), HttpStatus.OK);
    }


//    @GetMapping(value = "/available-lessons/{roomId}/{date}")
//    public ResponseEntity<List<TKB_Tiet>> getAvailableLessonForRoomAtDate(@PathVariable int roomId, @PathVariable String date) {
//        List<TKB_Tiet> tkb_availableLessons = tkb_tietService.findAll();
//
//        List<TKB_LichHocTheoNgay> tkb_lichHocTheoNgayCuaPhongs = tkb_lichHocTheoNgayService.getLichHocOfRoomByDate(roomId, date);
//        for (TKB_LichHocTheoNgay tkb_lichHocTheoNgay:
//                tkb_lichHocTheoNgayCuaPhongs) {
//            List<TKB_Tiet> tkb_tietNotFrees = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkb_lichHocTheoNgay.getTkb_tietDauTien().getId()-1, tkb_lichHocTheoNgay.getTkb_tietCuoiCung().getId()+1);
//            tkb_availableLessons.removeAll(tkb_tietNotFrees);
//        }
//
//        return new ResponseEntity<List<TKB_Tiet>>(tkb_availableLessons, HttpStatus.OK);
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/khoas")
    public ResponseEntity<List<DMDonVi>> getAllKhoas() {
        List<DMDonVi> dMDonVis = khoaService.findAll();
        dMDonVis.sort(Comparator.comparing(DMDonVi::getTen));

        return new ResponseEntity<List<DMDonVi>>(dMDonVis, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{khoaId}/khoa-hoc-not-end/{namHocId}/{kiHocId}")
    public ResponseEntity<List<TKB_KhoaHoc>> getKhoaHocsNotEndOfKhoa(@PathVariable int khoaId, @PathVariable int namHocId, @PathVariable int kiHocId) {
        DMDonVi dMDonVi = khoaService.findOne(khoaId);
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);

        Set<TKB_Khoa_KhoaHoc> khoa_khoaHocs = dMDonVi.getTkb_khoa_KhoaHocs();
        khoa_khoaHocs.removeIf(khoa_khoaHoc -> khoa_khoaHoc.getKiKetThuc().getNgayBatDau().compareTo(tkb_kiHoc_namHoc.getNgayBatDau()) < 0);

        List<TKB_KhoaHoc> tkb_KhoaHocs = new ArrayList<>();
        for (TKB_Khoa_KhoaHoc khoa_khoaHoc :
                khoa_khoaHocs) {
            tkb_KhoaHocs.add(khoa_khoaHoc.getTkb_khoaHoc());
        }

        tkb_KhoaHocs.sort(Comparator.comparing(TKB_KhoaHoc::getNam));
        return new ResponseEntity<List<TKB_KhoaHoc>>(tkb_KhoaHocs, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/nganhs/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}")
    public ResponseEntity<List<DMNganh>> getNganhs(@PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId) {
        TKB_Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);

        List<DMNganh> dmNganhs = new ArrayList<>();
        if (khoa_khoaHoc.getKiPhanNganh().getNgayBatDau().compareTo(tkb_kiHoc_namHoc.getNgayBatDau()) <= 0) {
            for (TKB_Khoa_KhoaHoc_Nganh TKB_khoa_khoaHoc_nganh :
                    khoa_khoaHoc.getTKB_khoa_khoaHoc_nganhs()) {
                dmNganhs.add(TKB_khoa_khoaHoc_nganh.getDmNganh());
            }

            dmNganhs.sort(Comparator.comparing(DMNganh::getTen));
            return new ResponseEntity<List<DMNganh>>(dmNganhs, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<DMNganh>>(dmNganhs, HttpStatus.OK);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/lopMonHocs/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}/{nganhId}")
    public ResponseEntity<MappingJacksonValue> getdmLopMonHocs(@PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId, @PathVariable int nganhId) {
        TKB_Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        TKB_KiHoc_NamHoc tkb_kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        List<DMLopMonHoc> dmLopMonHocs;
        if (nganhId != 0) {
            dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(tkb_kiHoc_namHoc.getId(), khoa_khoaHoc.getId(), nganhId);
        } else {
            dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(tkb_kiHoc_namHoc.getId(), khoa_khoaHoc.getId());
        }

        for (DMLopMonHoc DMLopMonHoc :
                dmLopMonHocs) {
            List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = new ArrayList<>(DMLopMonHoc.getTkb_lichHocTheoTuans());
            tkb_lichHocTheoTuans.sort(Comparator.comparing(TKB_LichHocTheoTuan::getTuanBatDau));
            Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuansSet = new LinkedHashSet<>();
            for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                    tkb_lichHocTheoTuans) {
                tkb_lichHocTheoTuansSet.add(tkb_lichHocTheoTuan);
            }
            DMLopMonHoc.setTkb_lichHocTheoTuans(tkb_lichHocTheoTuansSet);
        }

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dmLopMonHocs);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.DMLopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "dmMonHoc", "dmNhanVien", "soTietLyThuyet", "soTietThucHanh", "soLuongToiDa", "soBuoiLyThuyetMotTuan", "soTietLyThuyetMotTuan", "soBuoiThucHanhMotTuan", "soTietThucHanhMotTuan", "tkb_lichHocTheoTuans"));

        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/thus")
    public ResponseEntity<List<TKB_Thu>> getAllThus() {
        List<TKB_Thu> tkb_thus = tkb_thuService.findAll();
        tkb_thus.sort(Comparator.comparing(TKB_Thu::getId));

        return new ResponseEntity<List<TKB_Thu>>(tkb_thus, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/lich-hoc-theo-tuan/{DMLopMonHocId}")
    public ResponseEntity<List<TKB_LichHocTheoTuan>> getLichHocTheoTuanOfDMLopMonHoc(@PathVariable int DMLopMonHocId) {
        DMLopMonHoc dmLopMonHoc = lopMonHocService.findOne(DMLopMonHocId);
        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuansArrayList = new ArrayList<>();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                dmLopMonHoc.getTkb_lichHocTheoTuans()) {
            tkb_lichHocTheoTuansArrayList.add(tkb_lichHocTheoTuan);
        }
        tkb_lichHocTheoTuansArrayList.sort(Comparator.comparing(TKB_LichHocTheoTuan::getId));
        return new ResponseEntity<List<TKB_LichHocTheoTuan>>(tkb_lichHocTheoTuansArrayList, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/giang-duong/{DMLopMonHocId}/{dayNhaId}")
    public ResponseEntity<List<DMGiangDuong>> getGiangDuongsOfDMLopMonHoc(@PathVariable int DMLopMonHocId, @PathVariable int dayNhaId) {
        DMLopMonHoc dmLopMonHoc = lopMonHocService.findOne(DMLopMonHocId);
        List<DMGiangDuong> dmGiangDuongs = new ArrayList<>();

        for (DMMonHoc_GiangDuong dm_monHoc_giangDuong :
                dmLopMonHoc.getDmMonHoc().getDm_monHoc_giangDuong()) {
            dmGiangDuongs.add(dm_monHoc_giangDuong.getDmGiangDuong());
        }

        dmGiangDuongs.removeIf(giangDuong -> giangDuong.getSoLuong() < 0.7 * dmLopMonHoc.getSoLuongToiDa());
        dmGiangDuongs.removeIf(giangDuong -> giangDuong.getDmLoaiPhong().getId() != dayNhaId);
        dmGiangDuongs.sort(Comparator.comparing(DMGiangDuong::getMaGiangDuong));

        return new ResponseEntity<List<DMGiangDuong>>(dmGiangDuongs, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/loai-lop/{DMLopMonHocId}")
    public int getLoaiLop(@PathVariable int DMLopMonHocId) {
        DMLopMonHoc dmLopMonHoc = lopMonHocService.findOne(DMLopMonHocId);
        if (dmLopMonHoc.getSoTietLyThuyet() > 0 && dmLopMonHoc.getSoTietThucHanh() == 0) {
            return 1;
        } else if (dmLopMonHoc.getSoTietLyThuyet() == 0 && dmLopMonHoc.getSoTietThucHanh() > 0) {
            return 2;
        } else {
            return 3;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/day-nha")
    public ResponseEntity<List<DMLoaiPhong>> getAllDayNha() {
        List<DMLoaiPhong> dmLoaiPhongs = dayNhaService.findAll();
        dmLoaiPhongs.sort(Comparator.comparing(DMLoaiPhong::getId));

        return new ResponseEntity<List<DMLoaiPhong>>(dmLoaiPhongs, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/tkb-tuan/tiets-free/{DMLopMonHocId}/{tkbTuanId}/{thuId}/{giangDuongId}/{tuanBatDau}/{tuanKetThuc}")
    public ResponseEntity<List<TKB_Tiet>> getAvailableLessons(@PathVariable int DMLopMonHocId, @PathVariable int tkbTuanId, @PathVariable int thuId, @PathVariable int giangDuongId, @PathVariable int tuanBatDau, @PathVariable int tuanKetThuc) {
        List<TKB_Tiet> tkb_tiets = tkb_tietService.findAll();

        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = tkb_lichHocTheoTuanService.findLichHocTheoTuanByThuIdAndGiangDuongId(thuId, giangDuongId);
        DMLopMonHoc dmLopMonHoc = lopMonHocService.findOne(DMLopMonHocId);
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                dmLopMonHoc.getTkb_lichHocTheoTuans()) {
            if (tkb_lichHocTheoTuan.getTkb_thu().getId() == thuId) {
                tkb_lichHocTheoTuans.add(tkb_lichHocTheoTuan);
            }
        }

        DMNhanVien giaoVien = dmLopMonHoc.getDmNhanVien();
        Set<DMLopMonHoc> dmLopMonHocs = giaoVien.getDmLopMonHocs();
        List<TKB_LichHocTheoTuan> lichHocTheoTuansCuaGiaoVien = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHoc1 :
                dmLopMonHocs) {
            lichHocTheoTuansCuaGiaoVien.addAll(DMLopMonHoc1.getTkb_lichHocTheoTuans());
        }
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                lichHocTheoTuansCuaGiaoVien) {
            if (tkb_lichHocTheoTuan.getTkb_thu().getId() == thuId) {
                tkb_lichHocTheoTuans.add(tkb_lichHocTheoTuan);
            }
        }

        tkb_lichHocTheoTuans.removeIf(tkb_lichHocTheoTuan -> this.checkInvalidTuan(tkb_lichHocTheoTuan, tuanBatDau, tuanKetThuc));

        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                tkb_lichHocTheoTuans) {
            List<TKB_Tiet> tietsNotFree = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkb_lichHocTheoTuan.getTkb_tietDauTien().getId() - 1, tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId() + 1);
            tkb_tiets.removeAll(tietsNotFree);
        }


        if (tkbTuanId != 0) {
            TKB_LichHocTheoTuan tkb_lichHocTheoTuan = tkb_lichHocTheoTuanService.findOne(tkbTuanId);
            System.out.println(tuanBatDau + "-" + tkb_lichHocTheoTuan.getTuanBatDau() + "-" + tkb_lichHocTheoTuan.getTuanKetThuc());
            if (tuanBatDau >= tkb_lichHocTheoTuan.getTuanBatDau() && tuanBatDau <= tkb_lichHocTheoTuan.getTuanKetThuc() && thuId == tkb_lichHocTheoTuan.getTkb_thu().getId()) {
                System.out.println("11111111");
                List<TKB_Tiet> tietsCuaTKBHienTai = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkb_lichHocTheoTuan.getTkb_tietDauTien().getId() - 1, tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId() + 1);
                for (TKB_Tiet tkb_tiet :
                        tietsCuaTKBHienTai) {
                    if (!tkb_tiets.contains(tkb_tiet)) {
                        tkb_tiets.add(tkb_tiet);
                    }
                }
            }
        }

        tkb_tiets.sort(Comparator.comparing(TKB_Tiet::getThuTu));

        return new ResponseEntity<List<TKB_Tiet>>(tkb_tiets, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tkb-tuan/find-one/{tkbTuanId}")
    public ResponseEntity<TKB_LichHocTheoTuan> findOne(@PathVariable int tkbTuanId) {
        return new ResponseEntity<TKB_LichHocTheoTuan>(tkb_lichHocTheoTuanService.findOne(tkbTuanId), HttpStatus.OK);
    }

    boolean checkInvalidTuan(TKB_LichHocTheoTuan tkb_lichHocTheoTuan, int tuanBatDau, int tuanKetThuc) {
        boolean condition1 = tkb_lichHocTheoTuan.getTuanKetThuc() < tuanBatDau;
        boolean condition2 = tkb_lichHocTheoTuan.getTuanBatDau() > tuanKetThuc;
        if (condition1 || condition2) {
            return true;
        } else {
            return false;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tkb/{classId}")
    public ResponseEntity<MappingJacksonValue> findClass(@PathVariable int classId){
        DMLopMonHoc dmLopMonHoc = lopMonHocService.findOne(classId);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dmLopMonHoc);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.DMLopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "tkb_lichHocTheoNgays", "soTietLyThuyet", "soTietThucHanh"));
        mappingJacksonValue.setFilters(filterProvider);
        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tkb/lesson/{lessonId}")
    public ResponseEntity<MappingJacksonValue> findClassByLesson(@PathVariable int lessonId){
        int classId = tkb_lichHocTheoNgayService.getClassId(lessonId);

        DMLopMonHoc dmLopMonHoc = lopMonHocService.findOne(classId);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dmLopMonHoc);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.DMLopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "tkb_lichHocTheoNgays", "soTietLyThuyet", "soTietThucHanh"));
        mappingJacksonValue.setFilters(filterProvider);
        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tkb/first-date-of-week/{yearId}/{week}")
    public String getFirstDateOfWeek(@PathVariable int yearId,@PathVariable int week){
        TKB_NamHoc tkb_namHoc = namHocService.findOne(yearId);
        Date learningYearStartDate = tkb_namHoc.getNgayBatDau();

        int days = 7*(week-1);

        Calendar c = Calendar.getInstance(Locale.GERMAN);
        c.setTime(learningYearStartDate);
        c.add(Calendar.DATE, days);
        if (c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 2) {
            c.add(Calendar.DATE, 2 - c.get(Calendar.DAY_OF_WEEK));
        }

        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            c.add(Calendar.DATE, 2 - c.get(1));
        }

        return new java.sql.Date(c.getTime().getTime()).toString();
    }
}
