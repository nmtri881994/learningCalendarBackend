package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_LichNghiCuaTruong;

import java.sql.Date;

/**
 * Created by Tri on 4/4/2017.
 */
public interface TKB_LichNghiCuaTruongService {
    TKB_LichNghiCuaTruong findByNgay(Date ngay);
}
