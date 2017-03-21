package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.NamHoc;

/**
 * Created by Tri on 3/21/2017.
 */

@Repository
public interface NamHocRepository extends JpaRepository<NamHoc, Integer> {
}
