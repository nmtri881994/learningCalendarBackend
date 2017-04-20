package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.RegisterTime;

import javax.transaction.Transactional;

/**
 * Created by XuanVinh on 4/20/2017.
 */

@Repository
public interface RegisterTimeRepository extends JpaRepository<RegisterTime, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update register_time set status = ?2 where id = ?1", nativeQuery = true)
    void updateRegistering(int registerTimeId, boolean status);
}
