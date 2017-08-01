package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_KiHoc;

/**
 * Created by Tri on 7/28/2017.
 */

@Repository
public interface TKB_KiHocRepository extends JpaRepository<TKB_KiHoc, Integer> {
}
