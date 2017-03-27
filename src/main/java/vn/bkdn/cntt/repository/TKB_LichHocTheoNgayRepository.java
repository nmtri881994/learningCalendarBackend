package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;

/**
 * Created by XuanVinh on 3/27/2017.
 */

@Repository
public interface TKB_LichHocTheoNgayRepository extends JpaRepository<TKB_LichHocTheoNgay, Integer>{
}
