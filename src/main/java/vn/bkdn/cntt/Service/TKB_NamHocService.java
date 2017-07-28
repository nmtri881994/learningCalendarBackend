package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.controller.APIEntity.NamHoc;
import vn.bkdn.cntt.entity.TKB_KhoaHoc;
import vn.bkdn.cntt.entity.TKB_NamHoc;

import java.util.List;

/**
 * Created by Tri on 7/27/2017.
 */
public interface TKB_NamHocService {
    List<TKB_NamHoc> findAll();
    void insertNamHoc(NamHoc namHoc);
    void deleteNamHoc(int id);
    void editNamHoc(NamHoc namHoc);
    TKB_NamHoc findOne(int id);
}
