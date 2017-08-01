package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMMonHoc;

import javax.transaction.Transactional;

/**
 * Created by XuanVinh on 3/28/2017.
 */

@Repository
public interface DMMonHocRepository extends JpaRepository<DMMonHoc, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update dmmon_hoc set ma_mon_hoc = ?2, ten = ?3, so_tin_chi = ?4 where id = ?1", nativeQuery = true)
    void editMonHoc(int id, String maMonHoc, String ten, float soTinChi);
}
