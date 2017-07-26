package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMDonVi;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tri on 7/26/2017.
 */

@Repository
public interface DMDonViRepository extends JpaRepository<DMDonVi, Integer> {

    @Query(value = "select * from dmdon_vi where dm_loai_don_vi_id = 1", nativeQuery = true)
    List<DMDonVi> findAllFaculty();

    @Modifying
    @Transactional
    @Query(value = "update dmdon_vi set ma = ?2, ten = ?3 where id = ?1", nativeQuery = true)
    void editKhoa(int id, String ma, String ten);
}
