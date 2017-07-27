package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_KiHoc_NamHoc;
import vn.bkdn.cntt.repository.TKB_KiHoc_NamHocRepository;

import java.util.List;

/**
 * Created by Tri on 4/5/2017.
 */

@Service
public class KiHoc_NamHocServiceImpl implements KiHoc_NamHocService {

    @Autowired
    private TKB_KiHoc_NamHocRepository kiHoc_namHocRepository;

    @Override
    public TKB_KiHoc_NamHoc findKiHocNamHocByKyHocIdAndNamHocId(int kiHocId, int namHocId) {
        return kiHoc_namHocRepository.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
    }

    @Override
    public List<TKB_KiHoc_NamHoc> findAll() {
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
