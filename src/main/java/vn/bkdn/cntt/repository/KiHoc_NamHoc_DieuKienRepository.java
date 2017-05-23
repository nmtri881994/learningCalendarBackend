package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.KiHoc_NamHoc_DieuKien;

/**
 * Created by XuanVinh on 5/23/2017.
 */

@Repository
public interface KiHoc_NamHoc_DieuKienRepository extends JpaRepository<KiHoc_NamHoc_DieuKien, Integer> {
}
