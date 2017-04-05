package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.NamHoc;

import java.util.List;

/**
 * Created by Tri on 3/21/2017.
 */
public interface NamHocService {
    List<NamHoc> findAll();
    List<NamHoc> getYearsNotEnd();
    NamHoc findOne(int namHocId);
}
