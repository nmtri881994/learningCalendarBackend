package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMNganh;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc_Nganh;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc_Nganh_Nhom;

import java.util.Set;

/**
 * Created by Tri on 7/30/2017.
 */
public class KhoaKhoaHocNganh {
    private int id;
    private KhoaKhoaHoc khoaKhoaHoc;
    private DMNganh dmNganh;
    private Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> tkb_khoa_khoaHoc_nganh_nhoms;

    public KhoaKhoaHocNganh() {
    }

    public KhoaKhoaHocNganh(int id, KhoaKhoaHoc khoaKhoaHoc, DMNganh dmNganh, Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> tkb_khoa_khoaHoc_nganh_nhoms) {
        this.id = id;
        this.khoaKhoaHoc = khoaKhoaHoc;
        this.dmNganh = dmNganh;
        this.tkb_khoa_khoaHoc_nganh_nhoms = tkb_khoa_khoaHoc_nganh_nhoms;
    }

    public KhoaKhoaHocNganh(TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh) {
        this.id = tkb_khoa_khoaHoc_nganh.getId();
        this.khoaKhoaHoc = new KhoaKhoaHoc(tkb_khoa_khoaHoc_nganh.getTkb_khoa_khoaHoc());
        this.dmNganh = tkb_khoa_khoaHoc_nganh.getDmNganh();
        this.tkb_khoa_khoaHoc_nganh_nhoms = tkb_khoa_khoaHoc_nganh.getTkb_khoa_khoaHoc_nganh_nhoms();
    }

    public KhoaKhoaHocNganh(KhoaKhoaHocNganh khoaKhoaHocNganh) {
        this.id = khoaKhoaHocNganh.getId();
        this.khoaKhoaHoc = khoaKhoaHocNganh.getKhoaKhoaHoc();
        this.dmNganh = khoaKhoaHocNganh.getDmNganh();
        this.tkb_khoa_khoaHoc_nganh_nhoms = khoaKhoaHocNganh.getTkb_khoa_khoaHoc_nganh_nhoms();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KhoaKhoaHoc getKhoaKhoaHoc() {
        return khoaKhoaHoc;
    }

    public void setKhoaKhoaHoc(KhoaKhoaHoc khoaKhoaHoc) {
        this.khoaKhoaHoc = khoaKhoaHoc;
    }

    public DMNganh getDmNganh() {
        return dmNganh;
    }

    public void setDmNganh(DMNganh dmNganh) {
        this.dmNganh = dmNganh;
    }

    public Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> getTkb_khoa_khoaHoc_nganh_nhoms() {
        return tkb_khoa_khoaHoc_nganh_nhoms;
    }

    public void setTkb_khoa_khoaHoc_nganh_nhoms(Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> tkb_khoa_khoaHoc_nganh_nhoms) {
        this.tkb_khoa_khoaHoc_nganh_nhoms = tkb_khoa_khoaHoc_nganh_nhoms;
    }
}
