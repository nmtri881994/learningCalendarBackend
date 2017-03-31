package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.SinhVien;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay_SinhVienGhiChu;
import vn.bkdn.cntt.repository.TKB_LichHocTheoNgay_SinhVienGhiChuRepository;

/**
 * Created by Tri on 3/31/2017.
 */

@Service
class TKB_LichHocTheoNgay_SinhVienGhiChuServiceImpl implements TKB_LichHocTheoNgay_SinhVienGhiChuService {

    @Autowired
    private TKB_LichHocTheoNgay_SinhVienGhiChuRepository tkb_lichHocTheoNgay_sinhVienGhiChuRepository;

    @Override
    public TKB_LichHocTheoNgay_SinhVienGhiChu findByTkbLichHocTheoNgayAndSinhVien(int tkbLichHocTheoNgayId, int sinhVienId) {
        return tkb_lichHocTheoNgay_sinhVienGhiChuRepository.findByTkbLichHocTheoNgayAndSinhVien(tkbLichHocTheoNgayId, sinhVienId);
    }

    @Override
    public void editCalendarStudentNote(String editedNote, int tkbLichHocTheoNgayId, int sinhVienId) {
        tkb_lichHocTheoNgay_sinhVienGhiChuRepository.editCalendarStudentNote(editedNote, tkbLichHocTheoNgayId, sinhVienId);
    }
}
