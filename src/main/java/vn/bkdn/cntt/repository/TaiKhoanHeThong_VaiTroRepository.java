package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong_VaiTro;

import java.util.List;

/**
 * Created by XuanVinh on 3/19/2017.
 */

@Repository
public interface TaiKhoanHeThong_VaiTroRepository extends JpaRepository<TK_TaiKhoanHeThong_VaiTro, Integer>{

    @Query(value = "select * from tk_tai_khoan_he_thong_vai_tro where tk_tai_khoan_he_thong_id = ?1 ", nativeQuery = true)
    List<TK_TaiKhoanHeThong_VaiTro> findByTK_TaiKhoanHeThong(int tk_taiKhoanHeThongId);
}
