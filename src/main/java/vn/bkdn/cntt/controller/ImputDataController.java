package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private DMLopHocService dmLopHocService;

    @Autowired
    private DMNhanVienService dmNhanVienService;

    @Autowired
    private TK_TaiKhoanHeThongService tk_taiKhoanHeThongService;

    @Autowired
    private TK_TaiKhoanHeThong_VaiTroService tk_taiKhoanHeThong_vaiTroService;

    @Autowired
    private TK_VaiTroService tk_vaiTroService;

    @Autowired
    private DMNganhService dmNganhService;

    @Autowired
    private TKB_Khoa_KhoaHoc_NganhService tkb_khoa_khoaHoc_nganhService;

    @Autowired
    private DMSinhVienService dmSinhVienService;

    @Autowired
    private DMMonHocService dmMonHocService;

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
            if (dmDonVi.getDmLoaiDonVi().getId() == 1) {
                khoas.add(new Khoa(dmDonVi.getId(), dmDonVi.getMa(), dmDonVi.getTen()));
            }
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

    //khoa - khoa hoc

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
            for (int j = i + 1; j < khoaKhoaHocs.size(); j++) {
                if (khoaKhoaHocs.get(i).getKhoa().getMa().compareTo(khoaKhoaHocs.get(j).getKhoa().getMa()) > 0) {
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
            for (int j = i + 1; j < khoaKhoaHocs.size(); j++) {
                if (khoaKhoaHocs.get(i).getKhoa().getMa().compareTo(khoaKhoaHocs.get(j).getKhoa().getMa()) > 0) {
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
            for (int j = i + 1; j < khoaKhoaHocs.size(); j++) {
                if (khoaKhoaHocs.get(i).getKhoa().getMa().compareTo(khoaKhoaHocs.get(j).getKhoa().getMa()) > 0) {
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
    public ResponseEntity<List<KhoaKhoaHoc>> deleteKhoaKhoaHoc(@PathVariable int khoaKhoaHocId) {

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
            for (int j = i + 1; j < khoaKhoaHocs.size(); j++) {
                if (khoaKhoaHocs.get(i).getKhoa().getMa().compareTo(khoaKhoaHocs.get(j).getKhoa().getMa()) > 0) {
                    KhoaKhoaHoc khoaKhoaHoc1 = new KhoaKhoaHoc(khoaKhoaHocs.get(i));
                    khoaKhoaHocs.set(i, khoaKhoaHocs.get(j));
                    khoaKhoaHocs.set(j, khoaKhoaHoc1);
                }
            }
        }

        return new ResponseEntity<List<KhoaKhoaHoc>>(khoaKhoaHocs, HttpStatus.OK);
    }

    //Lop hoc

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-lop-hoc")
    public ResponseEntity<List<LopHoc>> getAllLopHoc() {
        List<DMLopHoc> dmLopHocs = dmLopHocService.findAll();
        List<LopHoc> lopHocs = new ArrayList<>();

        for (DMLopHoc dmLopHoc :
                dmLopHocs) {
            lopHocs.add(new LopHoc(dmLopHoc));
        }

        lopHocs.sort(Comparator.comparing(LopHoc::getMa));
        return new ResponseEntity<List<LopHoc>>(lopHocs, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/lop-hoc")
    public ResponseEntity<List<LopHoc>> insertLopHoc(@RequestBody LopHoc lopHoc) {

        dmLopHocService.insertLopHoc(new DMLopHoc(lopHoc.getId(), lopHoc.getMa(), lopHoc.getTen(),
                tkb_khoa_khoaHocService.findOne(lopHoc.getKhoaKhoaHoc().getId())));

        List<DMLopHoc> dmLopHocs = dmLopHocService.findAll();
        List<LopHoc> lopHocs = new ArrayList<>();

        for (DMLopHoc dmLopHoc :
                dmLopHocs) {
            lopHocs.add(new LopHoc(dmLopHoc));
        }

        lopHocs.sort(Comparator.comparing(LopHoc::getMa));
        return new ResponseEntity<List<LopHoc>>(lopHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/lop-hoc/edit")
    public ResponseEntity<List<LopHoc>> editLopHoc(@RequestBody LopHoc lopHoc) {

        dmLopHocService.editLopHoc(lopHoc);

        List<DMLopHoc> dmLopHocs = dmLopHocService.findAll();
        List<LopHoc> lopHocs = new ArrayList<>();

        for (DMLopHoc dmLopHoc :
                dmLopHocs) {
            lopHocs.add(new LopHoc(dmLopHoc));
        }

        lopHocs.sort(Comparator.comparing(LopHoc::getMa));
        return new ResponseEntity<List<LopHoc>>(lopHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/lop-hoc/delete/{lopHocId}")
    public ResponseEntity<List<LopHoc>> deleteLopHoc(@PathVariable int lopHocId) {

        dmLopHocService.deleteLopHoc(lopHocId);

        List<DMLopHoc> dmLopHocs = dmLopHocService.findAll();
        List<LopHoc> lopHocs = new ArrayList<>();

        for (DMLopHoc dmLopHoc :
                dmLopHocs) {
            lopHocs.add(new LopHoc(dmLopHoc));
        }

        lopHocs.sort(Comparator.comparing(LopHoc::getMa));
        return new ResponseEntity<List<LopHoc>>(lopHocs, HttpStatus.OK);
    }

    //Nhan vien

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-nhan-vien")
    public ResponseEntity<List<NhanVien>> getAllNhanVien() {
        List<DMNhanVien> dmNhanViens = dmNhanVienService.findAll();
        List<NhanVien> nhanViens = new ArrayList<>();
        for (DMNhanVien dmNhanVien :
                dmNhanViens) {
            nhanViens.add(new NhanVien(dmNhanVien));
        }
        nhanViens.sort(Comparator.comparing(NhanVien::getTen));
        return new ResponseEntity<List<NhanVien>>(nhanViens, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/nhan-vien")
    public ResponseEntity<List<NhanVien>> insertNhanVien(@RequestBody NhanVien nhanVien) {

        DMNhanVien dmNhanVien1 = dmNhanVienService.findByMaNhanVien(nhanVien.getMaNhanVien());
        if (dmNhanVien1 != null) {
            return new ResponseEntity<List<NhanVien>>(HttpStatus.CONFLICT);

        } else {
            DMDonVi dmDonVi = dmDonViService.findOne(nhanVien.getDmDonVi().getId());
            dmNhanVienService.insertNhanVien(new DMNhanVien(nhanVien.getId(), nhanVien.getMaNhanVien(), nhanVien.getHoDem(), nhanVien.getTen(), dmDonVi));

            tk_taiKhoanHeThongService.insertTK(new TK_TaiKhoanHeThong(nhanVien.getId(), nhanVien.getMaNhanVien(),
                    nhanVien.getMaNhanVien(), nhanVien.getHoDem() + " " + nhanVien.getTen()));

            List<DMNhanVien> dmNhanViens = dmNhanVienService.findAll();
            List<NhanVien> nhanViens = new ArrayList<>();
            for (DMNhanVien dmNhanVien :
                    dmNhanViens) {
                nhanViens.add(new NhanVien(dmNhanVien));
            }
            nhanViens.sort(Comparator.comparing(NhanVien::getTen));
            return new ResponseEntity<List<NhanVien>>(nhanViens, HttpStatus.OK);
        }


    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/nhan-vien/edit")
    public ResponseEntity<List<NhanVien>> editNhanVien(@RequestBody NhanVien nhanVien) {
        DMNhanVien dmNhanVien1 = dmNhanVienService.findByMaNhanVien(nhanVien.getMaNhanVien());
        DMNhanVien dmNhanVien2 = dmNhanVienService.findOne(nhanVien.getId());
        if (dmNhanVien1 != null && dmNhanVien1.getId() != dmNhanVien2.getId()) {
            return new ResponseEntity<List<NhanVien>>(HttpStatus.CONFLICT);
        } else {
            dmNhanVienService.editNhanVien(nhanVien);

            TK_TaiKhoanHeThong tk_taiKhoanHeThong = tk_taiKhoanHeThongService.findByTenDangNhap(nhanVien.getMaNhanVien());
            tk_taiKhoanHeThongService.editTK(tk_taiKhoanHeThong.getId(), nhanVien.getMaNhanVien(), nhanVien.getHoDem() + " " + nhanVien.getTen());

            List<DMNhanVien> dmNhanViens = dmNhanVienService.findAll();
            List<NhanVien> nhanViens = new ArrayList<>();
            for (DMNhanVien dmNhanVien :
                    dmNhanViens) {
                nhanViens.add(new NhanVien(dmNhanVien));
            }
            nhanViens.sort(Comparator.comparing(NhanVien::getTen));
            return new ResponseEntity<List<NhanVien>>(nhanViens, HttpStatus.OK);
        }

    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/nhan-vien/delete/{nhanVienId}")
    public ResponseEntity<List<NhanVien>> deleteNhanVien(@PathVariable int nhanVienId) {
        DMNhanVien dmNhanVien = dmNhanVienService.findOne(nhanVienId);

        dmNhanVienService.deleteNhanVien(nhanVienId);

        TK_TaiKhoanHeThong tk_taiKhoanHeThong = tk_taiKhoanHeThongService.findByTenDangNhap(dmNhanVien.getMaNhanVien());
        tk_taiKhoanHeThongService.deleteTK(tk_taiKhoanHeThong.getId());

        List<DMNhanVien> dmNhanViens = dmNhanVienService.findAll();
        List<NhanVien> nhanViens = new ArrayList<>();
        for (DMNhanVien dmNhanVien1 :
                dmNhanViens) {
            nhanViens.add(new NhanVien(dmNhanVien1));
        }
        nhanViens.sort(Comparator.comparing(NhanVien::getTen));
        return new ResponseEntity<List<NhanVien>>(nhanViens, HttpStatus.OK);
    }

    //Nhan vien - vai tro

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-nhan-vien-vai-tro")
    public ResponseEntity<List<TaiKhoanVaiTro>> getAllNhanVienVaiTro() {
        List<TK_TaiKhoanHeThong_VaiTro> tk_taiKhoanHeThong_vaiTros = tk_taiKhoanHeThong_vaiTroService.findAll();
        List<TaiKhoanVaiTro> taiKhoanVaiTros = new ArrayList<>();
        for (TK_TaiKhoanHeThong_VaiTro tk_taiKhoanHeThong_vaiTro :
                tk_taiKhoanHeThong_vaiTros) {
            if (checkNotSinhVien(tk_taiKhoanHeThong_vaiTro.getTk_taiKhoanHeThong())) {
                taiKhoanVaiTros.add(new TaiKhoanVaiTro(tk_taiKhoanHeThong_vaiTro));
            }
        }
        return new ResponseEntity<List<TaiKhoanVaiTro>>(taiKhoanVaiTros, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-vai-tro")
    public ResponseEntity<List<VaiTro>> getAllVaiTro() {
        List<TK_VaiTro> tk_vaiTros = tk_vaiTroService.findAll();
        List<VaiTro> vaiTros = new ArrayList<>();
        for (TK_VaiTro tk_vaiTro :
                tk_vaiTros) {
            //Bo vai tro sinh vien
            if (tk_vaiTro.getId() != 1) {
                vaiTros.add(new VaiTro(tk_vaiTro));
            }
        }

        vaiTros.sort(Comparator.comparing(VaiTro::getTenVaiTro));
        return new ResponseEntity<List<VaiTro>>(vaiTros, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-tai-khoan-nhan-vien")
    public ResponseEntity<List<TaiKhoan>> getAllTaiKhoanNhanVien() {
        List<TK_TaiKhoanHeThong> tk_taiKhoanHeThongs = tk_taiKhoanHeThongService.findAll();
        List<TaiKhoan> taiKhoans = new ArrayList<>();
        for (TK_TaiKhoanHeThong tk_taiKhoanHeThong :
                tk_taiKhoanHeThongs) {
            if (this.checkNotSinhVien(tk_taiKhoanHeThong)) {
                taiKhoans.add(new TaiKhoan(tk_taiKhoanHeThong));
            }
        }

        return new ResponseEntity<List<TaiKhoan>>(taiKhoans, HttpStatus.OK);
    }

    boolean checkNotSinhVien(TK_TaiKhoanHeThong tk_taiKhoanHeThong) {
        for (TK_TaiKhoanHeThong_VaiTro tk_taiKhoanHeThong_vaiTro :
                tk_taiKhoanHeThong.getTk_taiKhoanHeThong_vaiTros()) {
            if (tk_taiKhoanHeThong_vaiTro.getTk_vaiTro().getId() == 1) {
                return false;
            }
        }
        return true;
    }


    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/nhan-vien-vai-tro")
    public ResponseEntity<List<TaiKhoanVaiTro>> insertNhanVienVaiTro(@RequestBody TaiKhoanVaiTro taiKhoanVaiTro) {

        TK_TaiKhoanHeThong tk_taiKhoanHeThong = tk_taiKhoanHeThongService.findOne(taiKhoanVaiTro.getTaiKhoan().getId());
        List<TK_VaiTro> tk_vaiTrosCuaNhanVien = tk_taiKhoanHeThongService.getAllVaiTroByTaiKhoanId(tk_taiKhoanHeThong.getId());

        boolean vaiTroDaTonTai = false;
        for (TK_VaiTro aTk_vaiTrosCuaNhanVien : tk_vaiTrosCuaNhanVien) {
            if (aTk_vaiTrosCuaNhanVien.getId() == taiKhoanVaiTro.getVaiTro().getId()) {
                vaiTroDaTonTai = true;
                break;
            }
        }

        if (vaiTroDaTonTai) {
            return new ResponseEntity<List<TaiKhoanVaiTro>>(HttpStatus.CONFLICT);
        } else {
            TK_VaiTro tk_vaiTro = tk_vaiTroService.findOne(taiKhoanVaiTro.getVaiTro().getId());
            tk_taiKhoanHeThong_vaiTroService.insertTKVT(new TK_TaiKhoanHeThong_VaiTro(taiKhoanVaiTro.getId(), tk_taiKhoanHeThong, tk_vaiTro));

            List<TK_TaiKhoanHeThong_VaiTro> tk_taiKhoanHeThong_vaiTros = tk_taiKhoanHeThong_vaiTroService.findAll();
            List<TaiKhoanVaiTro> taiKhoanVaiTros = new ArrayList<>();
            for (TK_TaiKhoanHeThong_VaiTro tk_taiKhoanHeThong_vaiTro :
                    tk_taiKhoanHeThong_vaiTros) {
                if (checkNotSinhVien(tk_taiKhoanHeThong_vaiTro.getTk_taiKhoanHeThong())) {
                    taiKhoanVaiTros.add(new TaiKhoanVaiTro(tk_taiKhoanHeThong_vaiTro));
                }
            }
            return new ResponseEntity<List<TaiKhoanVaiTro>>(taiKhoanVaiTros, HttpStatus.OK);

        }


    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/nhan-vien-vai-tro/delete/{nhanVienVaiTroId}")
    public ResponseEntity<List<TaiKhoanVaiTro>> deleteNhanVienVaiTro(@PathVariable int nhanVienVaiTroId) {

        tk_taiKhoanHeThong_vaiTroService.deleteTKVT(nhanVienVaiTroId);

        List<TK_TaiKhoanHeThong_VaiTro> tk_taiKhoanHeThong_vaiTros = tk_taiKhoanHeThong_vaiTroService.findAll();
        List<TaiKhoanVaiTro> taiKhoanVaiTros = new ArrayList<>();
        for (TK_TaiKhoanHeThong_VaiTro tk_taiKhoanHeThong_vaiTro :
                tk_taiKhoanHeThong_vaiTros) {
            if (checkNotSinhVien(tk_taiKhoanHeThong_vaiTro.getTk_taiKhoanHeThong())) {
                taiKhoanVaiTros.add(new TaiKhoanVaiTro(tk_taiKhoanHeThong_vaiTro));
            }
        }
        return new ResponseEntity<List<TaiKhoanVaiTro>>(taiKhoanVaiTros, HttpStatus.OK);
    }

    //nganh

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-nganh")
    public ResponseEntity<List<DMNganh>> getAllNganh() {
        List<DMNganh> dmNganhs = dmNganhService.findAll();
        dmNganhs.sort(Comparator.comparing(DMNganh::getTen));
        return new ResponseEntity<List<DMNganh>>(dmNganhs, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/nganh")
    public ResponseEntity<List<DMNganh>> insertNganh(@RequestBody DMNganh dmNganh) {
        dmNganhService.insertNganh(dmNganh);

        List<DMNganh> dmNganhs = dmNganhService.findAll();
        dmNganhs.sort(Comparator.comparing(DMNganh::getTen));
        return new ResponseEntity<List<DMNganh>>(dmNganhs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/nganh/edit")
    public ResponseEntity<List<DMNganh>> editNganh(@RequestBody DMNganh dmNganh) {
        dmNganhService.editNganh(dmNganh);
        List<DMNganh> dmNganhs = dmNganhService.findAll();
        dmNganhs.sort(Comparator.comparing(DMNganh::getTen));
        return new ResponseEntity<List<DMNganh>>(dmNganhs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/nganh/delete/{nganhId}")
    public ResponseEntity<List<DMNganh>> deleteNganh(@PathVariable int nganhId) {
        dmNganhService.deleteNganh(nganhId);
        List<DMNganh> dmNganhs = dmNganhService.findAll();
        dmNganhs.sort(Comparator.comparing(DMNganh::getTen));
        return new ResponseEntity<List<DMNganh>>(dmNganhs, HttpStatus.OK);
    }

    //khoa - khoa hoc - nganh

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-khoa-khoa-hoc-nganh")
    public ResponseEntity<List<KhoaKhoaHocNganh>> getAllKhoaKhoaHocNganh() {
        List<TKB_Khoa_KhoaHoc_Nganh> tkb_khoa_khoaHoc_nganhs = tkb_khoa_khoaHoc_nganhService.findAll();
        List<KhoaKhoaHocNganh> khoaKhoaHocNganhs = new ArrayList<>();
        for (TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh :
                tkb_khoa_khoaHoc_nganhs) {
            khoaKhoaHocNganhs.add(new KhoaKhoaHocNganh(tkb_khoa_khoaHoc_nganh));
        }

        for (int i = 0; i < khoaKhoaHocNganhs.size() - 1; i++) {
            for (int j = i + 1; j < khoaKhoaHocNganhs.size(); j++) {
                if (khoaKhoaHocNganhs.get(i).getKhoaKhoaHoc().getKhoa().getTen().compareTo(khoaKhoaHocNganhs.get(j).getKhoaKhoaHoc().getKhoa().getTen()) > 0) {
                    KhoaKhoaHocNganh khoaKhoaHocNganh = khoaKhoaHocNganhs.get(i);
                    khoaKhoaHocNganhs.set(i, khoaKhoaHocNganhs.get(j));
                    khoaKhoaHocNganhs.set(j, khoaKhoaHocNganh);
                }
            }
        }

        return new ResponseEntity<List<KhoaKhoaHocNganh>>(khoaKhoaHocNganhs, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/khoa-khoa-hoc-nganh")
    public ResponseEntity<List<KhoaKhoaHocNganh>> insertKhoaKhoaHocNganh(@RequestBody KhoaKhoaHocNganh khoaKhoaHocNganh) {

        TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc = tkb_khoa_khoaHocService.findOne(khoaKhoaHocNganh.getKhoaKhoaHoc().getId());
        List<DMNganh> nganhsCuaKhoaKhoaHoc = new ArrayList<>();
        for (TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh :
                tkb_khoa_khoaHoc.getTKB_khoa_khoaHoc_nganhs()) {
            nganhsCuaKhoaKhoaHoc.add(tkb_khoa_khoaHoc_nganh.getDmNganh());
        }

        boolean nganhDaTonTai = false;
        for (DMNganh aNganhsCuaKhoaKhoaHoc : nganhsCuaKhoaKhoaHoc) {
            if (aNganhsCuaKhoaKhoaHoc.getId() == khoaKhoaHocNganh.getDmNganh().getId()) {
                nganhDaTonTai = true;
                break;
            }
        }

        if (nganhDaTonTai) {
            return new ResponseEntity<List<KhoaKhoaHocNganh>>(HttpStatus.CONFLICT);
        } else {
            tkb_khoa_khoaHoc_nganhService.insertKhoaKhoaHocNganh(new TKB_Khoa_KhoaHoc_Nganh(khoaKhoaHocNganh.getId(),
                    tkb_khoa_khoaHoc, khoaKhoaHocNganh.getDmNganh()));

            List<TKB_Khoa_KhoaHoc_Nganh> tkb_khoa_khoaHoc_nganhs = tkb_khoa_khoaHoc_nganhService.findAll();
            List<KhoaKhoaHocNganh> khoaKhoaHocNganhs = new ArrayList<>();
            for (TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh :
                    tkb_khoa_khoaHoc_nganhs) {
                khoaKhoaHocNganhs.add(new KhoaKhoaHocNganh(tkb_khoa_khoaHoc_nganh));
            }

            for (int i = 0; i < khoaKhoaHocNganhs.size() - 1; i++) {
                for (int j = i + 1; j < khoaKhoaHocNganhs.size(); j++) {
                    if (khoaKhoaHocNganhs.get(i).getKhoaKhoaHoc().getKhoa().getTen().compareTo(khoaKhoaHocNganhs.get(j).getKhoaKhoaHoc().getKhoa().getTen()) > 0) {
                        KhoaKhoaHocNganh khoaKhoaHocNganh1 = khoaKhoaHocNganhs.get(i);
                        khoaKhoaHocNganhs.set(i, khoaKhoaHocNganhs.get(j));
                        khoaKhoaHocNganhs.set(j, khoaKhoaHocNganh1);
                    }
                }
            }

            return new ResponseEntity<List<KhoaKhoaHocNganh>>(khoaKhoaHocNganhs, HttpStatus.OK);
        }


    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/khoa-khoa-hoc-nganh/delete/{khoaKhoaHocNganhId}")
    public ResponseEntity<List<KhoaKhoaHocNganh>> deleteKhoaKhoaHocNganh(@PathVariable int khoaKhoaHocNganhId) {

        tkb_khoa_khoaHoc_nganhService.deleteKhoaKhoaHocNganh(khoaKhoaHocNganhId);

        List<TKB_Khoa_KhoaHoc_Nganh> tkb_khoa_khoaHoc_nganhs = tkb_khoa_khoaHoc_nganhService.findAll();
        List<KhoaKhoaHocNganh> khoaKhoaHocNganhs = new ArrayList<>();
        for (TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh :
                tkb_khoa_khoaHoc_nganhs) {
            khoaKhoaHocNganhs.add(new KhoaKhoaHocNganh(tkb_khoa_khoaHoc_nganh));
        }

        for (int i = 0; i < khoaKhoaHocNganhs.size() - 1; i++) {
            for (int j = i + 1; j < khoaKhoaHocNganhs.size(); j++) {
                if (khoaKhoaHocNganhs.get(i).getKhoaKhoaHoc().getKhoa().getTen().compareTo(khoaKhoaHocNganhs.get(j).getKhoaKhoaHoc().getKhoa().getTen()) > 0) {
                    KhoaKhoaHocNganh khoaKhoaHocNganh = khoaKhoaHocNganhs.get(i);
                    khoaKhoaHocNganhs.set(i, khoaKhoaHocNganhs.get(j));
                    khoaKhoaHocNganhs.set(j, khoaKhoaHocNganh);
                }
            }
        }

        return new ResponseEntity<List<KhoaKhoaHocNganh>>(khoaKhoaHocNganhs, HttpStatus.OK);
    }

    //Sinh vien

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-sinh-vien")
    public ResponseEntity<List<SinhVien>> getAllSinhVien() {
        List<DMSinhVien> dmSinhViens = dmSinhVienService.findAll();
        List<SinhVien> sinhViens = new ArrayList<>();
        for (DMSinhVien dmSinhVien :
                dmSinhViens) {
            sinhViens.add(new SinhVien(dmSinhVien));
        }

        sinhViens.sort(Comparator.comparing(SinhVien::getTen));

        return new ResponseEntity<List<SinhVien>>(sinhViens, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/sinh-vien")
    public ResponseEntity<List<SinhVien>> insertSinhVien(@RequestBody SinhVien sinhVien) {

        DMSinhVien dmSinhVien1 = dmSinhVienService.findByMaSinhVien(sinhVien.getMaSinhVien());
        if (dmSinhVien1 != null) {
            return new ResponseEntity<List<SinhVien>>(HttpStatus.CONFLICT);
        } else {
            dmSinhVienService.insertSinhVien(new DMSinhVien(sinhVien.getId(), sinhVien.getMaSinhVien(), sinhVien.getHoDem(),
                    sinhVien.getTen(), dmLopHocService.findOne(sinhVien.getLopHoc().getId()), null));

            TK_TaiKhoanHeThong tk_taiKhoanHeThong = tk_taiKhoanHeThongService.insertTK(new TK_TaiKhoanHeThong(sinhVien.getId(),
                    sinhVien.getMaSinhVien(), sinhVien.getMaSinhVien(), sinhVien.getHoDem() + " " + sinhVien.getTen()));
            tk_taiKhoanHeThong_vaiTroService.insertTKVT(new TK_TaiKhoanHeThong_VaiTro(0, tk_taiKhoanHeThong, tk_vaiTroService.findOne(1)));

            List<DMSinhVien> dmSinhViens = dmSinhVienService.findAll();
            List<SinhVien> sinhViens = new ArrayList<>();
            for (DMSinhVien dmSinhVien :
                    dmSinhViens) {
                sinhViens.add(new SinhVien(dmSinhVien));
            }

            sinhViens.sort(Comparator.comparing(SinhVien::getTen));

            return new ResponseEntity<List<SinhVien>>(sinhViens, HttpStatus.OK);
        }


    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/sinh-vien/edit")
    public ResponseEntity<List<SinhVien>> editSinhVien(@RequestBody SinhVien sinhVien) throws InterruptedException {
        DMSinhVien dmSinhVien1 = dmSinhVienService.findOne(sinhVien.getId());
        DMSinhVien dmSinhVien2 = dmSinhVienService.findByMaSinhVien(sinhVien.getMaSinhVien());
        if (dmSinhVien2 != null && dmSinhVien1.getId() != dmSinhVien2.getId()) {
            return new ResponseEntity<List<SinhVien>>(HttpStatus.CONFLICT);
        } else {

            dmSinhVienService.editSinhVien(sinhVien);

            TK_TaiKhoanHeThong tk_taiKhoanHeThong = tk_taiKhoanHeThongService.findByTenDangNhap(sinhVien.getMaSinhVien());
            tk_taiKhoanHeThongService.editTK(tk_taiKhoanHeThong.getId(), sinhVien.getMaSinhVien(), sinhVien.getHoDem() + " " + sinhVien.getTen());
            List<DMSinhVien> dmSinhViens = dmSinhVienService.findAll2();
            List<SinhVien> sinhViens = new ArrayList<>();
            for (DMSinhVien dmSinhVien :
                    dmSinhViens) {
                sinhViens.add(new SinhVien(dmSinhVien));
            }

            sinhViens.sort(Comparator.comparing(SinhVien::getTen));

            return new ResponseEntity<List<SinhVien>>(sinhViens, HttpStatus.OK);
        }

    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/sinh-vien/delete/{sinhVienId}")
    public ResponseEntity<List<SinhVien>> deleteSinhVien(@PathVariable int sinhVienId) {

        dmSinhVienService.deleteSinhVien(sinhVienId);

        List<DMSinhVien> dmSinhViens = dmSinhVienService.findAll();
        List<SinhVien> sinhViens = new ArrayList<>();
        for (DMSinhVien dmSinhVien :
                dmSinhViens) {
            sinhViens.add(new SinhVien(dmSinhVien));
        }

        sinhViens.sort(Comparator.comparing(SinhVien::getTen));

        return new ResponseEntity<List<SinhVien>>(sinhViens, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-lop-hoc/{khoaId}/{khoaHocId}")
    public ResponseEntity<List<LopHoc>> deleteSinhVien(@PathVariable int khoaId, @PathVariable int khoaHocId) {
        TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc = tkb_khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        List<LopHoc> lopHocs = new ArrayList<>();
        if (tkb_khoa_khoaHoc != null) {
            for (DMLopHoc dmLopHoc :
                    tkb_khoa_khoaHoc.getDmLopHocs()) {
                lopHocs.add(new LopHoc(dmLopHoc));
            }
        }


        lopHocs.sort(Comparator.comparing(LopHoc::getMa));
        return new ResponseEntity<List<LopHoc>>(lopHocs, HttpStatus.OK);
    }

    //Môn học
    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-mon-hoc")
    public ResponseEntity<List<MonHoc>> getAllMonHoc() {
        List<DMMonHoc> dmMonHocs = dmMonHocService.findAll();
        List<MonHoc> monHocs = new ArrayList<>();
        for (DMMonHoc dmMonHoc :
                dmMonHocs) {
            monHocs.add(new MonHoc(dmMonHoc));
        }
        monHocs.sort(Comparator.comparing(MonHoc::getMaMonHoc));
        return new ResponseEntity<List<MonHoc>>(monHocs, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/mon-hoc")
    public ResponseEntity<List<MonHoc>> insertMonHoc(@RequestBody MonHoc monHoc) {
        dmMonHocService.insertMonHoc(new DMMonHoc(monHoc.getId(), monHoc.getMaMonHoc(), monHoc.getTen(), monHoc.getSoTinChi()));
        List<DMMonHoc> dmMonHocs = dmMonHocService.findAll();
        List<MonHoc> monHocs = new ArrayList<>();
        for (DMMonHoc dmMonHoc :
                dmMonHocs) {
            monHocs.add(new MonHoc(dmMonHoc));
        }
        monHocs.sort(Comparator.comparing(MonHoc::getMaMonHoc));
        return new ResponseEntity<List<MonHoc>>(monHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/mon-hoc/edit")
    public ResponseEntity<List<MonHoc>> editMonHoc(@RequestBody MonHoc monHoc) {
        dmMonHocService.editMonHoc(monHoc);
        List<DMMonHoc> dmMonHocs = dmMonHocService.findAll();
        List<MonHoc> monHocs = new ArrayList<>();
        for (DMMonHoc dmMonHoc :
                dmMonHocs) {
            monHocs.add(new MonHoc(dmMonHoc));
        }
        monHocs.sort(Comparator.comparing(MonHoc::getMaMonHoc));
        return new ResponseEntity<List<MonHoc>>(monHocs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/mon-hoc/delete/{monHocId}")
    public ResponseEntity<List<MonHoc>> deleteMonHoc(@PathVariable int monHocId) {
        dmMonHocService.deleteMonHoc(monHocId);
        List<DMMonHoc> dmMonHocs = dmMonHocService.findAll();
        List<MonHoc> monHocs = new ArrayList<>();
        for (DMMonHoc dmMonHoc :
                dmMonHocs) {
            monHocs.add(new MonHoc(dmMonHoc));
        }
        monHocs.sort(Comparator.comparing(MonHoc::getMaMonHoc));
        return new ResponseEntity<List<MonHoc>>(monHocs, HttpStatus.OK);
    }

    //Sinh vien - nganh

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/sinh-vien-nganh/edit/{sinhVienId}/{nganhId}")
    public ResponseEntity<List<SinhVien>> editSinhVienNganh(@PathVariable int sinhVienId, @PathVariable int nganhId) {

        DMSinhVien dmSinhVien1 = dmSinhVienService.findOne(sinhVienId);
        DMNganh dmNganh = dmNganhService.findOne(nganhId);
        dmSinhVien1.setDmNganh(dmNganh);

        dmSinhVienService.editSinhVien(new SinhVien(dmSinhVien1));

        List<DMSinhVien> dmSinhViens = dmSinhVienService.findAll();
        List<SinhVien> sinhViens = new ArrayList<>();
        for (DMSinhVien dmSinhVien :
                dmSinhViens) {
            sinhViens.add(new SinhVien(dmSinhVien));
        }

        sinhViens.sort(Comparator.comparing(SinhVien::getTen));

        return new ResponseEntity<List<SinhVien>>(sinhViens, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/sinh-vien-nganh/delete-nganh/{sinhVienId}")
    public ResponseEntity<List<SinhVien>> deleteSinhVienNganh(@PathVariable int sinhVienId) {
        DMSinhVien dmSinhVien1 = dmSinhVienService.findOne(sinhVienId);
        dmSinhVien1.setDmNganh(null);

        dmSinhVienService.editSinhVien(new SinhVien(dmSinhVien1));

        List<DMSinhVien> dmSinhViens = dmSinhVienService.findAll();
        List<SinhVien> sinhViens = new ArrayList<>();
        for (DMSinhVien dmSinhVien :
                dmSinhViens) {
            sinhViens.add(new SinhVien(dmSinhVien));
        }

        sinhViens.sort(Comparator.comparing(SinhVien::getTen));

        return new ResponseEntity<List<SinhVien>>(sinhViens, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/sinh-vien-nganh/get-all-nganh-of-sinh-vien/{sinhVienId}")
    public ResponseEntity<List<DMNganh>> getAllNganhOfSinhVien(@PathVariable int sinhVienId) {
        DMSinhVien dmSinhVien = dmSinhVienService.findOne(sinhVienId);
        DMLopHoc dmLopHoc = dmSinhVien.getDmLopHoc();
        TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc = dmLopHoc.getTkb_khoa_khoaHoc();
        List<DMNganh> dmNganhs = new ArrayList<>();
        for (TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh :
                tkb_khoa_khoaHoc.getTKB_khoa_khoaHoc_nganhs()) {
            dmNganhs.add(tkb_khoa_khoaHoc_nganh.getDmNganh());
        }

        return new ResponseEntity<List<DMNganh>>(dmNganhs, HttpStatus.OK);
    }
}

