package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMNhanVien;

/**
 * Created by Tri on 3/24/2017.
 */

@Repository
public interface NhanVienRepository extends JpaRepository<DMNhanVien, Integer>{
    DMNhanVien findByMaNhanVien(String maNhanVien);
}
