package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_KiHoc_NamHoc_DieuKien;

/**
 * Created by XuanVinh on 5/23/2017.
 */

@Repository
public interface TKB_KiHoc_NamHoc_DieuKienRepository extends JpaRepository<TKB_KiHoc_NamHoc_DieuKien, Integer> {
}
