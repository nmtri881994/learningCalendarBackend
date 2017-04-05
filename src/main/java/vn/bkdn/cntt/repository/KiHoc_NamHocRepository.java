package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.KiHoc_NamHoc;

/**
 * Created by Tri on 4/5/2017.
 */

@Repository
public interface KiHoc_NamHocRepository extends JpaRepository<KiHoc_NamHoc, Integer> {

    @Query(value = "select * from ki_hoc_nam_hoc where ki_hoc_id = ?1 and nam_hoc_id = ?2", nativeQuery = true)
    KiHoc_NamHoc findKiHocNamHocByKyHocIdAndNamHocId(int kiHocId, int namHocId);
}
