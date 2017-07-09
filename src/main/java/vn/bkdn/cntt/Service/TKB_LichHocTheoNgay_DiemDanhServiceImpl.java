package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay_DiemDanh;
import vn.bkdn.cntt.repository.DMLopMonHoc_SinhVienRepository;
import vn.bkdn.cntt.repository.SinhVienRepository;
import vn.bkdn.cntt.repository.TKB_LichHocTheoNgayRepository;
import vn.bkdn.cntt.repository.TKB_LichHocTheoNgay_DiemDanhRepository;

/**
 * Created by Tri on 7/9/2017.
 */

@Service
public class TKB_LichHocTheoNgay_DiemDanhServiceImpl implements TKB_LichHocTheoNgay_DiemDanhService {

    @Autowired
    private TKB_LichHocTheoNgay_DiemDanhRepository tkb_lichHocTheoNgay_diemDanhRepository;

    @Autowired
    private TKB_LichHocTheoNgayRepository tkb_lichHocTheoNgayRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Override
    public void diemDanh(int lessonId, int studentId, boolean status) {
        if(tkb_lichHocTheoNgay_diemDanhRepository.findByLessonIdAndStudentId(lessonId, studentId) != null){
            tkb_lichHocTheoNgay_diemDanhRepository.diemDanh2(lessonId, studentId, status);
        }else{
            TKB_LichHocTheoNgay_DiemDanh tkb_lichHocTheoNgay_diemDanh = new TKB_LichHocTheoNgay_DiemDanh();
            tkb_lichHocTheoNgay_diemDanh.setTkb_lichHocTheoNgay(tkb_lichHocTheoNgayRepository.findOne(lessonId));
            tkb_lichHocTheoNgay_diemDanh.setDmSinhVien(sinhVienRepository.findOne(studentId));
            tkb_lichHocTheoNgay_diemDanh.setPresented(status);
            tkb_lichHocTheoNgay_diemDanhRepository.save(tkb_lichHocTheoNgay_diemDanh);
        }
    }
}
