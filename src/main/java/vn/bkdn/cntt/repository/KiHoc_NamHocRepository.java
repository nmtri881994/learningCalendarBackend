package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_KiHoc_NamHoc;

import javax.transaction.Transactional;

/**
 * Created by Tri on 4/5/2017.
 */

@Repository
public interface KiHoc_NamHocRepository extends JpaRepository<TKB_KiHoc_NamHoc, Integer> {

    @Query(value = "select * from tkb_ki_hoc_nam_hoc where tkb_ki_hoc_id = ?1 and tkb_nam_hoc_id = ?2", nativeQuery = true)
    TKB_KiHoc_NamHoc findKiHocNamHocByKyHocIdAndNamHocId(int kiHocId, int namHocId);

    @Query(value = "select tkb_nam_hoc_id from tkb_ki_hoc_nam_hoc where id = ?1", nativeQuery = true)
    int getNamHocId(int kiHoc_NamHocId);

    @Modifying
    @Transactional
    @Query(value = "update tkb_ki_hoc_nam_hoc set da_sinh_lich = 1 where id = ?1", nativeQuery = true)
    void setDaSinhLich(int kiHoc_NamHocId);
}
