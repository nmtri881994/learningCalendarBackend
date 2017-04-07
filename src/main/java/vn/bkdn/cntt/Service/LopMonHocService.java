package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.GiaoVien;
import vn.bkdn.cntt.entity.LopMonHoc;

import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */
public interface LopMonHocService {
    List<LopMonHoc> findByGiaoVien(GiaoVien giaoVien);
    List<LopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(int ki_namHocId, int khoa_khoaHocId, int nganhId);
    List<LopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocId(int ki_namHocId, int khoa_khoaHocId);
    LopMonHoc findOne(int lopMonHocId);
}
