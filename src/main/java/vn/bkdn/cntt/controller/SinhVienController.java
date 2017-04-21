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
import vn.bkdn.cntt.controller.APIEntity.EditStudentNote;
import vn.bkdn.cntt.entity.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Tri on 3/15/2017.
 */

@RestController
@RequestMapping(value = "/api/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private TaiKhoanHeThongService taiKhoanHeThongService;

    @Autowired
    private TKB_LichHocTheoNgay_SinhVienGhiChuService tkb_lichHocTheoNgay_sinhVienGhiChuService;

    @Autowired
    private TKB_LichHocTheoNgayService tkb_lichHocTheoNgayService;

    @Autowired
    private RegisterTimeService registerTimeService;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private Khoa_KhoaHocService khoa_khoaHocService;

    @Autowired
    private KhoaService khoaService;

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

        //Get all student classes' calendar
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        SinhVien sinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);
        Set<LopMonHoc_SinhVien> lopMonHoc_sinhViens = sinhVien.getLopMonHoc_sinhViens();
        List<LopMonHoc> lopMonHocs = new ArrayList<>();
        for (LopMonHoc_SinhVien lopMonHoc_sinhVien :
                lopMonHoc_sinhViens) {
            lopMonHocs.add(lopMonHoc_sinhVien.getLopMonHoc());
        }

        //Filter student classes' calendar by input date
        CalendarCommonUtils calendarCommonUtils = new CalendarCommonUtils();
        lopMonHocs = calendarCommonUtils.getClassCalendarByWeek(lopMonHocs, currentYear, currentWeek);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(lopMonHocs);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.LopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "giaoVien", "tkb_lichHocTheoNgays", "monHoc"));

        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/calendar/note/{lichHocTheoNgayId}")
    public String getNoteByCalendarId(@PathVariable int lichHocTheoNgayId) {
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        SinhVien sinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);

        TKB_LichHocTheoNgay_SinhVienGhiChu tkb_lichHocTheoNgay_sinhVienGhiChu = tkb_lichHocTheoNgay_sinhVienGhiChuService.findByTkbLichHocTheoNgayAndSinhVien(lichHocTheoNgayId, sinhVien.getId());
        if (tkb_lichHocTheoNgay_sinhVienGhiChu != null) {
            return tkb_lichHocTheoNgay_sinhVienGhiChu.getSinhVienGhiChu();
        } else {
            return "";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/calendar/note/edit")
    public void editCalendarNote(@RequestBody EditStudentNote editStudentNote) {
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        SinhVien sinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);
        System.out.println(editStudentNote.getEditedNote() + "-" + editStudentNote.getLessonId() + "-" + sinhVien.getId());
        tkb_lichHocTheoNgay_sinhVienGhiChuService.editCalendarStudentNote(editStudentNote.getEditedNote(), editStudentNote.getLessonId(), sinhVien.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/register-time/{registerTimeId}")
    public ResponseEntity<RegisterTime> getRegisterTime(@PathVariable int registerTimeId) {
        return new ResponseEntity<RegisterTime>(registerTimeService.findOne(registerTimeId), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/register/classes/{registerTimeId}")
    public ResponseEntity<MappingJacksonValue> getCanRegisterClasses(@PathVariable int registerTimeId) {
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        SinhVien sinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);
        List<MonHoc> monHocsSinhVienCoTheDangKy = new ArrayList<>();
        for (SinhVien_LoTrinhMonHoc sinhVien_loTrinhMonHoc :
             sinhVien.getSinhVien_loTrinhMonHocs()) {
            if(sinhVien_loTrinhMonHoc.isCoTheDangKy()){
                monHocsSinhVienCoTheDangKy.add(sinhVien_loTrinhMonHoc.getMonHoc());
            }
        }
        Nganh nganh = sinhVien.getNganh();

        RegisterTime registerTime = registerTimeService.findOne(registerTimeId);
        int kiHoc_namHocId = registerTime.getKiHoc_namHoc().getId();
        int khoa_khoaHocId = registerTime.getKhoa_khoaHoc().getId();

        List<LopMonHoc> lopMonHocs;

        if (nganh != null) {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(kiHoc_namHocId, khoa_khoaHocId, nganh.getId());
        } else {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(kiHoc_namHocId, khoa_khoaHocId);
        }

        lopMonHocs.removeIf(lopMonHoc -> !monHocsSinhVienCoTheDangKy.contains(lopMonHoc.getMonHoc()));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(lopMonHocs);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.LopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "giaoVien", "khoa_khoaHoc", "monHoc"));

        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/ma-khoa/{khoa_khoaHocId}")
    public String getMaKhoa(@PathVariable int khoa_khoaHocId){
        return(khoaService.findOne(khoa_khoaHocService.getKhoaId(khoa_khoaHocId)).getMaKhoa());
    }
}
