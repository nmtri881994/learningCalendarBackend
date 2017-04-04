package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.GiaoVien;
import vn.bkdn.cntt.entity.TKB_LichNghiCuaGiaoVien;

import java.sql.Date;

/**
 * Created by Tri on 4/4/2017.
 */
public interface TKB_LichNghiCuaGiaoVienService {
    TKB_LichNghiCuaGiaoVien findByGiaoVienAndFindNgay(GiaoVien giaoVien, Date ngay);
}
