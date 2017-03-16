package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.SinhVienService;
import vn.bkdn.cntt.entity.SinhVien;

import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */

@RestController
@RequestMapping(value = "api/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping
    private ResponseEntity<List<SinhVien>> findAll(){
        return new ResponseEntity<List<SinhVien>>(sinhVienService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{sinhVienId}")
    private ResponseEntity<SinhVien> findOne(@PathVariable int sinhVienId){
        return new ResponseEntity<SinhVien>(sinhVienService.findOne(sinhVienId), HttpStatus.OK);
    }
}
