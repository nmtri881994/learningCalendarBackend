package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc_Nganh;

import javax.transaction.Transactional;

/**
 * Created by Tri on 7/30/2017.
 */

@Repository
public interface TKB_Khoa_KhoaHoc_NganhRepository extends JpaRepository<TKB_Khoa_KhoaHoc_Nganh, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update tkb_khoa_khoa_hoc_nganh set tkb_khoa_khoa_hoc_id = ?2, dm_nganh_id = ?3 where id = ?1", nativeQuery = true)
    void editKhoaHoc(int id, int khoaKhoaHocId, int nganhId);

    @Query(value = "select * from tkb_khoa_khoa_hoc_nganh where tkb_khoa_khoa_hoc_id = ?1 and dm_nganh_id = ?2", nativeQuery = true)
    TKB_Khoa_KhoaHoc_Nganh findByKhoaKhoaHocIdAndNganhId(int khoaKhoaHocId, int nganhId);
}
