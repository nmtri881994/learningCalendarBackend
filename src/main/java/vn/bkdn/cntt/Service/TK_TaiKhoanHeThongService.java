package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong;
import vn.bkdn.cntt.entity.TK_VaiTro;

import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */
public interface TK_TaiKhoanHeThongService {
    TK_TaiKhoanHeThong findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    TK_TaiKhoanHeThong findByTenDangNhap(String tenDangNhap);

    List<TK_VaiTro> getAllVaiTroByTaiKhoanId(int taiKhoanHeThongId);

    TK_TaiKhoanHeThong insertTK(TK_TaiKhoanHeThong tk_taiKhoanHeThong);
    void deleteTK(int id);
    List<TK_TaiKhoanHeThong> findAll();
    TK_TaiKhoanHeThong findOne(int id);
}
