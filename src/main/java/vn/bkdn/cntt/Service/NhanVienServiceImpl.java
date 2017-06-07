package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.repository.NhanVienRepository;

/**
 * Created by Tri on 3/24/2017.
 */

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;


    @Override
    public DMNhanVien findByMaNhanVien(String maNhanVien) {
        return nhanVienRepository.findByMaNhanVien(maNhanVien);
    }

    @Override
    public DMNhanVien findOne(int giaoVienId) {
        return nhanVienRepository.findOne(giaoVienId);
    }
}
