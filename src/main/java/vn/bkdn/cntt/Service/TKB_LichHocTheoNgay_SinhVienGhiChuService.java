package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.SinhVien;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay_SinhVienGhiChu;

/**
 * Created by Tri on 3/31/2017.
 */
public interface TKB_LichHocTheoNgay_SinhVienGhiChuService {
    TKB_LichHocTheoNgay_SinhVienGhiChu findByTkbLichHocTheoNgayAndSinhVien(int tkbLichHocTheoNgayId, int sinhVienId);
    void editCalendarStudentNote(String editedNote, int tkbLichHocTheoNgayId, int sinhVienId);
}
