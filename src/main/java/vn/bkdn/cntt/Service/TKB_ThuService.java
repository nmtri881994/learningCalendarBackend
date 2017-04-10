package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_Thu;

import java.util.List;

/**
 * Created by Tri on 3/28/2017.
 */
public interface TKB_ThuService {
    TKB_Thu findByTen(String ten);
    List<TKB_Thu> findAll();
    TKB_Thu findOne(int tkbThuId);
}
