package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.MonHocService;
import vn.bkdn.cntt.entity.DMGiangDuong;
import vn.bkdn.cntt.entity.DMMonHoc;
import vn.bkdn.cntt.entity.DMMonHoc_GiangDuong;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by XuanVinh on 3/28/2017.
 */

@RestController
@RequestMapping(value = "api/monhoc")
public class MonHocController {

    @Autowired
    private MonHocService monHocService;

    @GetMapping(value = "/{monHocId}")
    public ResponseEntity<List<DMGiangDuong>> getGiangDuongsByMonHocId(@PathVariable int monHocId){
        DMMonHoc dmmonHoc = monHocService.findOne(monHocId);
        Set<DMMonHoc_GiangDuong> dm_monHoc_giangDuong = dmmonHoc.getDm_monHoc_giangDuong();
        List<DMGiangDuong> dmGiangDuongs = new ArrayList<>();
        for (DMMonHoc_GiangDuong dm_monHoc_giangDuong1 :
                dm_monHoc_giangDuong) {
            dmGiangDuongs.add(dm_monHoc_giangDuong1.getDmGiangDuong());
        }

        return new ResponseEntity<List<DMGiangDuong>>(dmGiangDuongs, HttpStatus.OK);
    }
}
