package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc;
import vn.bkdn.cntt.repository.TKB_Khoa_KhoaHocRepository;

/**
 * Created by XuanVinh on 4/6/2017.
 */

@Service
public class Khoa_KhoaHocServiceImpl implements Khoa_KhoaHocService {

    @Autowired
    private TKB_Khoa_KhoaHocRepository khoa_khoaHocRepository;

    @Override
    public TKB_Khoa_KhoaHoc findByKhoaIdAndKhoaHocId(int khoaId, int khoaHocId) {
        return khoa_khoaHocRepository.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
    }

    @Override
    public int getKhoaId(int khoa_khoaHocId) {
        return khoa_khoaHocRepository.getKhoaId(khoa_khoaHocId);
    }
}
