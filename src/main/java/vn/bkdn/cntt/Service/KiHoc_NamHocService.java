package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.KiHoc_NamHoc;

/**
 * Created by Tri on 4/5/2017.
 */
public interface KiHoc_NamHocService {
    KiHoc_NamHoc findKiHocNamHocByKyHocIdAndNamHocId(int kiHocId, int namHocId);
}
