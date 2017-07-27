package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_NamHoc;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * Created by Tri on 3/21/2017.
 */

@Repository
public interface TKB_NamHocRepository extends JpaRepository<TKB_NamHoc, Integer> {

    @Query(value = "select * from tkb_nam_hoc nh where nh.ngay_ket_thuc > current_date()", nativeQuery = true)
    List<TKB_NamHoc> getYearsNotEnd();

    @Modifying
    @Transactional
    @Query(value = "update tkb_nam_hoc set name = ?2, ngay_bat_dau=?3, ngay_ket_thuc=?4 where id = ?1", nativeQuery = true)
    void editNamHoc(int id, String name, Date ngayBatDau, Date ngayKetThuc);
}
