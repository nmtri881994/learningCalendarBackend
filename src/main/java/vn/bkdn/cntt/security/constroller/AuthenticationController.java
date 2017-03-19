package vn.bkdn.cntt.security.constroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
 * Created by XuanVinh on 3/19/2017.
 */

@RestController
@RequestMapping(value = "/api/authen")
public class AuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TaiKhoanHeThongService taiKhoanHeThongService;

    @Autowired
    private VaiTroService vaiTroService;

    @PostMapping(value = "/login")
    private ResponseEntity<?> getLoginToken(@Valid @RequestBody JwtAuthenticationRequest authenticationRequest){
        System.out.println(authenticationRequest.getTenDangNhap()+"-"+authenticationRequest.getMatKhau());
        TaiKhoanHeThong taiKhoanHeThong = taiKhoanHeThongService.findByTenDangNhapAndMatKhau(authenticationRequest.getTenDangNhap(), authenticationRequest.getMatKhau());
        if(taiKhoanHeThong == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            List<VaiTro> vaiTros = taiKhoanHeThongService.getAllVaiTroByTaiKhoanId(taiKhoanHeThong.getId());
            VaiTro inputVaiTro = null;
            if("sinh_vien".equals(authenticationRequest.getRole())){
                inputVaiTro = vaiTroService.getVaiTroByName("ROLE_SINHVIEN");
            }else if("giang_vien".equals(authenticationRequest.getRole())){
                inputVaiTro = vaiTroService.getVaiTroByName("ROLE_GIANGVIEN");
            }else if("giao_vu".equals(authenticationRequest.getRole())){
                inputVaiTro = vaiTroService.getVaiTroByName("ROLE_GIAOVU");
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
}
