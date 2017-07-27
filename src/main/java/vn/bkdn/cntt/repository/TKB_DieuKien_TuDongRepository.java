package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_DieuKien_TuDong;

/**
 * Created by XuanVinh on 5/15/2017.
 */

@Repository
public interface TKB_DieuKien_TuDongRepository extends JpaRepository<TKB_DieuKien_TuDong, Integer> {
}
