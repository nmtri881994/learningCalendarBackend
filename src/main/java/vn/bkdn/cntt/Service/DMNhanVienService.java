package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.controller.APIEntity.NhanVien;
import vn.bkdn.cntt.entity.DMNhanVien;

import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */

public interface DMNhanVienService {
    DMNhanVien findByMaNhanVien(String maNhanVien);

    DMNhanVien findOne(int giaoVienId);
    List<DMNhanVien> findAll();
    void insertNhanVien(DMNhanVien dmNhanVien);
    void editNhanVien(NhanVien nhanVien);
    void deleteNhanVien(int id);
}
