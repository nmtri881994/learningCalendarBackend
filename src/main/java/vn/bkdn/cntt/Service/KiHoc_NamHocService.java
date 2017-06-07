package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_KiHoc_NamHoc;

import java.util.List;

/**
 * Created by Tri on 4/5/2017.
 */
public interface KiHoc_NamHocService {
    TKB_KiHoc_NamHoc findKiHocNamHocByKyHocIdAndNamHocId(int kiHocId, int namHocId);
    List<TKB_KiHoc_NamHoc> findAll();
    int getNamHocId(int kiHoc_NamHocId);

    void setDaSinhLich(int kiHoc_NamHocId);
}
