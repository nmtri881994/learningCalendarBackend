package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;
import vn.bkdn.cntt.entity.TaiKhoanHeThong_VaiTro;

import java.util.List;

/**
 * Created by XuanVinh on 3/19/2017.
 */

@Repository
public interface TaiKhoanHeThong_VaiTroRepository extends JpaRepository<TaiKhoanHeThong_VaiTro, Integer>{
    List<TaiKhoanHeThong_VaiTro> findByTaiKhoanHeThong(TaiKhoanHeThong taiKhoanHeThong);
}
