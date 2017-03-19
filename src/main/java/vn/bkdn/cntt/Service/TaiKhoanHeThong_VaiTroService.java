package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TaiKhoanHeThong;
import vn.bkdn.cntt.entity.TaiKhoanHeThong_VaiTro;

import java.util.List;

/**
 * Created by XuanVinh on 3/19/2017.
 */

public interface TaiKhoanHeThong_VaiTroService {
    List<TaiKhoanHeThong_VaiTro> getTaiKhoanHeThongVaiTrosByTaiKhoan(TaiKhoanHeThong taiKhoanHeThong);
}
