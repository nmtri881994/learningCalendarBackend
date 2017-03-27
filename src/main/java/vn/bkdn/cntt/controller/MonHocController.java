package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.MonHocService;
import vn.bkdn.cntt.entity.GiangDuong;
import vn.bkdn.cntt.entity.MonHoc;
import vn.bkdn.cntt.entity.MonHoc_GiangDuong;

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
    public ResponseEntity<List<GiangDuong>> getGiangDuongsByMonHocId(@PathVariable int monHocId){
        MonHoc monHoc = monHocService.findOne(monHocId);
        Set<MonHoc_GiangDuong> monHoc_giangDuongs = monHoc.getMonHoc_giangDuongs();
        List<GiangDuong> giangDuongs = new ArrayList<>();
        for (MonHoc_GiangDuong monHoc_giangDuong:
             monHoc_giangDuongs) {
            giangDuongs.add(monHoc_giangDuong.getGiangDuong());
        }

        return new ResponseEntity<List<GiangDuong>>(giangDuongs, HttpStatus.OK);
    }
}
