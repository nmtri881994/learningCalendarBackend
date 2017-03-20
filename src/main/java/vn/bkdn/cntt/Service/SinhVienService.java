package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.SinhVien;

import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */
public interface SinhVienService {
    List<SinhVien> findAll();

    SinhVien findOne(int sinhVienId);
    SinhVien findByMaSinhVien(String maSinhVien);
}
