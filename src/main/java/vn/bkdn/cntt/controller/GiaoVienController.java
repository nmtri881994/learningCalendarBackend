package vn.bkdn.cntt.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.common.CalendarCommonUtils;
import vn.bkdn.cntt.controller.APIEntity.SemesterYear;
import vn.bkdn.cntt.controller.APIEntity.SimpleDiemDanh;
import vn.bkdn.cntt.controller.APIEntity.SimpleSinhVien;
import vn.bkdn.cntt.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    private DMNhanVienService nhanVienService;

    @Autowired
    private DMLopMonHocService lopMonHocService;

    @Autowired
    private TKB_LichHocTheoNgayService tkb_lichHocTheoNgayService;

    @Autowired
    private TKB_TietService tkb_tietService;

    @Autowired
    private TKB_LichNghiCuaNhanVienService tkb_lichNghiCuaNhanVienService;

    @Autowired
    private TKB_LichNghiCuaTruongService tkb_lichNghiCuaTruongService;

    @Autowired
    private DMLopMonHoc_SinhVienService dmLopMonHoc_sinhVienService;

    @Autowired
    private DMSinhVienService sinhVienService;

    @Autowired
    private TKB_LichHocTheoNgay_DiemDanhService tkb_lichHocTheoNgay_diemDanhService;

    @Autowired
    private TKB_KiHoc_NamHocService kiHoc_namHocService;

    @Autowired
    private ExcelService excelService;

    @Autowired
    public HttpServletResponse httpServletResponse;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/week/{date}")
    public ResponseEntity<MappingJacksonValue> getCalendarByWeek(@PathVariable String date) throws ParseException {
        //Get year and WeekOFYear
//        Calendar c = Calendar.getInstance(Locale.GERMAN);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date utilDate = dateFormat.parse(date);
//        c.setTime(utilDate);
//        int currentYear = utilDate.getYear();
//        int currentWeek = c.get(Calendar.WEEK_OF_YEAR);

        //Get teacher who requests
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        DMNhanVien giaoVien = nhanVienService.findByMaNhanVien(tenDangNhap);

        //TODO Có được date rồi thì tìm kì học - năm học của date đó rồi find lớp môn học theo kì học - năm học đó
        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByGiaoVien(giaoVien);

        //Filter student classes' calendar by input date
        CalendarCommonUtils calendarCommonUtils = new CalendarCommonUtils();
        dmLopMonHocs = calendarCommonUtils.getClassCalendarByWeek(dmLopMonHocs, date);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dmLopMonHocs);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.DMLopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "tkb_lichHocTheoNgays", "dmMonHoc"));

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
    @GetMapping(value = "/calendar/students/{lessonId}")
    public ResponseEntity<List<SimpleSinhVien>> getSinhViensOfLopMonHoc(@PathVariable int lessonId) {
        System.out.println("!111111111111, " + lessonId);
        int classId = tkb_lichHocTheoNgayService.getClassId(lessonId);
        DMLopMonHoc dmLopMonHoc = lopMonHocService.findOne(classId);

        List<Integer> sinhVienIds = dmLopMonHoc_sinhVienService.getSinhVienIdsOfLopMonHoc(dmLopMonHoc.getId());
        List<SimpleSinhVien> simpleSinhViens = new ArrayList<>();
        DMSinhVien dmSinhVien;
        for (Integer id :
                sinhVienIds) {
            dmSinhVien = sinhVienService.findOne(id);
            simpleSinhViens.add(new SimpleSinhVien(dmSinhVien.getId(), dmSinhVien.getHoDem(), dmSinhVien.getTen(), dmSinhVien.getDmLopHoc().getTen(), dmSinhVien.getMaSinhVien()));
        }

        simpleSinhViens.sort(Comparator.comparing(SimpleSinhVien::getTen));
        return new ResponseEntity<List<SimpleSinhVien>>(simpleSinhViens, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIANGVIEN')")
    @GetMapping(value = "/available-lessons/{lessonId}/{roomId}/{date}")
    public ResponseEntity<?> getAvailableLessonForRoomAtDate(@PathVariable int lessonId, @PathVariable int roomId, @PathVariable String date) throws ParseException {
        List<TKB_Tiet> tkb_availableLessons = tkb_tietService.findAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = dateFormat.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        //Lay lich nghi cua truong va lich nghi cua giao vien
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        DMNhanVien giaoVien = nhanVienService.findByMaNhanVien(tenDangNhap);
        TKB_LichNghiCuaNhanVien tkb_lichNghiCuaNhanVien = tkb_lichNghiCuaNhanVienService.findByGiaoVienAndFindNgay(giaoVien, sqlDate);


        if (tkb_lichNghiCuaNhanVien == null) {

            TKB_LichNghiCuaTruong tkb_lichNghiCuaTruong = tkb_lichNghiCuaTruongService.findByNgay(sqlDate);
            if (tkb_lichNghiCuaTruong == null) {
                //Loc lich cua phong theo ngay
                List<TKB_Tiet> notFreeLessonsOfRoomByDate = this.getCalendarOfRoomByDate(roomId, date);
                tkb_availableLessons.removeAll(notFreeLessonsOfRoomByDate);

                //Loc lich cua giao vien theo ngay
                List<TKB_Tiet> notFreeLessonsOfTeacherByDate = this.getCalendarOfTeacherByDate(tenDangNhap, sqlDate);
                tkb_availableLessons.removeAll(notFreeLessonsOfTeacherByDate);

                //Loc lich cua sinh vien cua tiet hoc dang chinh sua theo ngay
                List<TKB_Tiet> notFreeLessonsOfStudentsOfLessonByDate = this.getCalendarOfStudentsOfLessonByDate(lessonId, sqlDate);
                tkb_availableLessons.removeAll(notFreeLessonsOfStudentsOfLessonByDate);

                TKB_LichHocTheoNgay tkbLichHocTheoNgay = tkb_lichHocTheoNgayService.findOne(lessonId);
                if (date.equals(tkbLichHocTheoNgay.getNgay().toString())) {
                    List<TKB_Tiet> tkb_tietHienTaiCuaLessons = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkbLichHocTheoNgay.getTkb_tietDauTien().getId() - 1, tkbLichHocTheoNgay.getTkb_tietCuoiCung().getId() + 1);
                    for (TKB_Tiet tkb_tiet :
                            tkb_tietHienTaiCuaLessons) {
                        if (!tkb_availableLessons.contains(tkb_tiet)) {
                            tkb_availableLessons.add(tkb_tiet);
                        }
                    }
                }

                tkb_availableLessons.sort(Comparator.comparing(TKB_Tiet::getThuTu));
                return new ResponseEntity<List<TKB_Tiet>>(tkb_availableLessons, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cấn lịch nghỉ của trường", HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>("Cấn lịch nghỉ của giáo viên", HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('GIANGVIEN')")
    @GetMapping(value = "/calendar/diem-danh/{lessonId}")
    public ResponseEntity<List<SimpleDiemDanh>> getDiemDanhList(@PathVariable int lessonId) {
        List<SimpleDiemDanh> simpleDiemDanhs = new ArrayList<>();
        TKB_LichHocTheoNgay tkbLichHocTheoNgay = tkb_lichHocTheoNgayService.findOne(lessonId);
        for (TKB_LichHocTheoNgay_DiemDanh tkb_lichHocTheoNgay_diemDanh :
                tkbLichHocTheoNgay.getTkb_lichHocTheoNgay_diemDanhs()) {
            simpleDiemDanhs.add(new SimpleDiemDanh(tkb_lichHocTheoNgay_diemDanh.getId(), tkb_lichHocTheoNgay_diemDanh.getDmSinhVien().getId(), tkb_lichHocTheoNgay_diemDanh.isPresented()));
        }
        return new ResponseEntity<List<SimpleDiemDanh>>(simpleDiemDanhs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIANGVIEN')")
    @GetMapping(value = "/calendar/diem-danh/{lessonId}/{studentId}/{status}")
    public ResponseEntity<?> updateDiemDanh(@PathVariable int lessonId, @PathVariable int studentId, @PathVariable boolean status) {
        tkb_lichHocTheoNgay_diemDanhService.diemDanh(lessonId, studentId, status);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    public List<TKB_Tiet> getCalendarOfRoomByDate(int roomId, String date) {
        List<TKB_Tiet> tkb_availableLessons = tkb_tietService.findAll();
        List<TKB_Tiet> tkb_availableLessonsClone = tkb_tietService.findAll();

        List<TKB_LichHocTheoNgay> tkb_lichHocTheoNgayCuaPhongs = tkb_lichHocTheoNgayService.getLichHocOfRoomByDate(roomId, date);
        for (TKB_LichHocTheoNgay tkb_lichHocTheoNgay :
                tkb_lichHocTheoNgayCuaPhongs) {
            List<TKB_Tiet> tkb_tietNotFrees = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkb_lichHocTheoNgay.getTkb_tietDauTien().getId() - 1, tkb_lichHocTheoNgay.getTkb_tietCuoiCung().getId() + 1);
            tkb_availableLessons.removeAll(tkb_tietNotFrees);
        }
        tkb_availableLessonsClone.removeAll(tkb_availableLessons);

        for (TKB_Tiet tkb_tiet :
                tkb_availableLessonsClone) {
            System.out.println(tkb_tiet.getTen());
        }
        return tkb_availableLessonsClone;
    }

    public List<TKB_Tiet> getCalendarOfTeacherByDate(String maGiaoVien, java.sql.Date date) {
        List<TKB_Tiet> tkb_availableLessons = tkb_tietService.findAll();
        List<TKB_Tiet> tkb_availableLessonsClone = tkb_tietService.findAll();

        DMNhanVien giaoVien = nhanVienService.findByMaNhanVien(maGiaoVien);
        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByGiaoVien(giaoVien);

        List<TKB_LichHocTheoNgay> tkb_lichHocTheoNgayCuaGiaoVien = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHoc :
                dmLopMonHocs) {
            tkb_lichHocTheoNgayCuaGiaoVien.addAll(tkb_lichHocTheoNgayService.findByDMLopMonHocAndNgay(DMLopMonHoc, date));
        }
//        tkb_lichHocTheoNgayCuaGiaoVien.removeIf(tkb_lichHocTheoNgay -> !date.equals(tkb_lichHocTheoNgay.getNgay().toString()));

        for (TKB_LichHocTheoNgay tkb_lichHocTheoNgay :
                tkb_lichHocTheoNgayCuaGiaoVien) {
            List<TKB_Tiet> tkb_tietNotFrees = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkb_lichHocTheoNgay.getTkb_tietDauTien().getId() - 1, tkb_lichHocTheoNgay.getTkb_tietCuoiCung().getId() + 1);
            tkb_availableLessons.removeAll(tkb_tietNotFrees);
        }

        tkb_availableLessonsClone.removeAll(tkb_availableLessons);
        return tkb_availableLessonsClone;
    }

    public List<TKB_Tiet> getCalendarOfStudentsOfLessonByDate(int lessonId, java.sql.Date date) {
        List<TKB_Tiet> tkb_availableLessons = tkb_tietService.findAll();
        List<TKB_Tiet> tkb_availableLessonsClone = tkb_tietService.findAll();
//        System.out.println("------"+lessonId);
        TKB_LichHocTheoNgay tkbLichHocTheoNgay = tkb_lichHocTheoNgayService.findOne(lessonId);

        DMLopMonHoc dmLopMonHoc = tkbLichHocTheoNgay.getDmLopMonHoc();
        List<DMSinhVien> dmSinhVienCuaTietHocs = new ArrayList<>();
        for (DMLopMonHoc_SinhVien dmLopMonHoc_sinhVien :
                dmLopMonHoc.getDMLopMonHoc_sinhViens()) {
            dmSinhVienCuaTietHocs.add(dmLopMonHoc_sinhVien.getDmSinhVien());
        }

        System.out.println("-------" + dmSinhVienCuaTietHocs.size());

        for (DMSinhVien dmSinhVien :
                dmSinhVienCuaTietHocs) {
            Set<DMLopMonHoc_SinhVien> DMLopMonHoc_sinhViens = dmSinhVien.getDMLopMonHoc_sinhViens();
            List<DMLopMonHoc> DMLopMonHocCuaSinhViens = new ArrayList<>();
            for (DMLopMonHoc_SinhVien dmLopMonHoc_sinhVien :
                    DMLopMonHoc_sinhViens) {
                DMLopMonHocCuaSinhViens.add(dmLopMonHoc_sinhVien.getDmLopMonHoc());
            }

            for (DMLopMonHoc DMLopMonHocCuaSinhVien :
                    DMLopMonHocCuaSinhViens) {
                List<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays = tkb_lichHocTheoNgayService.findByDMLopMonHocAndNgay(DMLopMonHocCuaSinhVien, date);
                for (TKB_LichHocTheoNgay tkbLichHocTheoNgay1 :
                        tkb_lichHocTheoNgays) {
                    List<TKB_Tiet> tkb_tietNotFrees = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkbLichHocTheoNgay1.getTkb_tietDauTien().getId() - 1, tkbLichHocTheoNgay1.getTkb_tietCuoiCung().getId() + 1);
                    tkb_availableLessons.removeAll(tkb_tietNotFrees);
                    if (tkb_availableLessons.isEmpty()) {
                        break;
                    }
                }
            }
        }

        tkb_availableLessonsClone.removeAll(tkb_availableLessons);
        return tkb_availableLessonsClone;
    }

    @PreAuthorize("hasRole('GIANGVIEN')")
    @GetMapping(value = "/calendar/current-week-calendar")
    public ResponseEntity<MappingJacksonValue> getCurrentWeekCalendar() {
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        DMNhanVien dmNhanVien = nhanVienService.findByMaNhanVien(tenDangNhap);
        java.util.Date now = new java.util.Date();
        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = kiHoc_namHocService.findAll();

        TKB_KiHoc_NamHoc tkb_kiHoc_namHocHienTai = new TKB_KiHoc_NamHoc();

        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocs) {
            if (!now.before(tkb_kiHoc_namHoc.getNgayBatDau()) && !now.after(tkb_kiHoc_namHoc.getNgayKetThuc())) {
                tkb_kiHoc_namHocHienTai = tkb_kiHoc_namHoc;
            }
        }

        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocId(tkb_kiHoc_namHocHienTai.getId());
        List<DMLopMonHoc> dmLopMonHocsCuaGiangVien = new ArrayList<>();

        for (DMLopMonHoc dmLopMonHoc :
                dmLopMonHocs) {
            if(dmNhanVien.getId() == dmLopMonHoc.getDmNhanVien().getId()){
                dmLopMonHocsCuaGiangVien.add(dmLopMonHoc);
            }
        }

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dmLopMonHocsCuaGiangVien);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.DMLopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "dmNhanVien", "tkb_khoa_khoaHoc", "dmMonHoc", "tkb_lichHocTheoTuans"));

        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIANGVIEN')")
    @GetMapping(value = "/calendar/current-semester-year")
    public ResponseEntity<SemesterYear> getCurrentKiHocNamHoc() {
        java.util.Date now = new java.util.Date();
        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = kiHoc_namHocService.findAll();

        TKB_KiHoc_NamHoc tkb_kiHoc_namHocHienTai = new TKB_KiHoc_NamHoc();

        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocs) {
            if (!now.before(tkb_kiHoc_namHoc.getNgayBatDau()) && !now.after(tkb_kiHoc_namHoc.getNgayKetThuc())) {
                tkb_kiHoc_namHocHienTai = tkb_kiHoc_namHoc;
            }
        }

        return new ResponseEntity<SemesterYear>(new SemesterYear(tkb_kiHoc_namHocHienTai.getTkb_kiHoc(), tkb_kiHoc_namHocHienTai.getTkb_namHoc()), HttpStatus.OK);
    }

    @GetMapping(value = "/calendar/print/{teacherId}")
    @Procedure("application/vnd.ms-excel")
    public ResponseEntity<InputStreamResource> printSemesterCalendar(@PathVariable int teacherId) throws IOException {
        DMNhanVien dmNhanVien = nhanVienService.findOne(teacherId);
        java.util.Date now = new java.util.Date();
        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = kiHoc_namHocService.findAll();

        TKB_KiHoc_NamHoc tkb_kiHoc_namHocHienTai = new TKB_KiHoc_NamHoc();

        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocs) {
            if (!now.before(tkb_kiHoc_namHoc.getNgayBatDau()) && !now.after(tkb_kiHoc_namHoc.getNgayKetThuc())) {
                tkb_kiHoc_namHocHienTai = tkb_kiHoc_namHoc;
            }
        }

        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocId(tkb_kiHoc_namHocHienTai.getId());
        List<DMLopMonHoc> dmLopMonHocsCuaGiangVien = new ArrayList<>();

        for (DMLopMonHoc dmLopMonHoc :
                dmLopMonHocs) {
            if(dmNhanVien.getId() == dmLopMonHoc.getDmNhanVien().getId()){
                dmLopMonHocsCuaGiangVien.add(dmLopMonHoc);
            }
        }

        XSSFWorkbook workbook = excelService.exportSemesterCalendar(dmLopMonHocsCuaGiangVien,
                tkb_kiHoc_namHocHienTai.getTkb_kiHoc().getTen(), tkb_kiHoc_namHocHienTai.getTkb_namHoc().getName());
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=tkb.xlsx");
        workbook.write(httpServletResponse.getOutputStream());
        org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream byteArrayOutputStream = new org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream();
        try {
            workbook.write(byteArrayOutputStream);
            InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            return new ResponseEntity<InputStreamResource>(inputStreamResource, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('GIANGVIEN')")
    @GetMapping(value = "/get-teacher-id")
    public int getStudentId(){
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        DMNhanVien dmNhanVien = nhanVienService.findByMaNhanVien(tenDangNhap);
        return dmNhanVien.getId();
    }
}
