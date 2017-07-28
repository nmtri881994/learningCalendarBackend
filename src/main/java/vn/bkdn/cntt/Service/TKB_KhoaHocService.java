package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_KhoaHoc;

import java.util.List;

/**
 * Created by Tri on 7/27/2017.
 */
public interface TKB_KhoaHocService {
    List<TKB_KhoaHoc> findAll();
    void insertKhoaHoc(TKB_KhoaHoc tkb_khoaHoc);
    void deleteKhoaHoc(int id);
    void editKhoaHoc(TKB_KhoaHoc tkb_khoaHoc);
    TKB_KhoaHoc findOne(int id);
}
