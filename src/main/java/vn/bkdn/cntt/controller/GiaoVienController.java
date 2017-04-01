package vn.bkdn.cntt.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.common.CalendarCommonUtils;
import vn.bkdn.cntt.entity.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Tri on 3/15/2017.
 */

@RestController
@RequestMapping(value = "/api/giaovien")
public class GiaoVienController {

    @Autowired
    private GiaoVienService giaoVienService;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private TKB_LichHocTheoNgayService tkb_lichHocTheoNgayService;

    @Autowired
    private TKB_TietService tkb_tietService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/week/{date}")
    public ResponseEntity<MappingJacksonValue> getCalendarByWeek(@PathVariable String date) throws ParseException {
        //Get year and WeekOFYear
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = dateFormat.parse(date);
        c.setTime(utilDate);
        int currentYear = utilDate.getYear();
        int currentWeek = c.get(Calendar.WEEK_OF_YEAR);

        //Get teacher who requests
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        GiaoVien giaoVien = giaoVienService.findByMaGiaoVien(tenDangNhap);
//
        List<LopMonHoc> lopMonHocs = lopMonHocService.findByGiaoVien(giaoVien);

        //Filter student classes' calendar by input date
        CalendarCommonUtils calendarCommonUtils = new CalendarCommonUtils();
        lopMonHocs = calendarCommonUtils.getClassCalendarByWeek(lopMonHocs, currentYear, currentWeek);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(lopMonHocs);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.LopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "tkb_lichHocTheoNgays", "monHoc"));

        mappingJacksonValue.setFilters(filterProvider);


        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/lesson/{lessonId}")
    public ResponseEntity<TKB_LichHocTheoNgay> getLessonDetail(@PathVariable int lessonId) {
        return new ResponseEntity<TKB_LichHocTheoNgay>(this.tkb_lichHocTheoNgayService.findOne(lessonId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIANGVIEN')")
    @PostMapping(value = "/edit/lesson")
    public ResponseEntity<?> editLesson(@RequestBody TKB_LichHocTheoNgay tkb_lichHocTheoNgay) {
        return new ResponseEntity<Object>(this.tkb_lichHocTheoNgayService.updateBuoiHoc(tkb_lichHocTheoNgay), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('GIANGVIEN')")
    @GetMapping(value = "/available-lessons/{lessonId}/{roomId}/{date}")
    public ResponseEntity<List<TKB_Tiet>> getAvailableLessonForRoomAtDate(@PathVariable int lessonId, @PathVariable int roomId, @PathVariable String date) throws ParseException {
        List<TKB_Tiet> tkb_availableLessons = tkb_tietService.findAll();

        //Loc lich cua phong theo ngay
        List<TKB_LichHocTheoNgay> tkb_lichHocTheoNgayCuaPhongs = tkb_lichHocTheoNgayService.getLichHocOfRoomByDate(roomId, date);
        for (TKB_LichHocTheoNgay tkb_lichHocTheoNgay :
                tkb_lichHocTheoNgayCuaPhongs) {
            List<TKB_Tiet> tkb_tietNotFrees = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkb_lichHocTheoNgay.getTkb_tietDauTien().getId() - 1, tkb_lichHocTheoNgay.getTkb_tietCuoiCung().getId() + 1);
            tkb_availableLessons.removeAll(tkb_tietNotFrees);
        }

        //Loc lich cua giao vien theo ngay
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        GiaoVien giaoVien = giaoVienService.findByMaGiaoVien(tenDangNhap);

        List<LopMonHoc> lopMonHocs = lopMonHocService.findByGiaoVien(giaoVien);

        //TODO
        //Can optimize the after code by using a method of TKB_LichHocTheoNgayRepository: findByLopMonHocAndNgay
        List<TKB_LichHocTheoNgay> tkb_lichHocTheoNgayCuaGiaoVien = new ArrayList<>();
        for (LopMonHoc lopMonHoc :
                lopMonHocs) {
            Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays = lopMonHoc.getTkb_lichHocTheoNgays();
            tkb_lichHocTheoNgayCuaGiaoVien.addAll(tkb_lichHocTheoNgays);
        }
        tkb_lichHocTheoNgayCuaGiaoVien.removeIf(tkb_lichHocTheoNgay -> !date.equals(tkb_lichHocTheoNgay.getNgay().toString()));


        for (TKB_LichHocTheoNgay tkb_lichHocTheoNgay :
                tkb_lichHocTheoNgayCuaGiaoVien) {
            List<TKB_Tiet> tkb_tietNotFrees = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkb_lichHocTheoNgay.getTkb_tietDauTien().getId() - 1, tkb_lichHocTheoNgay.getTkb_tietCuoiCung().getId() + 1);
            tkb_availableLessons.removeAll(tkb_tietNotFrees);
        }

        //Loc lich cua sinh vien cua tiet hoc dang chinh sua theo ngay
        TKB_LichHocTheoNgay tkbLichHocTheoNgay = tkb_lichHocTheoNgayService.findOne(lessonId);
        LopMonHoc lopMonHoc = tkbLichHocTheoNgay.getLopMonHoc();
        List<SinhVien> sinhVienCuaTietHocs = new ArrayList<>();
        for (LopMonHoc_SinhVien lopMonHoc_sinhVien:
             lopMonHoc.getLopMonHoc_sinhViens()) {
            sinhVienCuaTietHocs.add(lopMonHoc_sinhVien.getSinhVien());
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = dateFormat.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        for (SinhVien sinhVien:
             sinhVienCuaTietHocs) {
            Set<LopMonHoc_SinhVien> lopMonHoc_sinhViens = sinhVien.getLopMonHoc_sinhViens();
            List<LopMonHoc> lopMonHocCuaSinhViens = new ArrayList<>();
            for (LopMonHoc_SinhVien lopMonHoc_sinhVien:
                 lopMonHoc_sinhViens) {
                lopMonHocCuaSinhViens.add(lopMonHoc_sinhVien.getLopMonHoc());
            }
            for (LopMonHoc lopMonHocCuaSinhVien:
                 lopMonHocCuaSinhViens) {
                List<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays = tkb_lichHocTheoNgayService.findByLopMonHocAndNgay(lopMonHoc, sqlDate);
                for (TKB_LichHocTheoNgay tkbLichHocTheoNgay1:
                        tkb_lichHocTheoNgays) {
                    List<TKB_Tiet> tkb_tietNotFrees = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkbLichHocTheoNgay1.getTkb_tietDauTien().getId() - 1, tkbLichHocTheoNgay1.getTkb_tietCuoiCung().getId() + 1);
                    tkb_availableLessons.removeAll(tkb_tietNotFrees);
                    if(tkb_availableLessons.isEmpty()){
                        break;
                    }
                }
            }
        }
        System.out.println("aa"+tkb_availableLessons.size());
        List<TKB_Tiet> tkb_tietHienTaiCuaLessons = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkbLichHocTheoNgay.getTkb_tietDauTien().getId() - 1, tkbLichHocTheoNgay.getTkb_tietCuoiCung().getId() + 1);
        tkb_availableLessons.addAll(tkb_tietHienTaiCuaLessons);
        System.out.println("aa"+tkb_availableLessons.size());
        return new ResponseEntity<List<TKB_Tiet>>(tkb_availableLessons, HttpStatus.OK);
    }
}
