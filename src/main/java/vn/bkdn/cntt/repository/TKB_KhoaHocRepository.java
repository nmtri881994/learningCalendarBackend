package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_KhoaHoc;

import javax.transaction.Transactional;

/**
 * Created by Tri on 7/27/2017.
 */

@Repository
public interface TKB_KhoaHocRepository extends JpaRepository<TKB_KhoaHoc, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update tkb_khoa_hoc set nam = ?2 where id = ?1", nativeQuery = true)
    void editKhoaHoc(int id, int nam);
}
