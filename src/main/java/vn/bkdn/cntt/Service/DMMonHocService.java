package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.controller.APIEntity.MonHoc;
import vn.bkdn.cntt.entity.DMMonHoc;

import java.util.List;

/**
 * Created by XuanVinh on 3/28/2017.
 */

public interface DMMonHocService {
    DMMonHoc findOne(int id);
    List<DMMonHoc> findAll();
    void insertMonHoc(DMMonHoc dmMonHoc);
    void editMonHoc(MonHoc monHoc);
    void deleteMonHoc(int id);
}
