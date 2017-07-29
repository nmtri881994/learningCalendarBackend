package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMLopHoc;

import javax.transaction.Transactional;

/**
 * Created by Tri on 7/29/2017.
 */

@Repository
public interface DMLopHocRepository extends JpaRepository<DMLopHoc, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update dmlop_hoc set ma = ?2, ten = ?3, tkb_khoa_khoa_hoc_id = ?4 where id = ?1", nativeQuery = true)
    void editLopHoc(int id, String ma, String ten, int khoaKhoaHocId);
}
