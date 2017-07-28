package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.entity.DMLopMonHoc;
import vn.bkdn.cntt.repository.DMLopMonHocRepository;

import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */

@Service
public class DMLopMonHocServiceImpl implements DMLopMonHocService {

    @Autowired
    private DMLopMonHocRepository lopMonHocRepository;

    @Override
    public List<DMLopMonHoc> findByGiaoVien(DMNhanVien dmNhanVien) {
        return lopMonHocRepository.findByDmNhanVien(dmNhanVien);
    }

    @Override
    public List<DMLopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(int ki_namHocId, int khoa_khoaHocId, int nganhId) {
        return lopMonHocRepository.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(ki_namHocId, khoa_khoaHocId, nganhId);
    }

    @Override
    public List<DMLopMonHoc> findByKiHoc_NamHocIdAndKhoa_KhoaHocId(int ki_namHocId, int khoa_khoaHocId) {
        return lopMonHocRepository.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(ki_namHocId, khoa_khoaHocId);
    }

    @Override
    public DMLopMonHoc findOne(int DMLopMonHocId) {
        return lopMonHocRepository.findOne(DMLopMonHocId);
    }

    @Override
    public List<DMLopMonHoc> findByGiaoVienIdAndNamHocKiHocId(int giaoVienId, int namHoc_KiHocId) {
        return lopMonHocRepository.findByGiaoVienIdAndNamHocKiHocId(giaoVienId, namHoc_KiHocId);
    }

    @Override
    public List<DMLopMonHoc> findByKhoa_KhoaHoc(int khoa_KhoaHocId) {
        return lopMonHocRepository.findByKhoa_KhoaHoc(khoa_KhoaHocId);
    }

    @Override
    public List<DMLopMonHoc> findByKiHoc_NamHocId(int ki_namHocId) {
        return lopMonHocRepository.findByKiHoc_NamHocId(ki_namHocId);
    }

    @Override
    public List<DMLopMonHoc> findByMonHocIdAndKiHoc_NamHoc(int monHocId, int kiHoc_namHocId) {
        return lopMonHocRepository.findByMonHocIdAndKiHoc_NamHocId(monHocId, kiHoc_namHocId);
    }
}
