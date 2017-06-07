package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong;

/**
 * Created by Tri on 3/15/2017.
 */

@Repository
public interface TaiKhoanHeThongRepository extends JpaRepository<TK_TaiKhoanHeThong, Integer>{
    TK_TaiKhoanHeThong findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    TK_TaiKhoanHeThong findByTenDangNhap(String tenDangNhap);
}
