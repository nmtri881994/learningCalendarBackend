package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.GiaoVien;

/**
 * Created by Tri on 3/24/2017.
 */

@Repository
public interface GiaoVienRepository extends JpaRepository<GiaoVien, Integer>{
    GiaoVien findByMaGiaoVien(String maGiaoVien);
}
