package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMLopHoc;
import vn.bkdn.cntt.entity.DMNganh;
import vn.bkdn.cntt.entity.DMSinhVien;

/**
 * Created by Tri on 7/30/2017.
 */
public class SinhVien {
    private int id;
    private String maSinhVien;
    private String hoDem;
    private String ten;
    private LopHoc lopHoc;
    private DMNganh dmNganh;

    public SinhVien() {
    }

    public SinhVien(int id, String maSinhVien, String hoDem, String ten, LopHoc lopHoc, DMNganh dmNganh) {
        this.id = id;
        this.maSinhVien = maSinhVien;
        this.hoDem = hoDem;
        this.ten = ten;
        this.lopHoc = lopHoc;
        this.dmNganh = dmNganh;
    }

    public SinhVien(SinhVien sinhVien) {
        this.id = sinhVien.getId();
        this.maSinhVien = sinhVien.getMaSinhVien();
        this.hoDem = sinhVien.getHoDem();
        this.ten = sinhVien.getTen();
        this.lopHoc = sinhVien.getLopHoc();
        this.dmNganh = sinhVien.getDmNganh();
    }

    public SinhVien(DMSinhVien dmSinhVien) {
        this.id = dmSinhVien.getId();
        this.maSinhVien = dmSinhVien.getMaSinhVien();
        this.hoDem = dmSinhVien.getHoDem();
        this.ten = dmSinhVien.getTen();
        this.lopHoc = new LopHoc(dmSinhVien.getDmLopHoc());
        this.dmNganh = dmSinhVien.getDmNganh();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
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

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }

    public DMNganh getDmNganh() {
        return dmNganh;
    }

    public void setDmNganh(DMNganh dmNganh) {
        this.dmNganh = dmNganh;
    }
}
