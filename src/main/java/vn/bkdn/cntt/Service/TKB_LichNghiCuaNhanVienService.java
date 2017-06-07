package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.entity.TKB_LichNghiCuaNhanVien;

import java.sql.Date;

/**
 * Created by Tri on 4/4/2017.
 */
public interface TKB_LichNghiCuaNhanVienService {
    TKB_LichNghiCuaNhanVien findByGiaoVienAndFindNgay(DMNhanVien giaoVien, Date ngay);
}
