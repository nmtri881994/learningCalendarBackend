package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong_VaiTro;

import java.util.List;

/**
 * Created by XuanVinh on 3/19/2017.
 */

public interface TK_TaiKhoanHeThong_VaiTroService {
    List<TK_TaiKhoanHeThong_VaiTro> getTaiKhoanHeThongVaiTrosByTaiKhoan(TK_TaiKhoanHeThong tk_taiKhoanHeThong);
}
