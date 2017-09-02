package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import vn.bkdn.cntt.controller.APIEntity.Khoa;
import vn.bkdn.cntt.entity.DMDonVi;
import vn.bkdn.cntt.entity.DMNganh;
import vn.bkdn.cntt.entity.TKB_KhoaHoc;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc;

import javax.persistence.*;

/**
 * Created by XuanVinh on 9/2/2017.
 */

@Entity
public class TKB_Khoa_KhoaHoc_Nganh_Nhom {
    private int id;
    private TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc;
    private TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh;
    private int nhom;

    public TKB_Khoa_KhoaHoc_Nganh_Nhom() {
    }

    public TKB_Khoa_KhoaHoc_Nganh_Nhom(TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc, TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh, int nhom) {
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
        this.tkb_khoa_khoaHoc_nganh = tkb_khoa_khoaHoc_nganh;
        this.nhom = nhom;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_khoa_khoaHocId")
    @JsonIgnore
    public TKB_Khoa_KhoaHoc getTkb_khoa_khoaHoc() {
        return tkb_khoa_khoaHoc;
    }

    public void setTkb_khoa_khoaHoc(TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc) {
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_khoa_khoaHoc_nganhId")
    @JsonIgnore
    public TKB_Khoa_KhoaHoc_Nganh getTkb_khoa_khoaHoc_nganh() {
        return tkb_khoa_khoaHoc_nganh;
    }

    public void setTkb_khoa_khoaHoc_nganh(TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh) {
        this.tkb_khoa_khoaHoc_nganh = tkb_khoa_khoaHoc_nganh;
    }

    public int getNhom() {
        return nhom;
    }

    public void setNhom(int nhom) {
        this.nhom = nhom;
    }
}
