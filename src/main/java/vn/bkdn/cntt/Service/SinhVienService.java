package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMSinhVien;

import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */
public interface SinhVienService {
    List<DMSinhVien> findAll();

    DMSinhVien findOne(int sinhVienId);
    DMSinhVien findByMaSinhVien(String maSinhVien);
}
