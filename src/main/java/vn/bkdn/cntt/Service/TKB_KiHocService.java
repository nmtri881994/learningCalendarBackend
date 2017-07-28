package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_KiHoc;

import java.util.List;

/**
 * Created by Tri on 7/28/2017.
 */
public interface TKB_KiHocService {
    List<TKB_KiHoc> findAll();
    TKB_KiHoc findOne(int id);
}
