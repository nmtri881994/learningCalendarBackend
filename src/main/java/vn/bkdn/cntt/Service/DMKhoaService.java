package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMDonVi;

import java.util.List;

/**
 * Created by Tri on 4/5/2017.
 */
public interface DMKhoaService {
    List<DMDonVi> findAll();
    DMDonVi findOne(int khoaId);
}
