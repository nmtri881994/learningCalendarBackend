package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_ThoiGianDangKy;

import javax.transaction.Transactional;

/**
 * Created by XuanVinh on 4/20/2017.
 */

@Repository
public interface TKB_ThoiGianDangKyRepository extends JpaRepository<TKB_ThoiGianDangKy, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update tkb_thoi_gian_dang_ky set status = ?2 where id = ?1", nativeQuery = true)
    void updateRegistering(int registerTimeId, boolean status);
}
