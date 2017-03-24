package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.GiaoVien;
import vn.bkdn.cntt.repository.GiaoVienRepository;

/**
 * Created by Tri on 3/24/2017.
 */

@Service
public class GiaoVienServiceImpl implements GiaoVienService{

    @Autowired
    private GiaoVienRepository giaoVienRepository;

    @Override
    public GiaoVien findByMaGiaoVien(String maGiaoVien) {
        return giaoVienRepository.findByMaGiaoVien(maGiaoVien);
    }
}
