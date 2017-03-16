package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Security.JwtAuthenticationRequest;
import vn.bkdn.cntt.Security.JwtTokenUtil;
import vn.bkdn.cntt.Service.TaiKhoanHeThongService;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;

/**
 * Created by Tri on 3/15/2017.
 */

@RestController
@RequestMapping(value = "api/login")
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Autowired
    private TaiKhoanHeThongService taiKhoanHeThongService;

    @PostMapping
    private ResponseEntity<?> getLoginToken(@RequestBody JwtAuthenticationRequest authenticationRequest){
        TaiKhoanHeThong taiKhoanHeThong = taiKhoanHeThongService.findByTenDangNhapAndMatKhau(authenticationRequest.getTenDangNhap(), authenticationRequest.getMatKhau());
        if(taiKhoanHeThong == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            //ToDo
            return null;
        }
    }
}
