package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.GiaoVien;
import vn.bkdn.cntt.entity.LopMonHoc;

import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */

@Repository
public interface LopMonHocRepository extends JpaRepository<LopMonHoc, Integer> {
    List<LopMonHoc> findByGiaoVien(GiaoVien giaoVien);
}
