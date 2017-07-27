package vn.bkdn.cntt.controller;

import org.apache.poi.ss.formula.functions.Na;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.controller.APIEntity.GiangDuong;
import vn.bkdn.cntt.controller.APIEntity.Khoa;
import vn.bkdn.cntt.controller.APIEntity.NamHoc;
import vn.bkdn.cntt.entity.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tri on 7/26/2017.
 */

@RestController
@RequestMapping(value = "/api/input-data")
public class ImputDataController {

    @Autowired
    private DMLoaiDonViService dmLoaiDonViService;

    @Autowired
    private DMDonViService dmDonViService;

    @Autowired
    private DMLoaiPhongService dmLoaiPhongService;

    @Autowired
    private GiangDuongService giangDuongService;

    @Autowired
    private DayNhaService dayNhaService;

    @Autowired
    private TKB_KhoaHocService tkb_khoaHocService;

    @Autowired
    private TKB_NamHocService tkb_namHocService;

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/khoa")
    public ResponseEntity<Khoa> inputKhoa(@RequestBody Khoa khoa) {
        DMDonVi newDonVi = dmDonViService.save(new DMDonVi(khoa.getMa(), khoa.getTen(), dmLoaiDonViService.findOne(1)));
        return new ResponseEntity<Khoa>(new Khoa(newDonVi.getId(), newDonVi.getMa(), newDonVi.getTen()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/edit-khoa")
    public ResponseEntity<Boolean> editKhoa(@RequestBody Khoa khoa) {
        return new ResponseEntity<Boolean>(dmDonViService.editKhoa(khoa.getId(), khoa.getMa(), khoa.getTen()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-khoas")
    public ResponseEntity<List<Khoa>> getAllKhoas() {
        List<DMDonVi> dmDonVis = dmDonViService.findAllFaculty();
        List<Khoa> khoas = new ArrayList<>();
        for (DMDonVi dmDonVi :
                dmDonVis) {
            khoas.add(new Khoa(dmDonVi.getId(), dmDonVi.getMa(), dmDonVi.getTen()));
        }

        khoas.sort(Comparator.comparing(Khoa::getMa));
        return new ResponseEntity<List<Khoa>>(khoas, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/delete-khoa/{khoaId}")
    public ResponseEntity deleteKhoa(@PathVariable int khoaId) {
        dmDonViService.delete(khoaId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-loai-phong")
    public ResponseEntity<List<DMLoaiPhong>> getAllLoaiPhong() {
        List<DMLoaiPhong> dmLoaiPhongs = dmLoaiPhongService.findAll();
        dmLoaiPhongs.sort(Comparator.comparing(DMLoaiPhong::getTen));
        return new ResponseEntity<List<DMLoaiPhong>>(dmLoaiPhongs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-giang-duong")
    public ResponseEntity<List<DMGiangDuong>> getAllGiangDuong() {
        List<DMGiangDuong> dmGiangDuongs = giangDuongService.findAll();
        dmGiangDuongs.sort(Comparator.comparing(DMGiangDuong::getMaGiangDuong));
        return new ResponseEntity<List<DMGiangDuong>>(dmGiangDuongs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/giang-duong")
    public ResponseEntity<List<DMGiangDuong>> insertGiangDuong(@RequestBody GiangDuong giangDuong) {
        giangDuongService.save(new DMGiangDuong(giangDuong.getId(), giangDuong.getMaGiangDuong(), giangDuong.getTen(),
                dayNhaService.findOne(giangDuong.getLoaiPhongId()), giangDuong.getTang(), giangDuong.getSoLuong()));

        List<DMGiangDuong> dmGiangDuongs = giangDuongService.findAll();
        dmGiangDuongs.sort(Comparator.comparing(DMGiangDuong::getMaGiangDuong));
        return new ResponseEntity<List<DMGiangDuong>>(dmGiangDuongs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/giang-duong/edit")
    public ResponseEntity<List<DMGiangDuong>> editGiangDuong(@RequestBody GiangDuong giangDuong) {
        giangDuongService.editGiangDuong(giangDuong);

        List<DMGiangDuong> dmGiangDuongs = giangDuongService.findAll();
        dmGiangDuongs.sort(Comparator.comparing(DMGiangDuong::getMaGiangDuong));
        return new ResponseEntity<List<DMGiangDuong>>(dmGiangDuongs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/giang-duong/delete/{giangDuongId}")
    public ResponseEntity<List<DMGiangDuong>> deleteGiangDuong(@PathVariable int giangDuongId) {
        giangDuongService.deleteGiangDuong(giangDuongId);

        List<DMGiangDuong> dmGiangDuongs = giangDuongService.findAll();
        dmGiangDuongs.sort(Comparator.comparing(DMGiangDuong::getMaGiangDuong));
        return new ResponseEntity<List<DMGiangDuong>>(dmGiangDuongs, HttpStatus.OK);
    }

    //khoa hoc

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-khoa-hoc")
    public ResponseEntity<List<TKB_KhoaHoc>> getAllKhoaHoc() {
        List<TKB_KhoaHoc> tkb_khoaHocs = tkb_khoaHocService.findAll();
        tkb_khoaHocs.sort(Comparator.comparing(TKB_KhoaHoc::getNam));
        return new ResponseEntity<List<TKB_KhoaHoc>>(tkb_khoaHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/khoa-hoc")
    public ResponseEntity<List<TKB_KhoaHoc>> insertKhoaHoc(@RequestBody TKB_KhoaHoc tkb_khoaHoc) {
        tkb_khoaHocService.insertKhoaHoc(tkb_khoaHoc);

        List<TKB_KhoaHoc> tkb_khoaHocs = tkb_khoaHocService.findAll();
        tkb_khoaHocs.sort(Comparator.comparing(TKB_KhoaHoc::getNam));
        return new ResponseEntity<List<TKB_KhoaHoc>>(tkb_khoaHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/khoa-hoc/edit")
    public ResponseEntity<List<TKB_KhoaHoc>> editKhoaHoc(@RequestBody TKB_KhoaHoc tkb_khoaHoc) {
        tkb_khoaHocService.editKhoaHoc(tkb_khoaHoc);

        List<TKB_KhoaHoc> tkb_khoaHocs = tkb_khoaHocService.findAll();
        tkb_khoaHocs.sort(Comparator.comparing(TKB_KhoaHoc::getNam));
        return new ResponseEntity<List<TKB_KhoaHoc>>(tkb_khoaHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/khoa-hoc/delete/{khoaHocId}")
    public ResponseEntity<List<TKB_KhoaHoc>> deleteKhoaHoc(@PathVariable int khoaHocId) {
        tkb_khoaHocService.deleteKhoaHoc(khoaHocId);

        List<TKB_KhoaHoc> tkb_khoaHocs = tkb_khoaHocService.findAll();
        tkb_khoaHocs.sort(Comparator.comparing(TKB_KhoaHoc::getNam));
        return new ResponseEntity<List<TKB_KhoaHoc>>(tkb_khoaHocs, HttpStatus.OK);
    }

    //nam hoc

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-nam-hoc")
    public ResponseEntity<List<NamHoc>> getAllNamHoc() {
        List<TKB_NamHoc> tkb_namHocs = tkb_namHocService.findAll();
        tkb_namHocs.sort(Comparator.comparing(TKB_NamHoc::getName));

        List<NamHoc> namHocs = new ArrayList<>();
        for (TKB_NamHoc tkb_namHoc:
             tkb_namHocs) {
            namHocs.add(new NamHoc(tkb_namHoc.getId(), tkb_namHoc.getName(), tkb_namHoc.getNgayBatDau(), tkb_namHoc.getNgayKetThuc()));
        }
        return new ResponseEntity<List<NamHoc>>(namHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/nam-hoc")
    public ResponseEntity<List<NamHoc>> insertNamHoc(@RequestBody NamHoc namHoc) {
        tkb_namHocService.insertNamHoc(namHoc);

        List<TKB_NamHoc> tkb_namHocs = tkb_namHocService.findAll();
        tkb_namHocs.sort(Comparator.comparing(TKB_NamHoc::getName));

        List<NamHoc> namHocs = new ArrayList<>();
        for (TKB_NamHoc tkb_namHoc:
                tkb_namHocs) {
            namHocs.add(new NamHoc(tkb_namHoc.getId(), tkb_namHoc.getName(), tkb_namHoc.getNgayBatDau(), tkb_namHoc.getNgayKetThuc()));
        }
        return new ResponseEntity<List<NamHoc>>(namHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/nam-hoc/edit")
    public ResponseEntity<List<NamHoc>> editNamHoc(@RequestBody NamHoc namHoc) {
        tkb_namHocService.editNamHoc(namHoc);

        List<TKB_NamHoc> tkb_namHocs = tkb_namHocService.findAll();
        tkb_namHocs.sort(Comparator.comparing(TKB_NamHoc::getName));

        List<NamHoc> namHocs = new ArrayList<>();
        for (TKB_NamHoc tkb_namHoc:
                tkb_namHocs) {
            namHocs.add(new NamHoc(tkb_namHoc.getId(), tkb_namHoc.getName(), tkb_namHoc.getNgayBatDau(), tkb_namHoc.getNgayKetThuc()));
        }
        return new ResponseEntity<List<NamHoc>>(namHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/nam-hoc/delete/{namHocId}")
    public ResponseEntity<List<NamHoc>> deleteNamHoc(@PathVariable int namHocId) {
        tkb_namHocService.deleteNamHoc(namHocId);

        List<TKB_NamHoc> tkb_namHocs = tkb_namHocService.findAll();
        tkb_namHocs.sort(Comparator.comparing(TKB_NamHoc::getName));

        List<NamHoc> namHocs = new ArrayList<>();
        for (TKB_NamHoc tkb_namHoc:
                tkb_namHocs) {
            namHocs.add(new NamHoc(tkb_namHoc.getId(), tkb_namHoc.getName(), tkb_namHoc.getNgayBatDau(), tkb_namHoc.getNgayKetThuc()));
        }
        return new ResponseEntity<List<NamHoc>>(namHocs, HttpStatus.OK);
    }
}
