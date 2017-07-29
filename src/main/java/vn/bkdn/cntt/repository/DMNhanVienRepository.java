package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMNhanVien;

import javax.transaction.Transactional;

/**
 * Created by Tri on 3/24/2017.
 */

@Repository
public interface DMNhanVienRepository extends JpaRepository<DMNhanVien, Integer>{
    DMNhanVien findByMaNhanVien(String maNhanVien);

    @Modifying
    @Transactional
    @Query(value = "update dmnhan_vien set ma_nhan_vien = ?2, ho_dem = ?3, ten = ?4, dm_don_vi_id = ?5 where id = ?1", nativeQuery = true)
    void editNhanVien(int id, String maNhanVien, String hoDem, String ten, int  donViId);
}
