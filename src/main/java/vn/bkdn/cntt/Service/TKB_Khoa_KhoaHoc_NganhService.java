package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.controller.APIEntity.KhoaKhoaHocNganh;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc_Nganh;

import java.util.List;

/**
 * Created by Tri on 7/30/2017.
 */
public interface TKB_Khoa_KhoaHoc_NganhService {
    TKB_Khoa_KhoaHoc_Nganh findOne(int id);
    List<TKB_Khoa_KhoaHoc_Nganh> findAll();
    void insertKhoaKhoaHocNganh(TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh);
    void editKhoaKhoaHocNganh(KhoaKhoaHocNganh khoaKhoaHocNganh);
    void deleteKhoaKhoaHocNganh(int id);

    TKB_Khoa_KhoaHoc_Nganh findByKhoaKhoaHocIdAndNganhId(int khoaKhoaHocId, int nganhId);
}
