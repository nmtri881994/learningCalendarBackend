package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc;

/**
 * Created by XuanVinh on 4/6/2017.
 */

@Repository
public interface TKB_Khoa_KhoaHocRepository extends JpaRepository<TKB_Khoa_KhoaHoc, Integer> {

    @Query(value = "select * from tkb_khoa_khoa_hoc where dm_don_vi_id =?1 and tkb_khoa_hoc_id = ?2", nativeQuery = true)
    TKB_Khoa_KhoaHoc findByKhoaIdAndKhoaHocId(int khoaId, int khoaHocId);

    @Query(value = "select dm_don_vi_id from tkb_khoa_khoa_hoc where id = ?1", nativeQuery = true)
    int getKhoaId(int khoa_khoaHocId);
}
