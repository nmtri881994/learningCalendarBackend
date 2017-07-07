package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.repository.DMLopMonHoc_SinhVienRepository;

import java.util.List;

/**
 * Created by Tri on 7/7/2017.
 */

@Service
public class DMLopMonHoc_SinhVienServiceImpl implements DMLopMonHoc_SinhVienService {

    @Autowired
    private DMLopMonHoc_SinhVienRepository dmLopMonHoc_sinhVienRepository;

    @Override
    public List<Integer> getSinhVienIdsOfLopMonHoc(int lopMonHocId) {
        return dmLopMonHoc_sinhVienRepository.getSinhVienIdsOfLopMonHoc(lopMonHocId);
    }
}
