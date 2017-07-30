package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMNganh;

import java.util.List;

/**
 * Created by Tri on 7/30/2017.
 */
public interface DMNganhService {
    DMNganh findOne(int id);
    List<DMNganh> findAll();
    void insertNganh(DMNganh dmNganh);
    void editNganh(DMNganh dmNganh);
    void deleteNganh(int id);
}
