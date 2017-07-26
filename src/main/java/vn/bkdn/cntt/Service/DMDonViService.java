package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMDonVi;

import java.util.List;

/**
 * Created by Tri on 7/26/2017.
 */
public interface DMDonViService {
    DMDonVi save(DMDonVi dmDonVi);
    List<DMDonVi> findAllFaculty();
    void delete(int id);
    Boolean editKhoa(int id, String ma, String ten);
}
