package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMGiangDuong;

/**
 * Created by XuanVinh on 4/10/2017.
 */

@Repository
public interface GiangDuongRepository extends JpaRepository<DMGiangDuong, Integer> {
}
