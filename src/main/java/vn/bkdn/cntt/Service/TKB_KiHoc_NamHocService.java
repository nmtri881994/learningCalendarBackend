package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_KiHoc_NamHoc;

import java.sql.Date;
import java.util.List;

/**
 * Created by Tri on 4/5/2017.
 */
public interface TKB_KiHoc_NamHocService {
    TKB_KiHoc_NamHoc findKiHocNamHocByKyHocIdAndNamHocId(int kiHocId, int namHocId);

    List<TKB_KiHoc_NamHoc> findAll();

    int getNamHocId(int kiHoc_NamHocId);

    void setDaSinhLich(int kiHoc_NamHocId);

    void insertKiHocNamHoc(TKB_KiHoc_NamHoc tkb_kiHoc_namHoc);

    void editKiHocNamHoc(int id, int namHocId, int kiHocId, Date ngayBatDau, Date ngayKetThuc);

    void deleteKiHocNamHoc(int id);

    TKB_KiHoc_NamHoc findOne(int id);
}
