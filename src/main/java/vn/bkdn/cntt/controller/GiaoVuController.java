package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.NamHocService;
import vn.bkdn.cntt.entity.KiHoc;
import vn.bkdn.cntt.entity.KiHoc_NamHoc;
import vn.bkdn.cntt.entity.NamHoc;

import java.util.*;

/**
 * Created by Tri on 4/4/2017.
 */

@RestController
@RequestMapping(value = "api/giaovu")
public class GiaoVuController {

    @Autowired
    private NamHocService namHocService;

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/calendar/year-not-end")
    public ResponseEntity<List<NamHoc>> getNamHocsNotEnd(){
        List<NamHoc> namHocsNotEnd = namHocService.getYearsNotEnd();
        namHocsNotEnd.sort(Comparator.comparing(NamHoc::getNgayBatDau));

        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

        List<KiHoc_NamHoc> kiHoc_namHocsCuaNamDauTien = new ArrayList<>();
        for (KiHoc_NamHoc kiHoc_namHoc:
                namHocsNotEnd.get(0).getKiHoc_namHocs()) {
            kiHoc_namHocsCuaNamDauTien.add(kiHoc_namHoc);
        }
        kiHoc_namHocsCuaNamDauTien.sort(Comparator.comparing(KiHoc_NamHoc::getNgayBatDau));

        KiHoc_NamHoc kiHoc_namHocSauCung = kiHoc_namHocsCuaNamDauTien.get(kiHoc_namHocsCuaNamDauTien.size()-1);
        if(sqlDate.compareTo(kiHoc_namHocSauCung.getNgayBatDau())>0){
            namHocsNotEnd.remove(0);
        }

        return new ResponseEntity<List<NamHoc>>(namHocsNotEnd, HttpStatus.OK);
    }
}
