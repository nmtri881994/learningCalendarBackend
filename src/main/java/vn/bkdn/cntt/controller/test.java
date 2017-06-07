package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.NhanVienService;
import vn.bkdn.cntt.Service.TaiKhoanHeThong_VaiTroService;
import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong_VaiTro;
import vn.bkdn.cntt.repository.TaiKhoanHeThongRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@RestController
@RequestMapping(value = "api/test")
@CrossOrigin(value = "*")
public class test {

    @Autowired
    private TaiKhoanHeThong_VaiTroService taiKhoanHeThong_vaiTroService;

    @Autowired
    private TaiKhoanHeThongRepository taiKhoanHeThongRepository;

    @Autowired
    private NhanVienService nhanVienService;

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
}
