package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.LopMonHoc;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

/**
 * Created by XuanVinh on 3/27/2017.
 */
public interface TKB_LichHocTheoNgayService {
    TKB_LichHocTheoNgay findOne(int id);
    boolean updateBuoiHoc(TKB_LichHocTheoNgay tkb_lichHocTheoNgay);
    List<TKB_LichHocTheoNgay> getLichHocOfRoomByDate(int giangDuongId, String ngay);
    List<TKB_LichHocTheoNgay> findByLopMonHocAndNgay(LopMonHoc lopMonHoc, Date ngay);
    int getClassId(int lessonId);

    void addLichHocTheoNgay(TKB_LichHocTheoNgay tkb_lichHocTheoNgay);
}
