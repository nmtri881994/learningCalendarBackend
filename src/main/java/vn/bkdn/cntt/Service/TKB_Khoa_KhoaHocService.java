package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.controller.APIEntity.KhoaKhoaHoc;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc;

import java.util.List;

/**
 * Created by XuanVinh on 4/6/2017.
 */
public interface TKB_Khoa_KhoaHocService {
    TKB_Khoa_KhoaHoc findByKhoaIdAndKhoaHocId(int khoaId, int khoaHocId);
    int getKhoaId(int khoa_khoaHocId);

    List<TKB_Khoa_KhoaHoc> findAll();
    void insertKhoaKhoaHoc(TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc);
    void editKhoaKhoaHoc(KhoaKhoaHoc khoaKhoaHoc);
    void deleteKhoaKhoaHoc(int id);
    TKB_Khoa_KhoaHoc findOne(int id);
}
