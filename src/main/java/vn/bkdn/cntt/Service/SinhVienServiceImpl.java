package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.repository.SinhVienRepository;
import vn.bkdn.cntt.entity.DMSinhVien;

import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */

@Service
public class SinhVienServiceImpl implements SinhVienService{

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Override
    public List<DMSinhVien> findAll() {
        return sinhVienRepository.findAll();
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
}
