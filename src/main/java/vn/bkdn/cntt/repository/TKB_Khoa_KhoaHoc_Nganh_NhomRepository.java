package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc_Nganh_Nhom;

import java.util.List;

/**
 * Created by XuanVinh on 9/2/2017.
 */

@Repository
public interface TKB_Khoa_KhoaHoc_Nganh_NhomRepository extends JpaRepository<TKB_Khoa_KhoaHoc_Nganh_Nhom, Integer> {
    @Query(value = "select * from tkb_khoa_khoa_hoc_nganh_nhom where tkb_khoa_khoa_hoc_id = ?1", nativeQuery = true)
    List<TKB_Khoa_KhoaHoc_Nganh_Nhom> findByKhoaKhoaHoc(int khoaKhoaHocId);

    @Query(value = "select * from tkb_khoa_khoa_hoc_nganh_nhom where tkb_khoa_khoa_hoc_nganh_id = ?1", nativeQuery = true)
    List<TKB_Khoa_KhoaHoc_Nganh_Nhom> findByKhoaKhoaHocNganh(int khoaKhoaHocNganhId);
}
