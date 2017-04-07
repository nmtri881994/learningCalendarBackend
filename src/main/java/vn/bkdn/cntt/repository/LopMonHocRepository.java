package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select * from lop_mon_hoc where ki_hoc_nam_hoc_id = ?1 and khoa_khoa_hoc_id = ?2 and nganh_id = ?3", nativeQuery = true)
    List<LopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(int ki_namHocId, int khoa_khoaHocId, int nganhId);

    @Query(value = "select * from lop_mon_hoc where ki_hoc_nam_hoc_id = ?1 and khoa_khoa_hoc_id = ?2", nativeQuery = true)
    List<LopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocId(int ki_namHocId, int khoa_khoaHocId);
}
