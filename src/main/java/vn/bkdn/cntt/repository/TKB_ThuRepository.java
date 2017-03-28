package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_Thu;

/**
 * Created by Tri on 3/28/2017.
 */

@Repository
public interface TKB_ThuRepository extends JpaRepository<TKB_Thu, Integer> {
    TKB_Thu findByTen(String ten);
}
