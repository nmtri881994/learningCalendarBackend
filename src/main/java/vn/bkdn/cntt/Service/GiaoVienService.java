package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.GiaoVien;

/**
 * Created by Tri on 3/24/2017.
 */

public interface GiaoVienService {
    GiaoVien findByMaGiaoVien(String maGiaoVien);
}
