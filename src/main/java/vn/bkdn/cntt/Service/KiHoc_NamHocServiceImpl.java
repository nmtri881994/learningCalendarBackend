package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.KiHoc_NamHoc;
import vn.bkdn.cntt.repository.KiHoc_NamHocRepository;

/**
 * Created by Tri on 4/5/2017.
 */

@Service
public class KiHoc_NamHocServiceImpl implements KiHoc_NamHocService {

    @Autowired
    private KiHoc_NamHocRepository kiHoc_namHocRepository;

    @Override
    public KiHoc_NamHoc findKiHocNamHocByKyHocIdAndNamHocId(int kiHocId, int namHocId) {
        return kiHoc_namHocRepository.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
    }
}
