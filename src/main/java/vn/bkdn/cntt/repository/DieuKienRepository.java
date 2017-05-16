package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DieuKien;

/**
 * Created by XuanVinh on 5/15/2017.
 */

@Repository
public interface DieuKienRepository extends JpaRepository<DieuKien, Integer> {
}
