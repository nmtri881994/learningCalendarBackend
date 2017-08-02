package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMMonHoc_GiangDuong;

/**
 * Created by XuanVinh on 8/2/2017.
 */
@Repository
public interface DMMonHoc_GiangDuongRepository extends JpaRepository<DMMonHoc_GiangDuong, Integer> {
}
