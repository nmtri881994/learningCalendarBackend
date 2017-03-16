package vn.bkdn.cntt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.TaiKhoanHeThongService;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;
import vn.bkdn.cntt.security.JwtAuthenticationRequest;
import vn.bkdn.cntt.security.JwtAuthenticationResponse;
import vn.bkdn.cntt.security.JwtTokenUtil;

import javax.validation.Valid;

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

    @PostMapping
    private ResponseEntity<?> getLoginToken(@Valid @RequestBody JwtAuthenticationRequest authenticationRequest){
        TaiKhoanHeThong taiKhoanHeThong = taiKhoanHeThongService.findByTenDangNhapAndMatKhau(authenticationRequest.getTenDangNhap(), authenticationRequest.getMatKhau());
        if(taiKhoanHeThong == null){
            throw new AuthenticationCredentialsNotFoundException("Not Found Account");
        }else{
            final UserDetails userDetails = userDetailsService.loadUserByUsername(taiKhoanHeThong.getTenDangNhap());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return new ResponseEntity<Object>(new JwtAuthenticationResponse(token), HttpStatus.OK);
        }
    }
}
