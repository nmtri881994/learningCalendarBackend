package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.KiHoc_NamHoc;

import java.util.List;

/**
 * Created by Tri on 4/5/2017.
 */
public interface KiHoc_NamHocService {
    KiHoc_NamHoc findKiHocNamHocByKyHocIdAndNamHocId(int kiHocId, int namHocId);
    List<KiHoc_NamHoc> findAll();
    int getNamHocId(int kiHoc_NamHocId);

    void setDaSinhLich(int kiHoc_NamHocId);
}
