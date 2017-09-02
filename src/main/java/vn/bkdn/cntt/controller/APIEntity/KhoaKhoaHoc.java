package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.*;

import java.util.Set;

/**
 * Created by Tri on 7/28/2017.
 */
public class KhoaKhoaHoc {
    private int id;
    private Khoa khoa;
    private TKB_KhoaHoc tkb_khoaHoc;
    private KiHocNamHoc kiPhanNganh;
    private KiHocNamHoc kiBatDau;
    private KiHocNamHoc kiKetThuc;

    private Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> tkb_khoa_khoaHoc_nganh_nhoms;

    public KhoaKhoaHoc() {
    }

    public KhoaKhoaHoc(int id, Khoa khoa, TKB_KhoaHoc tkb_khoaHoc, KiHocNamHoc kiPhanNganh, KiHocNamHoc kiBatDau, KiHocNamHoc kiKetThuc, Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> tkb_khoa_khoaHoc_nganh_nhoms) {
        this.id = id;
        this.khoa = khoa;
        this.tkb_khoaHoc = tkb_khoaHoc;
        this.kiPhanNganh = kiPhanNganh;
        this.kiBatDau = kiBatDau;
        this.kiKetThuc = kiKetThuc;
        this.tkb_khoa_khoaHoc_nganh_nhoms = tkb_khoa_khoaHoc_nganh_nhoms;
    }

    public KhoaKhoaHoc(KhoaKhoaHoc khoaKhoaHoc){
        this.id = khoaKhoaHoc.getId();
        this.khoa = khoaKhoaHoc.getKhoa();
        this.tkb_khoaHoc = khoaKhoaHoc.getTkb_khoaHoc();
        this.kiPhanNganh = khoaKhoaHoc.getKiPhanNganh();
        this.kiBatDau = khoaKhoaHoc.getKiBatDau();
        this.kiKetThuc = khoaKhoaHoc.getKiKetThuc();
        this.tkb_khoa_khoaHoc_nganh_nhoms = khoaKhoaHoc.getTkb_khoa_khoaHoc_nganh_nhoms();
    }

    public KhoaKhoaHoc(TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc){
        this.id = tkb_khoa_khoaHoc.getId();
        this.khoa = new Khoa(tkb_khoa_khoaHoc.getDmDonVi());
        this.tkb_khoaHoc = tkb_khoa_khoaHoc.getTkb_khoaHoc();
        this.kiPhanNganh = new KiHocNamHoc(tkb_khoa_khoaHoc.getKiPhanNganh());
        this.kiBatDau = new KiHocNamHoc(tkb_khoa_khoaHoc.getKiBatDau());
        this.kiKetThuc = new KiHocNamHoc(tkb_khoa_khoaHoc.getKiKetThuc());
        this.tkb_khoa_khoaHoc_nganh_nhoms = tkb_khoa_khoaHoc.getTkb_khoa_khoaHoc_nganh_nhoms();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public TKB_KhoaHoc getTkb_khoaHoc() {
        return tkb_khoaHoc;
    }

    public void setTkb_khoaHoc(TKB_KhoaHoc tkb_khoaHoc) {
        this.tkb_khoaHoc = tkb_khoaHoc;
    }

    public KiHocNamHoc getKiPhanNganh() {
        return kiPhanNganh;
    }

    public void setKiPhanNganh(KiHocNamHoc kiPhanNganh) {
        this.kiPhanNganh = kiPhanNganh;
    }

    public KiHocNamHoc getKiBatDau() {
        return kiBatDau;
    }

    public void setKiBatDau(KiHocNamHoc kiBatDau) {
        this.kiBatDau = kiBatDau;
    }

    public KiHocNamHoc getKiKetThuc() {
        return kiKetThuc;
    }

    public void setKiKetThuc(KiHocNamHoc kiKetThuc) {
        this.kiKetThuc = kiKetThuc;
    }

    public Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> getTkb_khoa_khoaHoc_nganh_nhoms() {
        return tkb_khoa_khoaHoc_nganh_nhoms;
    }

    public void setTkb_khoa_khoaHoc_nganh_nhoms(Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> tkb_khoa_khoaHoc_nganh_nhoms) {
        this.tkb_khoa_khoaHoc_nganh_nhoms = tkb_khoa_khoaHoc_nganh_nhoms;
    }
}
