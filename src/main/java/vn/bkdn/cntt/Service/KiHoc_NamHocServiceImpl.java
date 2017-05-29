package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.KiHoc_NamHoc;
import vn.bkdn.cntt.repository.KiHoc_NamHocRepository;

import java.util.List;

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

    @Override
    public List<KiHoc_NamHoc> findAll() {
        return kiHoc_namHocRepository.findAll();
    }

    @Override
    public int getNamHocId(int kiHoc_NamHocId) {
        return kiHoc_namHocRepository.getNamHocId(kiHoc_NamHocId);
    }

    @Override
    public void setDaSinhLich(int kiHoc_NamHocId) {
        kiHoc_namHocRepository.setDaSinhLich(kiHoc_NamHocId);
    }
}
