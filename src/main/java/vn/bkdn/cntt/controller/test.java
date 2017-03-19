package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.TaiKhoanHeThongService;
import vn.bkdn.cntt.Service.TaiKhoanHeThong_VaiTroService;
import vn.bkdn.cntt.entity.TaiKhoanHeThong_VaiTro;
import vn.bkdn.cntt.repository.TaiKhoanHeThongRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@RestController
@RequestMapping(value = "api/test")
public class test {

    @Autowired
    private TaiKhoanHeThong_VaiTroService taiKhoanHeThong_vaiTroService;

    @Autowired
    private TaiKhoanHeThongRepository taiKhoanHeThongRepository;

    @GetMapping(value = "/1")
    public void getCalendaer() throws ParseException {
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse("11-03-2017");

        c.setTime(date);
        System.out.println("La thu: "+c.get(Calendar.DAY_OF_WEEK));
    }

    @GetMapping(value="/taikhoan/{taiKhoanId}")
    public ResponseEntity<List<TaiKhoanHeThong_VaiTro>> getTaiKhoanVaiTro(@PathVariable int taiKhoanId){
        return new ResponseEntity<List<TaiKhoanHeThong_VaiTro>>(taiKhoanHeThong_vaiTroService.getTaiKhoanHeThongVaiTrosByTaiKhoan(taiKhoanHeThongRepository.findOne(taiKhoanId)), HttpStatus.OK);
    }

}
