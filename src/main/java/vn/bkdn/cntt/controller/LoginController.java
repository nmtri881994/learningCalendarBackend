package vn.bkdn.cntt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.SinhVienService;
import vn.bkdn.cntt.Service.TaiKhoanHeThongService;
import vn.bkdn.cntt.Service.VaiTroService;
import vn.bkdn.cntt.entity.RegisterTime;
import vn.bkdn.cntt.entity.SinhVien;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;
import vn.bkdn.cntt.entity.VaiTro;
import vn.bkdn.cntt.jsonEntity.CheckLogin;
import vn.bkdn.cntt.security.JwtAuthenticationRequest;
import vn.bkdn.cntt.security.JwtAuthenticationResponse;
import vn.bkdn.cntt.security.JwtTokenUtil;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private TaiKhoanHeThongService taiKhoanHeThongService;

    @Autowired
    private SinhVienService sinhVienService;

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
    public ResponseEntity<List<RegisterTime>> getSinhVien(){
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        SinhVien sinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);
        List<RegisterTime> registerTimes = new ArrayList<>(sinhVien.getLopHoc().getKhoa_khoaHoc().getRegisterTimes());
        return new ResponseEntity<List<RegisterTime>>(registerTimes, HttpStatus.OK);
    }
}
