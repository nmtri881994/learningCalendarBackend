package vn.bkdn.cntt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.SinhVien;

/**
 * Created by Tri on 3/15/2017.
 */

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Integer> {
    SinhVien findById(int sinhVienId);
}
