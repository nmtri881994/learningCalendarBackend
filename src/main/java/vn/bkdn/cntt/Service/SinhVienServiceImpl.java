package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.repository.SinhVienRepository;
import vn.bkdn.cntt.entity.SinhVien;

import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */

@Service
public class SinhVienServiceImpl implements SinhVienService{

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Override
    public List<SinhVien> findAll() {
        return sinhVienRepository.findAll();
    }

    @Override
    public SinhVien findOne(int sinhVienId) {
//        return sinhVienRepository.findById(sinhVienId);
        return sinhVienRepository.findOne(sinhVienId);
    }

    @Override
    public SinhVien findOneByMaSinhVien(String maSinhVien) {
        return sinhVienRepository.findByMaSinhVien(maSinhVien);
    }
}
