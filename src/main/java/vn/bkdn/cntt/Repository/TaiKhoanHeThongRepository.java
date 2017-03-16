package vn.bkdn.cntt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;

/**
 * Created by Tri on 3/15/2017.
 */

@Repository
public interface TaiKhoanHeThongRepository extends JpaRepository<TaiKhoanHeThong, Integer>{
    TaiKhoanHeThong findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
}
