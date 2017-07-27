package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.controller.APIEntity.GiangDuong;
import vn.bkdn.cntt.controller.APIEntity.Khoa;
import vn.bkdn.cntt.entity.DMDonVi;
import vn.bkdn.cntt.entity.DMGiangDuong;
import vn.bkdn.cntt.entity.DMLoaiPhong;

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
}
