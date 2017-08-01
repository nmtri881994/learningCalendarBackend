package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMSinhVien;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */

@Repository
public interface DMSinhVienRepository extends JpaRepository<DMSinhVien, Integer> {
    DMSinhVien findByMaSinhVien(String maSinhVien);

    @Modifying
    @Transactional
    @Query(value = "update dmsinh_vien set ma_sinh_vien = ?2, ho_dem = ?3, ten = ?4, dm_lop_hoc_id = ?5 where id = ?1", nativeQuery = true)
    void editNhanVien(int id, String maSinhVien, String hoDem, String ten, int lopHocId);

    @Query(value = "select * from dmsinh_vien", nativeQuery = true)
    List<DMSinhVien> findAll2();
}
