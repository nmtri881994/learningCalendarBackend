package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Tri on 3/31/2017.
 */

@Entity
public class TKB_LichHocTheoNgay_SinhVienGhiChu {
    private int id;
    private TKB_LichHocTheoNgay tkb_lichHocTheoNgay;
    private DMSinhVien dmSinhVien;
    private String sinhVienGhiChu;

    public TKB_LichHocTheoNgay_SinhVienGhiChu() {
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
    @JoinColumn(name = "tkb_lichHocTheoNgayId")
    @JsonIgnore
    public TKB_LichHocTheoNgay getTkb_lichHocTheoNgay() {
        return tkb_lichHocTheoNgay;
    }

    public void setTkb_lichHocTheoNgay(TKB_LichHocTheoNgay tkb_lichHocTheoNgay) {
        this.tkb_lichHocTheoNgay = tkb_lichHocTheoNgay;
    }

    @ManyToOne
    @JoinColumn(name = "dmSinhVienId")
    @JsonIgnore
    public DMSinhVien getDmSinhVien() {
        return dmSinhVien;
    }

    public void setDmSinhVien(DMSinhVien dmSinhVien) {
        this.dmSinhVien = dmSinhVien;
    }

    public String getSinhVienGhiChu() {
        return sinhVienGhiChu;
    }

    public void setSinhVienGhiChu(String sinhVienGhiChu) {
        this.sinhVienGhiChu = sinhVienGhiChu;
    }
}
