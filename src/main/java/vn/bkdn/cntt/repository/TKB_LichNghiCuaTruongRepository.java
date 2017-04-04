package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_LichNghiCuaTruong;

import java.sql.Date;

/**
 * Created by Tri on 4/4/2017.
 */

@Repository
public interface TKB_LichNghiCuaTruongRepository extends JpaRepository<TKB_LichNghiCuaTruong, Integer> {
    TKB_LichNghiCuaTruong findByNgay(Date ngay);
}
