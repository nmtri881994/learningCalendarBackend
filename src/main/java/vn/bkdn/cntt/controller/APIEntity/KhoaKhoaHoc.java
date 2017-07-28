package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMDonVi;
import vn.bkdn.cntt.entity.TKB_KhoaHoc;
import vn.bkdn.cntt.entity.TKB_KiHoc_NamHoc;

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

    public KhoaKhoaHoc() {
    }

    public KhoaKhoaHoc(int id, Khoa khoa, TKB_KhoaHoc tkb_khoaHoc, KiHocNamHoc kiPhanNganh, KiHocNamHoc kiBatDau, KiHocNamHoc kiKetThuc) {
        this.id = id;
        this.khoa = khoa;
        this.tkb_khoaHoc = tkb_khoaHoc;
        this.kiPhanNganh = kiPhanNganh;
        this.kiBatDau = kiBatDau;
        this.kiKetThuc = kiKetThuc;
    }

    public KhoaKhoaHoc(KhoaKhoaHoc khoaKhoaHoc){
        this.id = khoaKhoaHoc.getId();
        this.khoa = khoaKhoaHoc.getKhoa();
        this.tkb_khoaHoc = khoaKhoaHoc.getTkb_khoaHoc();
        this.kiPhanNganh = khoaKhoaHoc.getKiPhanNganh();
        this.kiBatDau = khoaKhoaHoc.getKiBatDau();
        this.kiKetThuc = khoaKhoaHoc.getKiKetThuc();
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
}
