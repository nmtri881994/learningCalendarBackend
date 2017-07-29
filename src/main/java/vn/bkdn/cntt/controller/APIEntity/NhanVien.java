package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMDonVi;
import vn.bkdn.cntt.entity.DMNhanVien;

/**
 * Created by Tri on 7/29/2017.
 */
public class NhanVien {
    private int id;
    private String maNhanVien;
    private String hoDem;
    private String ten;
    private Khoa dmDonVi;

    public NhanVien() {
    }


    public NhanVien(int id, String maNhanVien, String hoDem, String ten, Khoa dmDonVi) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.hoDem = hoDem;
        this.ten = ten;
        this.dmDonVi = dmDonVi;
    }

    public NhanVien(DMNhanVien dmNhanVien) {
        this.id = dmNhanVien.getId();
        this.maNhanVien = dmNhanVien.getMaNhanVien();
        this.hoDem = dmNhanVien.getHoDem();
        this.ten = dmNhanVien.getTen();
        this.dmDonVi = new Khoa(dmNhanVien.getDmDonVi());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoDem() {
        return hoDem;
    }

    public void setHoDem(String hoDem) {
        this.hoDem = hoDem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Khoa getDmDonVi() {
        return dmDonVi;
    }

    public void setDmDonVi(Khoa dmDonVi) {
        this.dmDonVi = dmDonVi;
    }
}
