package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc;

/**
 * Created by XuanVinh on 4/6/2017.
 */
public interface Khoa_KhoaHocService {
    TKB_Khoa_KhoaHoc findByKhoaIdAndKhoaHocId(int khoaId, int khoaHocId);
    int getKhoaId(int khoa_khoaHocId);
}
