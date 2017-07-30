package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong;

import javax.transaction.Transactional;

/**
 * Created by Tri on 3/15/2017.
 */

@Repository
public interface TK_TaiKhoanHeThongRepository extends JpaRepository<TK_TaiKhoanHeThong, Integer>{
    TK_TaiKhoanHeThong findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    TK_TaiKhoanHeThong findByTenDangNhap(String tenDangNhap);

    @Modifying
    @Transactional
    @Query(value = "update tk_tai_khoan_he_thong set ten_dang_nhap = ?2, ho_va_ten = ?3 where id = ?1", nativeQuery = true)
    void editTK(int id, String tenDangNhap, String hoVaTen);
}
