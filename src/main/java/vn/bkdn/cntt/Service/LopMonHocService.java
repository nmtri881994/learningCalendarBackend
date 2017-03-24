package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.GiaoVien;
import vn.bkdn.cntt.entity.LopMonHoc;

import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */
public interface LopMonHocService {
    List<LopMonHoc> findByGiaoVien(GiaoVien giaoVien);
}
