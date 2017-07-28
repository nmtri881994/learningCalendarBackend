package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.entity.DMLopMonHoc;

import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */
public interface DMLopMonHocService {
    List<DMLopMonHoc> findByGiaoVien(DMNhanVien giaoVien);
    List<DMLopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(int ki_namHocId, int khoa_khoaHocId, int nganhId);
    List<DMLopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocId(int ki_namHocId, int khoa_khoaHocId);
    DMLopMonHoc findOne(int DMLopMonHocId);
    List<DMLopMonHoc> findByGiaoVienIdAndNamHocKiHocId(int giaoVienId, int namHoc_KiHocId);
    List<DMLopMonHoc> findByKhoa_KhoaHoc(int khoa_KhoaHocId);
    List<DMLopMonHoc> findByKiHoc_NamHocId(int ki_namHocId);
    List<DMLopMonHoc> findByMonHocIdAndKiHoc_NamHoc(int monHocId, int kiHoc_namHocId);
}
