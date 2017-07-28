package vn.bkdn.cntt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.DMSinhVienService;
import vn.bkdn.cntt.Service.TK_TaiKhoanHeThongService;
import vn.bkdn.cntt.entity.TKB_ThoiGianDangKy;
import vn.bkdn.cntt.entity.DMSinhVien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private TK_TaiKhoanHeThongService taiKhoanHeThongService;

    @Autowired
    private DMSinhVienService sinhVienService;

    @PreAuthorize("hasRole('SINHVIEN')")
    @GetMapping(value = "/sinhvien-authen")
    public ResponseEntity<?> checkSinhVienLogin(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIANGVIEN')")
    @GetMapping(value = "/giangvien-authen")
    public ResponseEntity<?> checkGiangVienLogin(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/giaovu-authen")
    public ResponseEntity<?> checkGiaoVuLogin(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/user-name")
    public String getTaiKhoanHeThongInfo(){
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        return taiKhoanHeThongService.findByTenDangNhap(tenDangNhap).getHoVaTen();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/account-name")
    public String getTenDangNhap(){
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        return tenDangNhap;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/sinh-vien/register-times")
    public ResponseEntity<List<TKB_ThoiGianDangKy>> getSinhVien(){
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        DMSinhVien dmSinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);
        List<TKB_ThoiGianDangKy> tkbThoiGianDangKies = new ArrayList<>(dmSinhVien.getDmLopHoc().getTkb_khoa_khoaHoc().getTkbThoiGianDangKies());
        return new ResponseEntity<List<TKB_ThoiGianDangKy>>(tkbThoiGianDangKies, HttpStatus.OK);
    }
}
