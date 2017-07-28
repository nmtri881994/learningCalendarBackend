package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.TKB_KiHoc;
import vn.bkdn.cntt.entity.TKB_NamHoc;

import java.sql.Date;

/**
 * Created by Tri on 7/28/2017.
 */
public class KiHocNamHoc {
    private int id;
    private KiHoc kiHoc;
    private NamHoc namHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public KiHocNamHoc() {
    }

    public KiHocNamHoc(int id, KiHoc kiHoc, NamHoc namHoc, Date ngayBatDau, Date ngayKetThuc) {
        this.id = id;
        this.kiHoc = kiHoc;
        this.namHoc = namHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KiHoc getKiHoc() {
        return kiHoc;
    }

    public void setKiHoc(KiHoc kiHoc) {
        this.kiHoc = kiHoc;
    }

    public NamHoc getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(NamHoc namHoc) {
        this.namHoc = namHoc;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
