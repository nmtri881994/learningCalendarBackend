package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMNhanVien;

/**
 * Created by Tri on 3/24/2017.
 */

public interface NhanVienService {
    DMNhanVien findByMaNhanVien(String maNhanVien);

    DMNhanVien findOne(int giaoVienId);
}
