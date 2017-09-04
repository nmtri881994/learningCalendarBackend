package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.controller.APIEntity.KhoaKhoaHocNganh;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc_Nganh;
import vn.bkdn.cntt.repository.TKB_Khoa_KhoaHoc_NganhRepository;

import java.util.List;

/**
 * Created by Tri on 7/30/2017.
 */

@Service
public class TKB_Khoa_KhoaHoc_NganhServiceImpl implements TKB_Khoa_KhoaHoc_NganhService {

    @Autowired
    private TKB_Khoa_KhoaHoc_NganhRepository tkb_khoa_khoaHoc_nganhRepository;

    @Override
    public TKB_Khoa_KhoaHoc_Nganh findOne(int id) {
        return tkb_khoa_khoaHoc_nganhRepository.findOne(id);
    }

    @Override
    public List<TKB_Khoa_KhoaHoc_Nganh> findAll() {
        return tkb_khoa_khoaHoc_nganhRepository.findAll();
    }

    @Override
    public void insertKhoaKhoaHocNganh(TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh) {
        tkb_khoa_khoaHoc_nganhRepository.save(tkb_khoa_khoaHoc_nganh);
    }

    @Override
    public void editKhoaKhoaHocNganh(KhoaKhoaHocNganh khoaKhoaHocNganh) {
        tkb_khoa_khoaHoc_nganhRepository.editKhoaHoc(khoaKhoaHocNganh.getId(), khoaKhoaHocNganh.getKhoaKhoaHoc().getId(), khoaKhoaHocNganh.getDmNganh().getId());
    }

    @Override
    public void deleteKhoaKhoaHocNganh(int id) {
        tkb_khoa_khoaHoc_nganhRepository.delete(id);
    }

    @Override
    public TKB_Khoa_KhoaHoc_Nganh findByKhoaKhoaHocIdAndNganhId(int khoaKhoaHocId, int nganhId) {
        return tkb_khoa_khoaHoc_nganhRepository.findByKhoaKhoaHocIdAndNganhId(khoaKhoaHocId, nganhId);
    }
}
