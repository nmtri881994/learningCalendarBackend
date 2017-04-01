package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.LopMonHoc;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * Created by XuanVinh on 3/27/2017.
 */

@Repository
public interface TKB_LichHocTheoNgayRepository extends JpaRepository<TKB_LichHocTheoNgay, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update tkb_lich_hoc_theo_ngay set ngay = ?1, tkb_thu_id = ?2, giang_duong_id = ?3, " +
            "tkb_tiet_dau_tien_id = ?4, tkb_tiet_cuoi_cung_id = ?5, thi_giua_ky = ?6, thi_cuoi_ky = ?7, " +
            "giao_vien_nhan = ?8, giao_vien_ghi_chu = ?9 where id = ?10", nativeQuery = true)
    void updateBuoiHoc(Date ngay, int tkb_thuId, int giangDuongId, int tkb_tietDauTienId, int tkb_tietCuoiCungId,
                                      boolean thiGiuaKy, boolean thiCuoiKy, String giaoVienNhan, String giaoVienGhiChu, int tkb_lichHocTheoNgayId);

    @Query(value = "select * from tkb_lich_hoc_theo_ngay tkb where tkb.giang_duong_id = ?1 and tkb.ngay = ?2", nativeQuery = true)
    List<TKB_LichHocTheoNgay> getLichHocOfRoomByDate(int giangDuongId, String ngay);

    List<TKB_LichHocTheoNgay> findByLopMonHocAndNgay(LopMonHoc lopMonHoc, Date ngay);
}
