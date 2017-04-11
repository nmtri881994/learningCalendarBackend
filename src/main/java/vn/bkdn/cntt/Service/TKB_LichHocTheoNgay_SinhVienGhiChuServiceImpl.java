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

    @Autowired
    private TKB_LichHocTheoNgayService tkb_lichHocTheoNgayService;

    @Autowired
    private SinhVienService sinhVienService;

    @Override
    public TKB_LichHocTheoNgay_SinhVienGhiChu findByTkbLichHocTheoNgayAndSinhVien(int tkbLichHocTheoNgayId, int sinhVienId) {
        return tkb_lichHocTheoNgay_sinhVienGhiChuRepository.findByTkbLichHocTheoNgayAndSinhVien(tkbLichHocTheoNgayId, sinhVienId);
    }

    @Override
    public void editCalendarStudentNote(String editedNote, int tkbLichHocTheoNgayId, int sinhVienId) {
        TKB_LichHocTheoNgay_SinhVienGhiChu tkb_lichHocTheoNgay_sinhVienGhiChu = tkb_lichHocTheoNgay_sinhVienGhiChuRepository.findByTKB_LichHocTheoNgayIdAndSinhVienId(tkbLichHocTheoNgayId, sinhVienId);
        if(tkb_lichHocTheoNgay_sinhVienGhiChu != null){
            tkb_lichHocTheoNgay_sinhVienGhiChuRepository.editCalendarStudentNote(editedNote, tkbLichHocTheoNgayId, sinhVienId);
        }else{
            tkb_lichHocTheoNgay_sinhVienGhiChu = new TKB_LichHocTheoNgay_SinhVienGhiChu();
            tkb_lichHocTheoNgay_sinhVienGhiChu.setTkbLichHocTheoNgay(tkb_lichHocTheoNgayService.findOne(tkbLichHocTheoNgayId));
            tkb_lichHocTheoNgay_sinhVienGhiChu.setSinhVien(sinhVienService.findOne(sinhVienId));
            tkb_lichHocTheoNgay_sinhVienGhiChu.setSinhVienGhiChu(editedNote);

            tkb_lichHocTheoNgay_sinhVienGhiChuRepository.save(tkb_lichHocTheoNgay_sinhVienGhiChu);
        }
    }
}
