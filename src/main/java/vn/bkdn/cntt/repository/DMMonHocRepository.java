package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMMonHoc;

/**
 * Created by XuanVinh on 3/28/2017.
 */

@Repository
public interface DMMonHocRepository extends JpaRepository<DMMonHoc, Integer> {
}
