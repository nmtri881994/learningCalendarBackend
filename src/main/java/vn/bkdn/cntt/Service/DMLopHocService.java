package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.controller.APIEntity.LopHoc;
import vn.bkdn.cntt.entity.DMLopHoc;

import java.util.List;

/**
 * Created by Tri on 7/29/2017.
 */
public interface DMLopHocService {
    DMLopHoc findOne(int id);
    List<DMLopHoc> findAll();
    void insertLopHoc(DMLopHoc dmLopHoc);
    void editLopHoc(LopHoc lopHoc);
    void deleteLopHoc(int id);
}
