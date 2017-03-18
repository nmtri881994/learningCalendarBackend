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
import vn.bkdn.cntt.Service.TaiKhoanHeThongService;
import vn.bkdn.cntt.Service.VaiTroService;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;
import vn.bkdn.cntt.entity.VaiTro;
import vn.bkdn.cntt.security.JwtAuthenticationRequest;
import vn.bkdn.cntt.security.JwtAuthenticationResponse;
import vn.bkdn.cntt.security.JwtTokenUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */

@RestController
@RequestMapping(value = "api/login")
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TaiKhoanHeThongService taiKhoanHeThongService;

    @Autowired
    private VaiTroService vaiTroService;

    @PostMapping
    private ResponseEntity<?> getLoginToken(@Valid @RequestBody JwtAuthenticationRequest authenticationRequest){
        System.out.println(authenticationRequest.getTenDangNhap()+"-"+authenticationRequest.getMatKhau());
        TaiKhoanHeThong taiKhoanHeThong = taiKhoanHeThongService.findByTenDangNhapAndMatKhau(authenticationRequest.getTenDangNhap(), authenticationRequest.getMatKhau());
        if(taiKhoanHeThong == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            List<VaiTro> vaiTros = taiKhoanHeThongService.getAllVaiTroByTaiKhoanId(taiKhoanHeThong.getId());
            VaiTro inputVaiTro = null;
            if("sinh_vien".equals(authenticationRequest.getRole())){
                inputVaiTro = vaiTroService.getVaiTroByName("sinh_vien");
            }else if("giang_vien".equals(authenticationRequest.getRole())){
                inputVaiTro = vaiTroService.getVaiTroByName("giang_vien");
            }else if("giao_vu".equals(authenticationRequest.getRole())){
                inputVaiTro = vaiTroService.getVaiTroByName("giao_vu");
            }

            if(vaiTros.contains(inputVaiTro)){
                final UserDetails userDetails = userDetailsService.loadUserByUsername(taiKhoanHeThong.getTenDangNhap());
                final String token = jwtTokenUtil.generateToken(userDetails);
                System.out.println("token: "+token);
                return new ResponseEntity<JwtAuthenticationResponse>(new JwtAuthenticationResponse(token), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

        }
    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping(value = "/check-login")
//    public void checkLogin(@RequestPart("role") String role){
//        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println("Ten dang nhap: "+tenDangNhap);
//        System.out.print("Role: "+role);
//    }
}
