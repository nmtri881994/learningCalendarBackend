package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_LichHocTheoTuan;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by XuanVinh on 4/10/2017.
 */

@Repository
public interface TKB_LichHocTheoTuanRepository extends JpaRepository<TKB_LichHocTheoTuan, Integer> {

    @Query(value = "select * from tkb_lich_hoc_theo_tuan tkb where tkb.tkb_thu_id = ?1 and tkb.dm_giang_duong_id = ?2", nativeQuery = true)
    List<TKB_LichHocTheoTuan> findLichHocTheoTuanByThuIdAndGiangDuongId(int thuId, int giangDuongId);

    @Modifying
    @Transactional
    @Query(value = "update tkb_lich_hoc_theo_tuan set dm_giang_duong_id = ?1, tkb_thu_id =?2, tkb_tiet_dau_tien_id= ?3, " +
            "tkb_tiet_cuoi_cung_id =?4, tuan_bat_dau = ?5, tuan_ket_thuc=?6 where id = ?7 ", nativeQuery = true)
    void updateWeekCalendar(int giangDuongId, int thuId, int tietDauTienId, int tietCuoiCungId, int tuanBatDau, int tuanKetThuc, int weekCalendarId);

    @Query(value = "select dm_lop_mon_hoc_id from tkb_lich_hoc_theo_tuan where id = ?1 ", nativeQuery = true)
    int getDMLopMonHocIdByTKB_LichHocTheoTuanId(int tkb_lichHocTheoTuanId);

    @Query(value = "select * from tkb_lich_hoc_theo_tuan tkb where tkb.tkb_thu_id = ?1", nativeQuery = true)
    List<TKB_LichHocTheoTuan> findLichHocTheoTuanByThuId(int thuId);

    @Query(value = "select dm_lop_mon_hoc_id from tkb_lich_hoc_theo_tuan where id = ?1 ", nativeQuery = true)
    int findDMLopMonHocIdOfLichHocTheoTuan(int tkb_lichHocTheoTuanId);

    @Modifying
    @Transactional
    @Query(value = "delete from tkb_lich_hoc_theo_tuan where dm_lop_mon_hoc_id = ?1", nativeQuery = true)
    void deleteWeekCalendarOfDMLopMonHoc(int DMLopMonHocId);
}
