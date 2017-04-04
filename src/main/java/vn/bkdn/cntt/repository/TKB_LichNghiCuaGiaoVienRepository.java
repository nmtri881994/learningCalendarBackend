package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_LichNghiCuaGiaoVien;

import java.sql.Date;
import java.util.List;

/**
 * Created by Tri on 4/4/2017.
 */

@Repository
public interface TKB_LichNghiCuaGiaoVienRepository extends JpaRepository<TKB_LichNghiCuaGiaoVien, Integer>{
    TKB_LichNghiCuaGiaoVien findByNgay(Date ngay);
}
