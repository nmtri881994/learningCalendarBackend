package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_ThoiGianDangKy;

import java.util.List;

/**
 * Created by XuanVinh on 4/20/2017.
 */
public interface TKB_ThoiGianDangKyService {
    void udpateRegistering(int registerTimeId, boolean status);
    TKB_ThoiGianDangKy findOne(int registerTimeId);
    List<TKB_ThoiGianDangKy> findAll();
}
