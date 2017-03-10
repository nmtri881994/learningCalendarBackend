package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.Lop;

/**
 * Created by XuanVinh on 3/7/2017.
 */

@Repository
public interface LopRepository extends JpaRepository<Lop, Long>{
}
