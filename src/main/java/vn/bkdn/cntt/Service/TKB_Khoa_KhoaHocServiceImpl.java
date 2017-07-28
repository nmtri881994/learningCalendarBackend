package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.controller.APIEntity.KhoaKhoaHoc;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc;
import vn.bkdn.cntt.repository.TKB_Khoa_KhoaHocRepository;

import java.util.List;

/**
 * Created by XuanVinh on 4/6/2017.
 */

@Service
public class TKB_Khoa_KhoaHocServiceImpl implements TKB_Khoa_KhoaHocService {

    @Autowired
    private TKB_Khoa_KhoaHocRepository khoa_khoaHocRepository;

    @Override
    public TKB_Khoa_KhoaHoc findByKhoaIdAndKhoaHocId(int khoaId, int khoaHocId) {
        return khoa_khoaHocRepository.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
    }

    @Override
    public int getKhoaId(int khoa_khoaHocId) {
        return khoa_khoaHocRepository.getKhoaId(khoa_khoaHocId);
    }

    @Override
    public List<TKB_Khoa_KhoaHoc> findAll() {
        return khoa_khoaHocRepository.findAll();
    }

    @Override
    public void insertKhoaKhoaHoc(TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc) {
        khoa_khoaHocRepository.save(tkb_khoa_khoaHoc);
    }

    @Override
    public void editKhoaKhoaHoc(KhoaKhoaHoc khoaKhoaHoc) {
        khoa_khoaHocRepository.editKhoaHoc(khoaKhoaHoc.getId(), khoaKhoaHoc.getKhoa().getId(),khoaKhoaHoc.getTkb_khoaHoc().getId(),
                khoaKhoaHoc.getKiPhanNganh().getId(), khoaKhoaHoc.getKiBatDau().getId(), khoaKhoaHoc.getKiKetThuc().getId());
    }

    @Override
    public void deleteKhoaKhoaHoc(int id) {
        khoa_khoaHocRepository.delete(id);
    }
}
