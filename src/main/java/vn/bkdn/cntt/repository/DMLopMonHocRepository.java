package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.entity.DMLopMonHoc;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */

@Repository
public interface DMLopMonHocRepository extends JpaRepository<DMLopMonHoc, Integer> {
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

    @Modifying
    @Transactional
    @Query(value = "update dmlop_mon_hoc set dm_mon_hoc_id = ?2, dm_nhan_vien_id = ?3, tkb_ki_hoc_nam_hoc_id = ?4, " +
            "tkb_khoa_khoa_hoc_id=?5, dm_nganh_id=?6, so_tiet_ly_thuyet=?7, so_tiet_thuc_hanh=?8, so_luong_toi_da=?9, " +
            "gioi_han_tuan_bat_dau=?10, gioi_han_tuan_ket_thuc=?11 where id = ?1", nativeQuery = true)
    void editLopMonHoc1(int id, int monHocId, int nhanVienId, int kiHocNamHocId, int khoaKhoaHocId, int nganhId, int soTietLyThuyet,
                    int soTietThucHanh, int soLuongToiDa, int gioiHanTuanBatDau, int gioiHanTuanKetThuc);

    @Modifying
    @Transactional
    @Query(value = "update dmlop_mon_hoc set dm_mon_hoc_id = ?2, dm_nhan_vien_id = ?3, tkb_ki_hoc_nam_hoc_id = ?4, " +
            "tkb_khoa_khoa_hoc_id=?5, so_tiet_ly_thuyet=?6, so_tiet_thuc_hanh=?7, so_luong_toi_da=?8, " +
            "gioi_han_tuan_bat_dau=?9, gioi_han_tuan_ket_thuc=?10 where id = ?1", nativeQuery = true)
    void editLopMonHoc2(int id, int monHocId, int nhanVienId, int kiHocNamHocId, int khoaKhoaHocId, int soTietLyThuyet,
                        int soTietThucHanh, int soLuongToiDa, int gioiHanTuanBatDau, int gioiHanTuanKetThuc);
}
