package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.Khoa_KhoaHoc;
import vn.bkdn.cntt.repository.Khoa_KhoaHocRepository;

/**
 * Created by XuanVinh on 4/6/2017.
 */

@Service
public class Khoa_KhoaHocServiceImpl implements Khoa_KhoaHocService {

    @Autowired
    private Khoa_KhoaHocRepository khoa_khoaHocRepository;

    @Override
    public Khoa_KhoaHoc findByKhoaIdAndKhoaHocId(int khoaId, int khoaHocId) {
        return khoa_khoaHocRepository.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
    }
}
