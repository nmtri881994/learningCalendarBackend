package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.controller.APIEntity.SinhVien;
import vn.bkdn.cntt.repository.DMSinhVienRepository;
import vn.bkdn.cntt.entity.DMSinhVien;

import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */

@Service
public class DMSinhVienServiceImpl implements DMSinhVienService {

    @Autowired
    private DMSinhVienRepository sinhVienRepository;

    @Override
    public List<DMSinhVien> findAll() {
        return sinhVienRepository.findAll();
    }

    @Override
    public List<DMSinhVien> findAll2() {
        return sinhVienRepository.findAll2();
    }

    @Override
    public DMSinhVien findOne(int sinhVienId) {
//        return sinhVienRepository.findById(sinhVienId);
        return sinhVienRepository.findOne(sinhVienId);
    }

    @Override
    public DMSinhVien findByMaSinhVien(String maSinhVien) {
        return sinhVienRepository.findByMaSinhVien(maSinhVien);
    }

    @Override
    public void insertSinhVien(DMSinhVien dmSinhVien) {
        sinhVienRepository.save(dmSinhVien);
    }

    @Override
    public void editSinhVien(SinhVien sinhVien) {
        sinhVienRepository.editNhanVien(sinhVien.getId(), sinhVien.getMaSinhVien(), sinhVien.getHoDem(), sinhVien.getTen(),
                sinhVien.getLopHoc().getId());
    }

    @Override
    public void deleteSinhVien(int id) {
        sinhVienRepository.delete(id);
    }
}
