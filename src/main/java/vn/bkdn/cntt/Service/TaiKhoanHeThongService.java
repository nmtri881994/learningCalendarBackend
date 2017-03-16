package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TaiKhoanHeThong;

/**
 * Created by Tri on 3/15/2017.
 */
public interface TaiKhoanHeThongService {
    TaiKhoanHeThong findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
}
