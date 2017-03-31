package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Tri on 3/31/2017.
 */

@Entity
public class TKB_LichHocTheoNgay_SinhVienGhiChu {
    private int id;
    private TKB_LichHocTheoNgay tkbLichHocTheoNgay;
    private SinhVien sinhVien;
    private String sinhVienGhiChu;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "tkbLichHocTheoNgayId")
    @JsonIgnore
    public TKB_LichHocTheoNgay getTkbLichHocTheoNgay() {
        return tkbLichHocTheoNgay;
    }

    public void setTkbLichHocTheoNgay(TKB_LichHocTheoNgay tkbLichHocTheoNgay) {
        this.tkbLichHocTheoNgay = tkbLichHocTheoNgay;
    }

    @ManyToOne
    @JoinColumn(name = "sinhVienId")
    @JsonIgnore
    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public String getSinhVienGhiChu() {
        return sinhVienGhiChu;
    }

    public void setSinhVienGhiChu(String sinhVienGhiChu) {
        this.sinhVienGhiChu = sinhVienGhiChu;
    }
}
