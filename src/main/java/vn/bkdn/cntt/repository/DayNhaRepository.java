package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMLoaiPhong;

/**
 * Created by XuanVinh on 4/9/2017.
 */

@Repository
public interface DayNhaRepository extends JpaRepository<DMLoaiPhong, Integer> {
}
