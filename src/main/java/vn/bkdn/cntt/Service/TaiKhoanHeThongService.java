package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TaiKhoanHeThong;
import vn.bkdn.cntt.entity.VaiTro;

import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */
public interface TaiKhoanHeThongService {
    TaiKhoanHeThong findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    TaiKhoanHeThong findByTenDangNhap(String tenDangNhap);

    List<VaiTro> getAllVaiTroByTaiKhoanId(int taiKhoanHeThongId);
}
