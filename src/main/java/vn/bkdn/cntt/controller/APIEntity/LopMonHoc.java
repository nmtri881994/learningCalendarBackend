package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.*;

/**
 * Created by XuanVinh on 8/2/2017.
 */
public class LopMonHoc {
    private int id;
    private MonHoc dmMonHoc;
    private NhanVien dmNhanVien;
    private KiHocNamHoc tkb_kiHoc_namHoc;
    private KhoaKhoaHoc tkb_khoa_khoaHoc;
    private DMNganh dmNganh;
    private int soTietLyThuyet;
    private int soTietThucHanh;
    private int soLuongToiDa;
    private int gioiHanTuanBatDau;
    private int gioiHanTuanKetThuc;

    public LopMonHoc() {
    }

    public LopMonHoc(int id, MonHoc dmMonHoc, NhanVien dmNhanVien, KiHocNamHoc tkb_kiHoc_namHoc, KhoaKhoaHoc tkb_khoa_khoaHoc,
                     DMNganh dmNganh, int soTietLyThuyet, int soTietThucHanh, int soLuongToiDa, int gioiHanTuanBatDau,
                     int gioiHanTuanKetThuc) {
        this.id = id;
        this.dmMonHoc = dmMonHoc;
        this.dmNhanVien = dmNhanVien;
        this.tkb_kiHoc_namHoc = tkb_kiHoc_namHoc;
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
        this.dmNganh = dmNganh;
        this.soTietLyThuyet = soTietLyThuyet;
        this.soTietThucHanh = soTietThucHanh;
        this.soLuongToiDa = soLuongToiDa;
        this.gioiHanTuanBatDau = gioiHanTuanBatDau;
        this.gioiHanTuanKetThuc = gioiHanTuanKetThuc;
    }

    public LopMonHoc(DMLopMonHoc dmLopMonHoc) {
        this.id = dmLopMonHoc.getId();
        this.dmMonHoc = new MonHoc(dmLopMonHoc.getDmMonHoc());
        this.dmNhanVien = new NhanVien(dmLopMonHoc.getDmNhanVien());
        this.tkb_kiHoc_namHoc = new KiHocNamHoc(dmLopMonHoc.getTkb_kiHoc_namHoc());
        this.tkb_khoa_khoaHoc = new KhoaKhoaHoc(dmLopMonHoc.getTkb_khoa_khoaHoc());
        this.dmNganh = dmLopMonHoc.getDmNganh();
        this.soTietLyThuyet = dmLopMonHoc.getSoTietLyThuyet();
        this.soTietThucHanh = dmLopMonHoc.getSoTietThucHanh();
        this.soLuongToiDa = dmLopMonHoc.getSoLuongToiDa();
        this.gioiHanTuanBatDau = dmLopMonHoc.getGioiHanTuanBatDau();
        this.gioiHanTuanKetThuc = dmLopMonHoc.getGioiHanTuanKetThuc();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MonHoc getDmMonHoc() {
        return dmMonHoc;
    }

    public void setDmMonHoc(MonHoc dmMonHoc) {
        this.dmMonHoc = dmMonHoc;
    }

    public NhanVien getDmNhanVien() {
        return dmNhanVien;
    }

    public void setDmNhanVien(NhanVien dmNhanVien) {
        this.dmNhanVien = dmNhanVien;
    }

    public KiHocNamHoc getTkb_kiHoc_namHoc() {
        return tkb_kiHoc_namHoc;
    }

    public void setTkb_kiHoc_namHoc(KiHocNamHoc tkb_kiHoc_namHoc) {
        this.tkb_kiHoc_namHoc = tkb_kiHoc_namHoc;
    }

    public KhoaKhoaHoc getTkb_khoa_khoaHoc() {
        return tkb_khoa_khoaHoc;
    }

    public void setTkb_khoa_khoaHoc(KhoaKhoaHoc tkb_khoa_khoaHoc) {
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
    }

    public DMNganh getDmNganh() {
        return dmNganh;
    }

    public void setDmNganh(DMNganh dmNganh) {
        this.dmNganh = dmNganh;
    }

    public int getSoTietLyThuyet() {
        return soTietLyThuyet;
    }

    public void setSoTietLyThuyet(int soTietLyThuyet) {
        this.soTietLyThuyet = soTietLyThuyet;
    }

    public int getSoTietThucHanh() {
        return soTietThucHanh;
    }

    public void setSoTietThucHanh(int soTietThucHanh) {
        this.soTietThucHanh = soTietThucHanh;
    }

    public int getSoLuongToiDa() {
        return soLuongToiDa;
    }

    public void setSoLuongToiDa(int soLuongToiDa) {
        this.soLuongToiDa = soLuongToiDa;
    }

    public int getGioiHanTuanBatDau() {
        return gioiHanTuanBatDau;
    }

    public void setGioiHanTuanBatDau(int gioiHanTuanBatDau) {
        this.gioiHanTuanBatDau = gioiHanTuanBatDau;
    }

    public int getGioiHanTuanKetThuc() {
        return gioiHanTuanKetThuc;
    }

    public void setGioiHanTuanKetThuc(int gioiHanTuanKetThuc) {
        this.gioiHanTuanKetThuc = gioiHanTuanKetThuc;
    }
}
