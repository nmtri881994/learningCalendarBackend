package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.entity.*;

import java.util.*;

/**
 * Created by Tri on 4/4/2017.
 */

@RestController
@RequestMapping(value = "api/giaovu")
public class GiaoVuController {

    @Autowired
    private NamHocService namHocService;

    @Autowired
    private TKB_LichHocTheoTuanService tkb_lichHocTheoTuanService;

    @Autowired
    private GiangDuongService giangDuongService;

    @Autowired
    private TKB_ThuService tkb_thuService;

    @Autowired
    private TKB_TietService tkb_tietService;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private Khoa_KhoaHocService khoa_khoaHocService;

    @Autowired
    private KiHoc_NamHocService kiHoc_namHocService;

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/calendar/year-not-end")
    public ResponseEntity<List<NamHoc>> getNamHocsNotEnd() {
        List<NamHoc> namHocsNotEnd = namHocService.getYearsNotEnd();
        namHocsNotEnd.sort(Comparator.comparing(NamHoc::getNgayBatDau));

        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

        List<KiHoc_NamHoc> kiHoc_namHocsCuaNamDauTien = new ArrayList<>();
        for (KiHoc_NamHoc kiHoc_namHoc :
                namHocsNotEnd.get(0).getKiHoc_namHocs()) {
            kiHoc_namHocsCuaNamDauTien.add(kiHoc_namHoc);
        }
        kiHoc_namHocsCuaNamDauTien.sort(Comparator.comparing(KiHoc_NamHoc::getNgayBatDau));

        KiHoc_NamHoc kiHoc_namHocSauCung = kiHoc_namHocsCuaNamDauTien.get(kiHoc_namHocsCuaNamDauTien.size() - 1);
        if (sqlDate.compareTo(kiHoc_namHocSauCung.getNgayBatDau()) > 0) {
            namHocsNotEnd.remove(0);
        }

        return new ResponseEntity<List<NamHoc>>(namHocsNotEnd, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/calendar/{yearId}/semester-not-end")
    public ResponseEntity<List<KiHoc>> getKiHocNotEndOfYear(@PathVariable int yearId) {
        NamHoc namHoc = namHocService.findOne(yearId);
        Set<KiHoc_NamHoc> kiHoc_namHocs = namHoc.getKiHoc_namHocs();

        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        kiHoc_namHocs.removeIf(kiHoc_namHoc -> sqlDate.compareTo(kiHoc_namHoc.getNgayBatDau()) > 0);

        List<KiHoc_NamHoc> kiHoc_namHocsArrayList = new ArrayList<>();
        for (KiHoc_NamHoc kiHoc_namHoc :
                kiHoc_namHocs) {
            kiHoc_namHocsArrayList.add(kiHoc_namHoc);
        }
        kiHoc_namHocsArrayList.sort(Comparator.comparing(KiHoc_NamHoc::getNgayBatDau));

        List<KiHoc> kiHocs = new ArrayList<>();
        for (KiHoc_NamHoc kiHoc_namHoc :
                kiHoc_namHocsArrayList) {
            kiHocs.add(kiHoc_namHoc.getKiHoc());
        }

        return new ResponseEntity<List<KiHoc>>(kiHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/edit-calendar/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}/{nganhId}")
    public ResponseEntity<TKB_LichHocTheoTuan> updateWeekCalendar(@RequestBody TKB_LichHocTheoTuan tkb_lichHocTheoTuan, @PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId, @PathVariable int nganhId) {
        Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        KiHoc_NamHoc kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        List<LopMonHoc> lopMonHocs;
        if (nganhId != 0) {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId(), nganhId);
        } else {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId());
        }

        tkb_lichHocTheoTuan.setTkb_tietDauTien(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietDauTien().getId()));
        tkb_lichHocTheoTuan.setTkb_tietCuoiCung(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId()));

        boolean canUpdate = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, lopMonHocs);
        if(canUpdate){
            tkb_lichHocTheoTuanService.updateWeekCalendar(tkb_lichHocTheoTuan);
            return new ResponseEntity<TKB_LichHocTheoTuan>(tkb_lichHocTheoTuanService.findOne(tkb_lichHocTheoTuan.getId()), HttpStatus.OK);
        }else{
            return new ResponseEntity<TKB_LichHocTheoTuan>(tkb_lichHocTheoTuan, HttpStatus.FORBIDDEN);
        }

    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/add-calendar/{lopMonHocId}/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}/{nganhId}")
    public ResponseEntity<TKB_LichHocTheoTuan> addCalendar(@RequestBody TKB_LichHocTheoTuan tkb_lichHocTheoTuan, @PathVariable int lopMonHocId, @PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId, @PathVariable int nganhId){

        Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        KiHoc_NamHoc kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        List<LopMonHoc> lopMonHocs;
        if (nganhId != 0) {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId(), nganhId);
        } else {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId());
        }

        tkb_lichHocTheoTuan.setTkb_tietDauTien(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietDauTien().getId()));
        tkb_lichHocTheoTuan.setTkb_tietCuoiCung(tkb_tietService.findOne(tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId()));

        boolean canAdd = tkb_lichHocTheoTuanService.canAddOrEditWeekCalendar(tkb_lichHocTheoTuan, lopMonHocs);
        if(canAdd){
            tkb_lichHocTheoTuan.setGiangDuong(giangDuongService.findOne(tkb_lichHocTheoTuan.getGiangDuong().getId()));
            tkb_lichHocTheoTuan.setTkb_thu(tkb_thuService.findOne(tkb_lichHocTheoTuan.getTkb_thu().getId()));
            tkb_lichHocTheoTuan.setLopMonHoc(lopMonHocService.findOne(lopMonHocId));
            tkb_lichHocTheoTuanService.addWeekCalendar(tkb_lichHocTheoTuan);
            return new ResponseEntity<TKB_LichHocTheoTuan>(tkb_lichHocTheoTuanService.findOne(tkb_lichHocTheoTuan.getId()), HttpStatus.OK);
        }else{
            return new ResponseEntity<TKB_LichHocTheoTuan>(tkb_lichHocTheoTuan, HttpStatus.FORBIDDEN);
        }


    }

    @PreAuthorize("hasRole('GIAOVU')")
    @DeleteMapping(value = "/delete-calendar/{calendarId}")
    public void deleteCalendar(@PathVariable int calendarId){
        tkb_lichHocTheoTuanService.deleteCalendar(calendarId);
    }
}
