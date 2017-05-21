package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.GiaoVien;
import vn.bkdn.cntt.entity.Khoa_KhoaHoc;
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

    @Query(value = "select * from lop_mon_hoc where giao_vien_id = ?1 and ki_hoc_nam_hoc_id = ?2", nativeQuery = true)
    List<LopMonHoc> findByGiaoVienIdAndNamHocKiHocId(int giaoVienId, int namHoc_KiHocId);

    @Query(value = "select * from lop_mon_hoc where khoa_khoa_hoc_id = ?1", nativeQuery = true)
    List<LopMonHoc> findByKhoa_KhoaHoc(int khoa_khoaHocId);

    @Query(value = "select * from lop_mon_hoc where ki_hoc_nam_hoc_id = ?1", nativeQuery = true)
    List<LopMonHoc> findByKiHoc_NamHocId(int ki_namHocId);

    @Query(value = "select * from lop_mon_hoc where mon_hoc_id=?1 and ki_hoc_nam_hoc_id = ?2", nativeQuery = true)
    List<LopMonHoc> findByMonHocIdAndKiHoc_NamHocId(int monHocId, int ki_namHocId);
}
