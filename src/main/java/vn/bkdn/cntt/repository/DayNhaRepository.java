package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DayNha;

/**
 * Created by XuanVinh on 4/9/2017.
 */

@Repository
public interface DayNhaRepository extends JpaRepository<DayNha, Integer> {
}
