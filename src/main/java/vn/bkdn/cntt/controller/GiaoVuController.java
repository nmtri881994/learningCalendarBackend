package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.NamHocService;
import vn.bkdn.cntt.entity.Khoa;
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

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/calendar/{yearId}/semester-not-end")
    public ResponseEntity<List<KiHoc>> getKiHocNotEndOfYear(@PathVariable int yearId){
        NamHoc namHoc = namHocService.findOne(yearId);
        Set<KiHoc_NamHoc> kiHoc_namHocs = namHoc.getKiHoc_namHocs();

        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        kiHoc_namHocs.removeIf(kiHoc_namHoc -> sqlDate.compareTo(kiHoc_namHoc.getNgayBatDau())>0);

        List<KiHoc_NamHoc> kiHoc_namHocsArrayList = new ArrayList<>();
        for (KiHoc_NamHoc kiHoc_namHoc:
             kiHoc_namHocs) {
            kiHoc_namHocsArrayList.add(kiHoc_namHoc);
        }
        kiHoc_namHocsArrayList.sort(Comparator.comparing(KiHoc_NamHoc::getNgayBatDau));

        List<KiHoc> kiHocs = new ArrayList<>();
        for (KiHoc_NamHoc kiHoc_namHoc:
                kiHoc_namHocsArrayList) {
            kiHocs.add(kiHoc_namHoc.getKiHoc());
        }

        return new ResponseEntity<List<KiHoc>>(kiHocs, HttpStatus.OK);
    }

}
