package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.entity.TKB_LichNghiCuaNhanVien;

import java.sql.Date;

/**
 * Created by Tri on 4/4/2017.
 */

@Repository
public interface TKB_LichNghiCuaNhanVienRepository extends JpaRepository<TKB_LichNghiCuaNhanVien, Integer>{
    TKB_LichNghiCuaNhanVien findByDmNhanVienAndNgay(DMNhanVien dmNhanVien, Date ngay);
}
