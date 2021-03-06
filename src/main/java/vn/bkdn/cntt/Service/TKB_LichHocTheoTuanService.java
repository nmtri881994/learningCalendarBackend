package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMLopMonHoc;
import vn.bkdn.cntt.entity.TKB_LichHocTheoTuan;

import java.util.List;

/**
 * Created by XuanVinh on 4/10/2017.
 */
public interface TKB_LichHocTheoTuanService {
    List<TKB_LichHocTheoTuan> findLichHocTheoTuanByThuIdAndGiangDuongId(int thuId, int giangDuongId);
    TKB_LichHocTheoTuan findOne(int tkbTuanId);
    void updateWeekCalendar(TKB_LichHocTheoTuan tkb_lichHocTheoTuan);
    void addWeekCalendar(TKB_LichHocTheoTuan tkb_lichHocTheoTuan);
    void deleteCalendar(int calendarId);
    int getDMLopMonHocIdByTKB_LichHocTheoTuanId(int tkb_lichHocTheoTuanId);
    boolean canAddOrEditWeekCalendar(TKB_LichHocTheoTuan tkb_lichHocTheoTuan, List<DMLopMonHoc> dmLopMonHocs);
    int findDMLopMonHocIdOfLichHocTheoTuan(int tkb_lichHocTheoTuanId);
    void deleteWeekCalendarOfDMLopMonHoc(int DMLopMonHocId);
}
