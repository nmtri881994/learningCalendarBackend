package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.entity.DMLopMonHoc;

import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */

@Repository
public interface LopMonHocRepository extends JpaRepository<DMLopMonHoc, Integer> {
    List<DMLopMonHoc> findByDmNhanVien(DMNhanVien dmNhanVien);

    @Query(value = "select * from dmlop_mon_hoc where tkb_ki_hoc_nam_hoc_id = ?1 and tkb_khoa_khoa_hoc_id = ?2 and dm_nganh_id = ?3", nativeQuery = true)
    List<DMLopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(int ki_namHocId, int khoa_khoaHocId, int nganhId);

    @Query(value = "select * from dmlop_mon_hoc where tkb_ki_hoc_nam_hoc_id = ?1 and tkb_khoa_khoa_hoc_id = ?2", nativeQuery = true)
    List<DMLopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocId(int ki_namHocId, int khoa_khoaHocId);

    @Query(value = "select * from dmlop_mon_hoc where dm_nhan_vien_id = ?1 and tkb_ki_hoc_nam_hoc_id = ?2", nativeQuery = true)
    List<DMLopMonHoc> findByGiaoVienIdAndNamHocKiHocId(int giaoVienId, int namHoc_KiHocId);

    @Query(value = "select * from dmlop_mon_hoc where tkb_khoa_khoa_hoc_id = ?1", nativeQuery = true)
    List<DMLopMonHoc> findByKhoa_KhoaHoc(int khoa_khoaHocId);

    @Query(value = "select * from dmlop_mon_hoc where tkb_ki_hoc_nam_hoc_id = ?1", nativeQuery = true)
    List<DMLopMonHoc> findByKiHoc_NamHocId(int ki_namHocId);

    @Query(value = "select * from dmlop_mon_hoc where dm_mon_hoc_id=?1 and tkb_ki_hoc_nam_hoc_id = ?2", nativeQuery = true)
    List<DMLopMonHoc> findByMonHocIdAndKiHoc_NamHocId(int monHocId, int ki_namHocId);
}
