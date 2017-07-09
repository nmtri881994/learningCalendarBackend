package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Tri on 7/7/2017.
 */

@Entity
public class TKB_LichHocTheoNgay_DiemDanh {
    private int id;
    private TKB_LichHocTheoNgay tkb_lichHocTheoNgay;
    private DMSinhVien dmSinhVien;
    private boolean presented;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_lich_hoc_theo_ngay_id")
    @JsonIgnore
    public TKB_LichHocTheoNgay getTkb_lichHocTheoNgay() {
        return tkb_lichHocTheoNgay;
    }

    public void setTkb_lichHocTheoNgay(TKB_LichHocTheoNgay tkb_lichHocTheoNgay) {
        this.tkb_lichHocTheoNgay = tkb_lichHocTheoNgay;
    }

    @ManyToOne
    @JoinColumn(name = "dm_sinh_vien_id")
    @JsonIgnore
    public DMSinhVien getDmSinhVien() {
        return dmSinhVien;
    }

    public void setDmSinhVien(DMSinhVien dmSinhVien) {
        this.dmSinhVien = dmSinhVien;
    }

    public boolean isPresented() {
        return presented;
    }

    public void setPresented(boolean presented) {
        this.presented = presented;
    }
}
