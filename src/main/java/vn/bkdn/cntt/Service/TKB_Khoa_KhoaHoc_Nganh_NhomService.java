package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc_Nganh_Nhom;

import java.util.List;

/**
 * Created by XuanVinh on 9/2/2017.
 */
public interface TKB_Khoa_KhoaHoc_Nganh_NhomService {
     void insertKhoaKhoaHocNganhNhom(TKB_Khoa_KhoaHoc_Nganh_Nhom tkb_khoa_khoaHoc_nganh_nhom);
     void deleteNhom(int nhomId);

     List<TKB_Khoa_KhoaHoc_Nganh_Nhom> findByKhoaKhoaHoc(int khoaKhoaHocId);
     List<TKB_Khoa_KhoaHoc_Nganh_Nhom> findByKhoaKhoaHocNganh(int khoaKhoaHocNganhId);

     TKB_Khoa_KhoaHoc_Nganh_Nhom findOne(int id);
}
