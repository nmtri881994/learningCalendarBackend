package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMMonHoc;

/**
 * Created by XuanVinh on 8/1/2017.
 */
public class MonHoc {
    private int id;
    private String maMonHoc;
    private String ten;
    private float soTinChi;

    public MonHoc() {
    }

    public MonHoc(int id, String maMonHoc, String ten, float soTinChi) {
        this.id = id;
        this.maMonHoc = maMonHoc;
        this.ten = ten;
        this.soTinChi = soTinChi;
    }

    public MonHoc(DMMonHoc dmMonHoc) {
        this.id = dmMonHoc.getId();
        this.maMonHoc = dmMonHoc.getMaMonHoc();
        this.ten = dmMonHoc.getTen();
        this.soTinChi = dmMonHoc.getSoTinChi();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(float soTinChi) {
        this.soTinChi = soTinChi;
    }
}
