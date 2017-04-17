package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.GiaoVien;
import vn.bkdn.cntt.entity.LopMonHoc;
import vn.bkdn.cntt.repository.LopMonHocRepository;

import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */

@Service
public class LopMonHocServiceImpl implements LopMonHocService {

    @Autowired
    private LopMonHocRepository lopMonHocRepository;

    @Override
    public List<LopMonHoc> findByGiaoVien(GiaoVien giaoVien) {
        return lopMonHocRepository.findByGiaoVien(giaoVien);
    }

    @Override
    public List<LopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(int ki_namHocId, int khoa_khoaHocId, int nganhId) {
        return lopMonHocRepository.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(ki_namHocId, khoa_khoaHocId, nganhId);
    }

    @Override
    public List<LopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocId(int ki_namHocId, int khoa_khoaHocId) {
        return lopMonHocRepository.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(ki_namHocId, khoa_khoaHocId);
    }

    @Override
    public LopMonHoc findOne(int lopMonHocId) {
        return lopMonHocRepository.findOne(lopMonHocId);
    }

    @Override
    public List<LopMonHoc> findByGiaoVienIdAndNamHocKiHocId(int giaoVienId, int namHoc_KiHocId) {
        return lopMonHocRepository.findByGiaoVienIdAndNamHocKiHocId(giaoVienId, namHoc_KiHocId);
    }
}
