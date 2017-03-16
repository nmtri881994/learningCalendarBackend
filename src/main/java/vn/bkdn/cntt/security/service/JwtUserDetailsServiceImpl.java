package vn.bkdn.cntt.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.Service.TaiKhoanHeThongService;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;
import vn.bkdn.cntt.security.JwtUser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TaiKhoanHeThongService taiKhoanHeThongService;

    @Override
    public UserDetails loadUserByUsername(String tenDangNhap) throws UsernameNotFoundException {
        TaiKhoanHeThong taiKhoanHeThong = taiKhoanHeThongService.findByTenDangNhap(tenDangNhap);
        if (taiKhoanHeThong == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", tenDangNhap));
        } else {

            return new JwtUser(
                    taiKhoanHeThong.getId(),
                    taiKhoanHeThong.getTenDangNhap(),
                    taiKhoanHeThong.getHoVaTen(),
                    this.mapToGrantedAuthorities(taiKhoanHeThong.getId()));
        }
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(int taiKhoanHeThongId) {
        return this.taiKhoanHeThongService.getAllVaiTroByTaiKhoanId(taiKhoanHeThongId)
                .stream()
                .map(x -> new SimpleGrantedAuthority(x.getTenVaiTro()))
                .collect(Collectors.toList());
    }
}
