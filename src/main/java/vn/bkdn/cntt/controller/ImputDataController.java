package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.controller.APIEntity.*;
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
    private DMGiangDuongService giangDuongService;

    @Autowired
    private DMDayNhaService dayNhaService;

    @Autowired
    private TKB_KhoaHocService tkb_khoaHocService;

    @Autowired
    private TKB_NamHocService tkb_namHocService;

    @Autowired
    private TKB_KiHocService tkb_kiHocService;

    @Autowired
    private TKB_KiHoc_NamHocService tkb_kiHoc_namHocService;

    @Autowired
    private TKB_Khoa_KhoaHocService tkb_khoa_khoaHocService;

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
        for (TKB_NamHoc tkb_namHoc :
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
        for (TKB_NamHoc tkb_namHoc :
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
        for (TKB_NamHoc tkb_namHoc :
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
        for (TKB_NamHoc tkb_namHoc :
                tkb_namHocs) {
            namHocs.add(new NamHoc(tkb_namHoc.getId(), tkb_namHoc.getName(), tkb_namHoc.getNgayBatDau(), tkb_namHoc.getNgayKetThuc()));
        }
        return new ResponseEntity<List<NamHoc>>(namHocs, HttpStatus.OK);
    }

    //ki hoc - nam hoc

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-ki-hoc")
    public ResponseEntity<List<KiHoc>> getAllKiHoc() {
        List<TKB_KiHoc> tkb_kiHocs = tkb_kiHocService.findAll();
        List<KiHoc> kiHocs = new ArrayList<>();
        for (TKB_KiHoc tkb_kiHoc :
                tkb_kiHocs) {
            kiHocs.add(new KiHoc(tkb_kiHoc.getId(), tkb_kiHoc.getTen()));
        }

        kiHocs.sort(Comparator.comparing(KiHoc::getTen));
        return new ResponseEntity<List<KiHoc>>(kiHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-ki-hoc-nam-hoc")
    public ResponseEntity<List<KiHocNamHoc>> getAllKiHocNamHoc() {
        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = tkb_kiHoc_namHocService.findAll();
        List<KiHocNamHoc> kiHocNamHocs = new ArrayList<>();
        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocs) {
            kiHocNamHocs.add(new KiHocNamHoc(tkb_kiHoc_namHoc.getId(), new KiHoc(tkb_kiHoc_namHoc.getTkb_kiHoc().getId(),
                    tkb_kiHoc_namHoc.getTkb_kiHoc().getTen()), new NamHoc(tkb_kiHoc_namHoc.getTkb_namHoc().getId(),
                    tkb_kiHoc_namHoc.getTkb_namHoc().getName(), tkb_kiHoc_namHoc.getTkb_namHoc().getNgayBatDau(),
                    tkb_kiHoc_namHoc.getTkb_namHoc().getNgayKetThuc()), tkb_kiHoc_namHoc.getNgayBatDau(),
                    tkb_kiHoc_namHoc.getNgayKetThuc()));
        }

        kiHocNamHocs.sort(Comparator.comparing(KiHocNamHoc::getNgayBatDau));
        return new ResponseEntity<List<KiHocNamHoc>>(kiHocNamHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/ki-hoc-nam-hoc")
    public ResponseEntity<List<KiHocNamHoc>> insertKiHocNamHoc(@RequestBody KiHocNamHoc kiHocNamHoc) {
        TKB_KiHoc tkb_kiHoc = tkb_kiHocService.findOne(kiHocNamHoc.getKiHoc().getId());
        TKB_NamHoc tkb_namHoc = tkb_namHocService.findOne(kiHocNamHoc.getNamHoc().getId());

        tkb_kiHoc_namHocService.insertKiHocNamHoc(new TKB_KiHoc_NamHoc(kiHocNamHoc.getId(), tkb_kiHoc, tkb_namHoc,
                kiHocNamHoc.getNgayBatDau(), kiHocNamHoc.getNgayKetThuc(), false));

        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = tkb_kiHoc_namHocService.findAll();
        List<KiHocNamHoc> kiHocNamHocs = new ArrayList<>();
        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocs) {
            kiHocNamHocs.add(new KiHocNamHoc(tkb_kiHoc_namHoc.getId(), new KiHoc(tkb_kiHoc_namHoc.getTkb_kiHoc().getId(),
                    tkb_kiHoc_namHoc.getTkb_kiHoc().getTen()), new NamHoc(tkb_kiHoc_namHoc.getTkb_namHoc().getId(),
                    tkb_kiHoc_namHoc.getTkb_namHoc().getName(), tkb_kiHoc_namHoc.getTkb_namHoc().getNgayBatDau(),
                    tkb_kiHoc_namHoc.getTkb_namHoc().getNgayKetThuc()), tkb_kiHoc_namHoc.getNgayBatDau(),
                    tkb_kiHoc_namHoc.getNgayKetThuc()));
        }

        kiHocNamHocs.sort(Comparator.comparing(KiHocNamHoc::getNgayBatDau));
        return new ResponseEntity<List<KiHocNamHoc>>(kiHocNamHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/ki-hoc-nam-hoc/edit")
    public ResponseEntity<List<KiHocNamHoc>> editKiHocNamHoc(@RequestBody KiHocNamHoc kiHocNamHoc) {

        tkb_kiHoc_namHocService.editKiHocNamHoc(kiHocNamHoc.getId(), kiHocNamHoc.getNamHoc().getId(),
                kiHocNamHoc.getKiHoc().getId(), kiHocNamHoc.getNgayBatDau(), kiHocNamHoc.getNgayKetThuc());

        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = tkb_kiHoc_namHocService.findAll();
        List<KiHocNamHoc> kiHocNamHocs = new ArrayList<>();
        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocs) {
            kiHocNamHocs.add(new KiHocNamHoc(tkb_kiHoc_namHoc.getId(), new KiHoc(tkb_kiHoc_namHoc.getTkb_kiHoc().getId(),
                    tkb_kiHoc_namHoc.getTkb_kiHoc().getTen()), new NamHoc(tkb_kiHoc_namHoc.getTkb_namHoc().getId(),
                    tkb_kiHoc_namHoc.getTkb_namHoc().getName(), tkb_kiHoc_namHoc.getTkb_namHoc().getNgayBatDau(),
                    tkb_kiHoc_namHoc.getTkb_namHoc().getNgayKetThuc()), tkb_kiHoc_namHoc.getNgayBatDau(),
                    tkb_kiHoc_namHoc.getNgayKetThuc()));
        }

        kiHocNamHocs.sort(Comparator.comparing(KiHocNamHoc::getNgayBatDau));
        return new ResponseEntity<List<KiHocNamHoc>>(kiHocNamHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/ki-hoc-nam-hoc/delete/{kiHocNamHocId}")
    public ResponseEntity<List<KiHocNamHoc>> deleteKiHocNamHoc(@PathVariable int kiHocNamHocId) {

        tkb_kiHoc_namHocService.deleteKiHocNamHoc(kiHocNamHocId);

        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = tkb_kiHoc_namHocService.findAll();
        List<KiHocNamHoc> kiHocNamHocs = new ArrayList<>();
        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocs) {
            kiHocNamHocs.add(new KiHocNamHoc(tkb_kiHoc_namHoc.getId(), new KiHoc(tkb_kiHoc_namHoc.getTkb_kiHoc().getId(),
                    tkb_kiHoc_namHoc.getTkb_kiHoc().getTen()), new NamHoc(tkb_kiHoc_namHoc.getTkb_namHoc().getId(),
                    tkb_kiHoc_namHoc.getTkb_namHoc().getName(), tkb_kiHoc_namHoc.getTkb_namHoc().getNgayBatDau(),
                    tkb_kiHoc_namHoc.getTkb_namHoc().getNgayKetThuc()), tkb_kiHoc_namHoc.getNgayBatDau(),
                    tkb_kiHoc_namHoc.getNgayKetThuc()));
        }

        kiHocNamHocs.sort(Comparator.comparing(KiHocNamHoc::getNgayBatDau));
        return new ResponseEntity<List<KiHocNamHoc>>(kiHocNamHocs, HttpStatus.OK);
    }

    //nam hoc

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-khoa-khoa-hoc")
    public ResponseEntity<List<KhoaKhoaHoc>> getAllKhoaKhoaHoc() {
        List<TKB_Khoa_KhoaHoc> tkb_khoa_khoaHocs = tkb_khoa_khoaHocService.findAll();
        List<KhoaKhoaHoc> khoaKhoaHocs = new ArrayList<>();
        for (TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc :
                tkb_khoa_khoaHocs) {
            khoaKhoaHocs.add(new KhoaKhoaHoc(tkb_khoa_khoaHoc.getId(), new Khoa(tkb_khoa_khoaHoc.getDmDonVi().getId(),
                    tkb_khoa_khoaHoc.getDmDonVi().getMa(), tkb_khoa_khoaHoc.getDmDonVi().getTen()),
                    tkb_khoa_khoaHoc.getTkb_khoaHoc(), newKiHocNamHoc(tkb_khoa_khoaHoc.getKiPhanNganh()),
                    newKiHocNamHoc(tkb_khoa_khoaHoc.getKiBatDau()), newKiHocNamHoc(tkb_khoa_khoaHoc.getKiKetThuc())));
        }

        for (int i = 0; i < khoaKhoaHocs.size() - 1; i++) {
            for (int j = i+1; j < khoaKhoaHocs.size(); j++) {
                if(khoaKhoaHocs.get(i).getKhoa().getMa().compareTo(khoaKhoaHocs.get(j).getKhoa().getMa())>0){
                    KhoaKhoaHoc khoaKhoaHoc = new KhoaKhoaHoc(khoaKhoaHocs.get(i));
                    khoaKhoaHocs.set(i, khoaKhoaHocs.get(j));
                    khoaKhoaHocs.set(j, khoaKhoaHoc);
                }
            }
        }

        return new ResponseEntity<List<KhoaKhoaHoc>>(khoaKhoaHocs, HttpStatus.OK);
    }

    public KiHocNamHoc newKiHocNamHoc(TKB_KiHoc_NamHoc tkb_kiHoc_namHoc) {
        return new KiHocNamHoc(tkb_kiHoc_namHoc.getId(), new KiHoc(tkb_kiHoc_namHoc.getTkb_kiHoc().getId(),
                tkb_kiHoc_namHoc.getTkb_kiHoc().getTen()), new NamHoc(tkb_kiHoc_namHoc.getTkb_namHoc().getId(),
                tkb_kiHoc_namHoc.getTkb_namHoc().getName(), tkb_kiHoc_namHoc.getTkb_namHoc().getNgayBatDau(),
                tkb_kiHoc_namHoc.getTkb_namHoc().getNgayKetThuc()), tkb_kiHoc_namHoc.getNgayBatDau(),
                tkb_kiHoc_namHoc.getNgayKetThuc());
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/khoa-khoa-hoc")
    public ResponseEntity<List<KhoaKhoaHoc>> insertKhoaKhoaHoc(@RequestBody KhoaKhoaHoc khoaKhoaHoc) {
        DMDonVi dmDonVi = dmDonViService.findOne(khoaKhoaHoc.getKhoa().getId());

        tkb_khoa_khoaHocService.insertKhoaKhoaHoc(new TKB_Khoa_KhoaHoc(khoaKhoaHoc.getId(), dmDonVi, khoaKhoaHoc.getTkb_khoaHoc(),
                tkb_kiHoc_namHocService.findOne(khoaKhoaHoc.getKiPhanNganh().getId()),
                tkb_kiHoc_namHocService.findOne(khoaKhoaHoc.getKiBatDau().getId()),
                tkb_kiHoc_namHocService.findOne(khoaKhoaHoc.getKiKetThuc().getId())));

        List<TKB_Khoa_KhoaHoc> tkb_khoa_khoaHocs = tkb_khoa_khoaHocService.findAll();
        List<KhoaKhoaHoc> khoaKhoaHocs = new ArrayList<>();
        for (TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc :
                tkb_khoa_khoaHocs) {
            khoaKhoaHocs.add(new KhoaKhoaHoc(tkb_khoa_khoaHoc.getId(), new Khoa(tkb_khoa_khoaHoc.getDmDonVi().getId(),
                    tkb_khoa_khoaHoc.getDmDonVi().getMa(), tkb_khoa_khoaHoc.getDmDonVi().getTen()),
                    tkb_khoa_khoaHoc.getTkb_khoaHoc(), newKiHocNamHoc(tkb_khoa_khoaHoc.getKiPhanNganh()),
                    newKiHocNamHoc(tkb_khoa_khoaHoc.getKiBatDau()), newKiHocNamHoc(tkb_khoa_khoaHoc.getKiKetThuc())));
        }

        for (int i = 0; i < khoaKhoaHocs.size() - 1; i++) {
            for (int j = i+1; j < khoaKhoaHocs.size(); j++) {
                if(khoaKhoaHocs.get(i).getKhoa().getMa().compareTo(khoaKhoaHocs.get(j).getKhoa().getMa())>0){
                    KhoaKhoaHoc khoaKhoaHoc1 = new KhoaKhoaHoc(khoaKhoaHocs.get(i));
                    khoaKhoaHocs.set(i, khoaKhoaHocs.get(j));
                    khoaKhoaHocs.set(j, khoaKhoaHoc1);
                }
            }
        }

        return new ResponseEntity<List<KhoaKhoaHoc>>(khoaKhoaHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/khoa-khoa-hoc/edit")
    public ResponseEntity<List<KhoaKhoaHoc>> editKhoaKhoaHoc(@RequestBody KhoaKhoaHoc khoaKhoaHoc) {

        tkb_khoa_khoaHocService.editKhoaKhoaHoc(khoaKhoaHoc);

        List<TKB_Khoa_KhoaHoc> tkb_khoa_khoaHocs = tkb_khoa_khoaHocService.findAll();
        List<KhoaKhoaHoc> khoaKhoaHocs = new ArrayList<>();
        for (TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc :
                tkb_khoa_khoaHocs) {
            khoaKhoaHocs.add(new KhoaKhoaHoc(tkb_khoa_khoaHoc.getId(), new Khoa(tkb_khoa_khoaHoc.getDmDonVi().getId(),
                    tkb_khoa_khoaHoc.getDmDonVi().getMa(), tkb_khoa_khoaHoc.getDmDonVi().getTen()),
                    tkb_khoa_khoaHoc.getTkb_khoaHoc(), newKiHocNamHoc(tkb_khoa_khoaHoc.getKiPhanNganh()),
                    newKiHocNamHoc(tkb_khoa_khoaHoc.getKiBatDau()), newKiHocNamHoc(tkb_khoa_khoaHoc.getKiKetThuc())));
        }

        for (int i = 0; i < khoaKhoaHocs.size() - 1; i++) {
            for (int j = i+1; j < khoaKhoaHocs.size(); j++) {
                if(khoaKhoaHocs.get(i).getKhoa().getMa().compareTo(khoaKhoaHocs.get(j).getKhoa().getMa())>0){
                    KhoaKhoaHoc khoaKhoaHoc1 = new KhoaKhoaHoc(khoaKhoaHocs.get(i));
                    khoaKhoaHocs.set(i, khoaKhoaHocs.get(j));
                    khoaKhoaHocs.set(j, khoaKhoaHoc1);
                }
            }
        }

        return new ResponseEntity<List<KhoaKhoaHoc>>(khoaKhoaHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/khoa-khoa-hoc/delete/{khoaKhoaHocId}")
    public ResponseEntity<List<KhoaKhoaHoc>> KhoaKhoaNamHoc(@PathVariable int khoaKhoaHocId) {

        tkb_khoa_khoaHocService.deleteKhoaKhoaHoc(khoaKhoaHocId);

        List<TKB_Khoa_KhoaHoc> tkb_khoa_khoaHocs = tkb_khoa_khoaHocService.findAll();
        List<KhoaKhoaHoc> khoaKhoaHocs = new ArrayList<>();
        for (TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc :
                tkb_khoa_khoaHocs) {
            khoaKhoaHocs.add(new KhoaKhoaHoc(tkb_khoa_khoaHoc.getId(), new Khoa(tkb_khoa_khoaHoc.getDmDonVi().getId(),
                    tkb_khoa_khoaHoc.getDmDonVi().getMa(), tkb_khoa_khoaHoc.getDmDonVi().getTen()),
                    tkb_khoa_khoaHoc.getTkb_khoaHoc(), newKiHocNamHoc(tkb_khoa_khoaHoc.getKiPhanNganh()),
                    newKiHocNamHoc(tkb_khoa_khoaHoc.getKiBatDau()), newKiHocNamHoc(tkb_khoa_khoaHoc.getKiKetThuc())));
        }

        for (int i = 0; i < khoaKhoaHocs.size() - 1; i++) {
            for (int j = i+1; j < khoaKhoaHocs.size(); j++) {
                if(khoaKhoaHocs.get(i).getKhoa().getMa().compareTo(khoaKhoaHocs.get(j).getKhoa().getMa())>0){
                    KhoaKhoaHoc khoaKhoaHoc1 = new KhoaKhoaHoc(khoaKhoaHocs.get(i));
                    khoaKhoaHocs.set(i, khoaKhoaHocs.get(j));
                    khoaKhoaHocs.set(j, khoaKhoaHoc1);
                }
            }
        }

        return new ResponseEntity<List<KhoaKhoaHoc>>(khoaKhoaHocs, HttpStatus.OK);
    }
}
