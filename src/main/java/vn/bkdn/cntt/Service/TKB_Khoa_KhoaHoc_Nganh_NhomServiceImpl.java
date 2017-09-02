package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc_Nganh_Nhom;
import vn.bkdn.cntt.repository.TKB_Khoa_KhoaHoc_Nganh_NhomRepository;

import java.util.List;

/**
 * Created by XuanVinh on 9/2/2017.
 */

@Service
public class TKB_Khoa_KhoaHoc_Nganh_NhomServiceImpl implements TKB_Khoa_KhoaHoc_Nganh_NhomService {

    @Autowired
    TKB_Khoa_KhoaHoc_Nganh_NhomRepository  tkb_khoa_khoaHoc_nganh_nhomRepository;

    @Override
    public void insertKhoaKhoaHocNganhNhom(TKB_Khoa_KhoaHoc_Nganh_Nhom tkb_khoa_khoaHoc_nganh_nhom) {
        tkb_khoa_khoaHoc_nganh_nhomRepository.save(tkb_khoa_khoaHoc_nganh_nhom);
    }

    @Override
    public void deleteNhom(int nhomId) {
        tkb_khoa_khoaHoc_nganh_nhomRepository.delete(nhomId);
    }

    @Override
    public List<TKB_Khoa_KhoaHoc_Nganh_Nhom> findByKhoaKhoaHoc(int khoaKhoaHocId) {
        return tkb_khoa_khoaHoc_nganh_nhomRepository.findByKhoaKhoaHoc(khoaKhoaHocId);
    }

    @Override
    public List<TKB_Khoa_KhoaHoc_Nganh_Nhom> findByKhoaKhoaHocNganh(int khoaKhoaHocNganhId) {
        return tkb_khoa_khoaHoc_nganh_nhomRepository.findByKhoaKhoaHocNganh(khoaKhoaHocNganhId);
    }
}
