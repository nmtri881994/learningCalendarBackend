package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_NamHoc;

import java.util.List;

/**
 * Created by Tri on 3/21/2017.
 */
public interface NamHocService {
    List<TKB_NamHoc> findAll();
    List<TKB_NamHoc> getYearsNotEnd();
    TKB_NamHoc findOne(int namHocId);
}
