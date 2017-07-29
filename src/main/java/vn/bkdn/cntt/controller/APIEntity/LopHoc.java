package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMLopHoc;

/**
 * Created by Tri on 7/29/2017.
 */
public class LopHoc {
    private int id;
    private String ma;
    private String ten;
    private KhoaKhoaHoc khoaKhoaHoc;

    public LopHoc() {
    }

    public LopHoc(int id, String ma, String ten, KhoaKhoaHoc khoaKhoaHoc) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.khoaKhoaHoc = khoaKhoaHoc;
    }

    public LopHoc(DMLopHoc dmLopHoc) {
        this.id = dmLopHoc.getId();
        this.ma = dmLopHoc.getMa();
        this.ten = dmLopHoc.getTen();
        this.khoaKhoaHoc = new KhoaKhoaHoc(dmLopHoc.getTkb_khoa_khoaHoc());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public KhoaKhoaHoc getKhoaKhoaHoc() {
        return khoaKhoaHoc;
    }

    public void setKhoaKhoaHoc(KhoaKhoaHoc khoaKhoaHoc) {
        this.khoaKhoaHoc = khoaKhoaHoc;
    }
}
