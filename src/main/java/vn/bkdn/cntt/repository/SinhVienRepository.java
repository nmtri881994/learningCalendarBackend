package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMSinhVien;

/**
 * Created by Tri on 3/15/2017.
 */

@Repository
public interface SinhVienRepository extends JpaRepository<DMSinhVien, Integer> {
    DMSinhVien findByMaSinhVien(String maSinhVien);

}
