package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;

import java.text.ParseException;

/**
 * Created by XuanVinh on 3/27/2017.
 */
public interface TKB_LichHocTheoNgayService {
    TKB_LichHocTheoNgay findOne(int id);
    boolean updateBuoiHoc(TKB_LichHocTheoNgay tkb_lichHocTheoNgay);
}
