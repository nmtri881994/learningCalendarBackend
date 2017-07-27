package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMLoaiPhong;

/**
 * Created by Tri on 7/27/2017.
 */
public class GiangDuong {
    private int id;
    private String maGiangDuong;
    private String ten;
    private int loaiPhongId;
    private int tang;
    private int soLuong;

    public GiangDuong(int id, String maGiangDuong, String ten, int loaiPhongId, int tang, int soLuong) {
        this.id = id;
        this.maGiangDuong = maGiangDuong;
        this.ten = ten;
        this.loaiPhongId = loaiPhongId;
        this.tang = tang;
        this.soLuong = soLuong;
    }

    public GiangDuong() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaGiangDuong() {
        return maGiangDuong;
    }

    public void setMaGiangDuong(String maGiangDuong) {
        this.maGiangDuong = maGiangDuong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getLoaiPhongId() {
        return loaiPhongId;
    }

    public void setLoaiPhongId(int loaiPhongId) {
        this.loaiPhongId = loaiPhongId;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
