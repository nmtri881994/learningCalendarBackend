package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMNganh;

import javax.transaction.Transactional;

/**
 * Created by Tri on 7/30/2017.
 */

@Repository
public interface DMNganhRepository extends JpaRepository<DMNganh, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update dmnganh set ten = ?2 where id = ?1", nativeQuery = true)
    void editNganh(int id, String ten);
}
