package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.controller.APIEntity.SinhVien;
import vn.bkdn.cntt.entity.DMSinhVien;

import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */
public interface DMSinhVienService {
    List<DMSinhVien> findAll();

    DMSinhVien findOne(int sinhVienId);
    DMSinhVien findByMaSinhVien(String maSinhVien);

    void insertSinhVien(DMSinhVien dmSinhVien);
    void editSinhVien(SinhVien sinhVien);
    void deleteSinhVien(int id);
}
