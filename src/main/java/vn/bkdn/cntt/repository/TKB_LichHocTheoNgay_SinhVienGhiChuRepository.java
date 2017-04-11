package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.SinhVien;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay_SinhVienGhiChu;

import javax.transaction.Transactional;

/**
 * Created by Tri on 3/31/2017.
 */

@Repository
public interface TKB_LichHocTheoNgay_SinhVienGhiChuRepository extends JpaRepository<TKB_LichHocTheoNgay_SinhVienGhiChu, Integer> {
    @Query(value = "select * from tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu where tkb_lich_hoc_theo_ngay_id = ?1 and sinh_vien_id = ?2", nativeQuery = true)
    TKB_LichHocTheoNgay_SinhVienGhiChu findByTkbLichHocTheoNgayAndSinhVien(int tkbLichHocTheoNgay, int sinhVien);

    @Modifying
    @Transactional
    @Query(value = "update tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu tkb set tkb.sinh_vien_ghi_chu = ?1 where tkb.tkb_lich_hoc_theo_ngay_id = ?2 and tkb.sinh_vien_id = ?3", nativeQuery = true)
    void editCalendarStudentNote(String editedNote, int tkbLichHocTheoNgay, int sinhVien);

    @Query(value = "select * from tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu tkb where tkb.tkb_lich_hoc_theo_ngay_id = ?1 and tkb.sinh_vien_id = ?2", nativeQuery = true)
    TKB_LichHocTheoNgay_SinhVienGhiChu findByTKB_LichHocTheoNgayIdAndSinhVienId(int tkb_lichHocTheoNgayId, int sinhVienId);
}
