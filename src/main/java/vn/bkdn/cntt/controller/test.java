package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.entity.*;
import vn.bkdn.cntt.repository.TK_TaiKhoanHeThongRepository;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@RestController
@RequestMapping(value = "api/test")
@CrossOrigin(value = "*")
public class test {

    @Autowired
    private TK_TaiKhoanHeThong_VaiTroService taiKhoanHeThong_vaiTroService;

    @Autowired
    private TK_TaiKhoanHeThongRepository taiKhoanHeThongRepository;

    @Autowired
    private DMNhanVienService nhanVienService;

    @Autowired
    private DMSinhVienService sinhVienService;

    @Autowired
    private TKB_KiHoc_NamHocService kiHoc_namHocService;

    @Autowired
    private DMLopMonHocService lopMonHocService;

    @Autowired ExcelService excelService;

    @Autowired
    public HttpServletResponse httpServletResponse;

    @GetMapping(value = "/1")
    public void getCalendaer() throws ParseException {
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse("11-03-2017");
        c.setTime(date);
    }

    @GetMapping(value="/taikhoan/{taiKhoanId}")
    public ResponseEntity<List<TK_TaiKhoanHeThong_VaiTro>> getTaiKhoanVaiTro(@PathVariable int taiKhoanId){
        return new ResponseEntity<List<TK_TaiKhoanHeThong_VaiTro>>(taiKhoanHeThong_vaiTroService.getTaiKhoanHeThongVaiTrosByTaiKhoan(taiKhoanHeThongRepository.findOne(taiKhoanId)), HttpStatus.OK);
    }

    @GetMapping(value="/giaovien/{maGiaoVien}")
    public ResponseEntity<DMNhanVien> getGiaoVien(@PathVariable String maGiaoVien){
        return new ResponseEntity<DMNhanVien>(nhanVienService.findByMaNhanVien(maGiaoVien), HttpStatus.OK);
    }

    @GetMapping(value = "/calendar/week/{date}")
    public int getWeekOfYear(@PathVariable String date) throws ParseException {
        Calendar c = Calendar.getInstance(Locale.GERMAN);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dateFormat.parse(date);

        c.setTime(d);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    @GetMapping(value = "/calendar/date/{date}")
    public int getDayOfWeek(@PathVariable String date) throws ParseException {
        Calendar c = Calendar.getInstance(Locale.GERMAN);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dateFormat.parse(date);

        c.setTime(d);
        return c.get(Calendar.DAY_OF_WEEK);
    }
//
//    @GetMapping(value = "/calendar/print")
//    @Procedure("application/vnd.ms-excel")
//    public ResponseEntity<InputStreamResource> printSemesterCalendar() throws IOException {
//        DMSinhVien dmSinhVien = sinhVienService.findOne(11);
//        java.util.Date now = new java.util.Date();
//        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = kiHoc_namHocService.findAll();
//
//        TKB_KiHoc_NamHoc tkb_kiHoc_namHocHienTai = new TKB_KiHoc_NamHoc();
//
//        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
//                tkb_kiHoc_namHocs) {
//            if (!now.before(tkb_kiHoc_namHoc.getNgayBatDau()) && !now.after(tkb_kiHoc_namHoc.getNgayKetThuc())) {
//                tkb_kiHoc_namHocHienTai = tkb_kiHoc_namHoc;
//            }
//        }
//
//        List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocId(tkb_kiHoc_namHocHienTai.getId());
//        List<DMLopMonHoc> dmLopMonHocsCuaSinhVien = new ArrayList<>();
//
//        for (DMLopMonHoc dmLopMonHoc :
//                dmLopMonHocs) {
//            for (DMLopMonHoc_SinhVien dmLopMonHoc_sinhVien :
//                    dmLopMonHoc.getDMLopMonHoc_sinhViens()) {
//                if (dmSinhVien.getId() == dmLopMonHoc_sinhVien.getDmSinhVien().getId()) {
//                    dmLopMonHocsCuaSinhVien.add(dmLopMonHoc);
//                    break;
//                }
//            }
//        }
//
//        XSSFWorkbook workbook = excelService.exportSemesterCalendar(dmLopMonHocsCuaSinhVien,
//                tkb_kiHoc_namHocHienTai.getTkb_kiHoc().getTen(), tkb_kiHoc_namHocHienTai.getTkb_namHoc().getName());
//        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=tkb.xlsx");
//        workbook.write(httpServletResponse.getOutputStream());
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        try {
//            workbook.write(byteArrayOutputStream);
//            InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
//            return new ResponseEntity<InputStreamResource>(inputStreamResource, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
//    }
}
