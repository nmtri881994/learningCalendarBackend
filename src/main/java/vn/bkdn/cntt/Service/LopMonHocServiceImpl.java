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
public class LopMonHocServiceImpl implements LopMonHocService{

    @Autowired
    private LopMonHocRepository lopMonHocRepository;

    @Override
    public List<LopMonHoc> findByGiaoVien(GiaoVien giaoVien) {
        return lopMonHocRepository.findByGiaoVien(giaoVien);
    }
}
