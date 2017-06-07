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

import java.text.ParseException;
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
    private ThoiGianDangKyService thoiGianDangKyService;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private Khoa_KhoaHocService khoa_khoaHocService;

    @Autowired
    private KhoaService khoaService;

    @Autowired
    private LopMonHoc_SinhVienService lopMonHoc_sinhVienService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/week/{date}")
    public ResponseEntity<MappingJacksonValue> getCalendarByWeek(@PathVariable String date) throws ParseException {
        //Get year and WeekOFYear
//        Calendar c = Calendar.getInstance();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date utilDate = dateFormat.parse(date);
//        c.setTime(utilDate);
//        int currentYear = utilDate.getYear();
//        int currentWeek = c.get(Calendar.WEEK_OF_YEAR);

        //Get all student classes' calendar
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        DMSinhVien dmSinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);
        Set<DMLopMonHoc_SinhVien> DMLopMonHoc_sinhViens = dmSinhVien.getDMLopMonHoc_sinhViens();
        List<DMLopMonHoc> dmLopMonHocs = new ArrayList<>();
        for (DMLopMonHoc_SinhVien DMLopMonHoc_sinhVien :
                DMLopMonHoc_sinhViens) {
            dmLopMonHocs.add(DMLopMonHoc_sinhVien.getDmLopMonHoc());
        }

        //Filter student classes' calendar by input date
        CalendarCommonUtils calendarCommonUtils = new CalendarCommonUtils();
        dmLopMonHocs = calendarCommonUtils.getClassCalendarByWeek(dmLopMonHocs, date);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dmLopMonHocs);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.DMLopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "giaoVien", "tkb_lichHocTheoNgays", "monHoc"));

        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/calendar/note/{lichHocTheoNgayId}")
    public String getNoteByCalendarId(@PathVariable int lichHocTheoNgayId) {
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        DMSinhVien dmSinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);

        TKB_LichHocTheoNgay_SinhVienGhiChu tkb_lichHocTheoNgay_sinhVienGhiChu = tkb_lichHocTheoNgay_sinhVienGhiChuService.findByTkbLichHocTheoNgayAndSinhVien(lichHocTheoNgayId, dmSinhVien.getId());
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
        DMSinhVien dmSinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);
        System.out.println(editStudentNote.getEditedNote() + "-" + editStudentNote.getLessonId() + "-" + dmSinhVien.getId());
        tkb_lichHocTheoNgay_sinhVienGhiChuService.editCalendarStudentNote(editStudentNote.getEditedNote(), editStudentNote.getLessonId(), dmSinhVien.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/register-time/{registerTimeId}")
    public ResponseEntity<TKB_ThoiGianDangKy> getRegisterTime(@PathVariable int registerTimeId) {
        return new ResponseEntity<TKB_ThoiGianDangKy>(thoiGianDangKyService.findOne(registerTimeId), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/register/classes/{registerTimeId}")
    public ResponseEntity<MappingJacksonValue> getCanRegisterClasses(@PathVariable int registerTimeId) {
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        DMSinhVien dmSinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);
        List<DMMonHoc> dmmonHocsSinhVienCoTheDangKy = new ArrayList<>();
        for (TKB_SinhVien_LoTrinhMonHoc tkb_sinhVien_loTrinhMonHoc :
                dmSinhVien.getTkb_sinhVien_loTrinhMonHocs()) {
            if (tkb_sinhVien_loTrinhMonHoc.isCoTheDangKy()) {
                dmmonHocsSinhVienCoTheDangKy.add(tkb_sinhVien_loTrinhMonHoc.getDmMonHoc());
            }
        }
        DMNganh dmNganh = dmSinhVien.getDmNganh();

        TKB_ThoiGianDangKy tkbThoiGianDangKy = thoiGianDangKyService.findOne(registerTimeId);
        int kiHoc_namHocId = tkbThoiGianDangKy.getTkb_kiHoc_namHoc().getId();
        int khoa_khoaHocId = tkbThoiGianDangKy.getTkb_khoa_khoaHoc().getId();

        List<DMLopMonHoc> dmLopMonHocs;

        if (dmNganh != null) {
            dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(kiHoc_namHocId, khoa_khoaHocId, dmNganh.getId());
        } else {
            dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(kiHoc_namHocId, khoa_khoaHocId);
        }

        dmLopMonHocs.removeIf(DMLopMonHoc -> !dmmonHocsSinhVienCoTheDangKy.contains(DMLopMonHoc.getDmMonHoc()));

        dmLopMonHocs.sort(Comparator.comparing(DMLopMonHoc::getId));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dmLopMonHocs);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.DMLopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "giaoVien", "khoa_khoaHoc", "monHoc", "soTietLyThuyet", "soTietThucHanh", "soLuongToiDa", "tkb_lichHocTheoTuans"));

        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/ma-khoa/{khoa_khoaHocId}")
    public String getMaKhoa(@PathVariable int khoa_khoaHocId) {
        return khoaService.findOne(khoa_khoaHocService.getKhoaId(khoa_khoaHocId)).getMa();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/register/{classId}/quantity")
    public int getClassCurrentQuantity(@PathVariable int classId) {
        return lopMonHoc_sinhVienService.getClassCurrentQuantity(classId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/register/{classId}")
    public ResponseEntity<Integer> studentRegister(@PathVariable int classId) {
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        DMLopMonHoc dmLopMonHoc = lopMonHocService.findOne(classId);
        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByMonHocIdAndKiHoc_NamHoc(dmLopMonHoc.getDmMonHoc().getId(), dmLopMonHoc.getTkb_kiHoc_namHoc().getId());
        if(dmLopMonHocs!=null){
            for (DMLopMonHoc DMLopMonHoc1:
                 dmLopMonHocs) {
                lopMonHoc_sinhVienService.studentCancelRegister(DMLopMonHoc1.getId(), sinhVienService.findByMaSinhVien(tenDangNhap).getId());
            }
        }
        return new ResponseEntity<Integer>(lopMonHoc_sinhVienService.studentRegister(classId, sinhVienService.findByMaSinhVien(tenDangNhap).getId()), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/cancel-register/{classId}")
    public ResponseEntity<Boolean> studentCancelRegister(@PathVariable int classId) {
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<Boolean>(lopMonHoc_sinhVienService.studentCancelRegister(classId, sinhVienService.findByMaSinhVien(tenDangNhap).getId()), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/check-registered/{classId}")
    public ResponseEntity<Boolean> checkRegisterd(@PathVariable int classId) {
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        if (lopMonHoc_sinhVienService.findByClassIdAndStudentId(classId, sinhVienService.findByMaSinhVien(tenDangNhap).getId()) != null) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }
}
