package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class LopMonHocTinChi {
    private int ID;
    private String MaLopMonHocTinChi;
    private MonHocTinChi monHocTinChi;
    private int SoLuong;

    private Set<LichHocTheoNgay> lichHocTheoNgays;
    private KyHoc_NamHoc kyHoc_namHoc;

    public LopMonHocTinChi() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaLopMonHocTinChi() {
        return MaLopMonHocTinChi;
    }

    public void setMaLopMonHocTinChi(String maLopMonHocTinChi) {
        MaLopMonHocTinChi = maLopMonHocTinChi;
    }

    @ManyToOne
    @JoinColumn(name = "MonHocTinChiID")
    @JsonIgnore
    public MonHocTinChi getMonHocTinChi() {
        return monHocTinChi;
    }

    public void setMonHocTinChi(MonHocTinChi monHocTinChi) {
        this.monHocTinChi = monHocTinChi;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    @OneToMany(mappedBy = "lopMonHocTinChi")
    public Set<LichHocTheoNgay> getLichHocTheoNgays() {
        return lichHocTheoNgays;
    }

    public void setLichHocTheoNgays(Set<LichHocTheoNgay> lichHocTheoNgays) {
        this.lichHocTheoNgays = lichHocTheoNgays;
    }

    @ManyToOne
    @JoinColumn(name = "KyHoc_NamHocID")
    public KyHoc_NamHoc getKyHoc_namHoc() {
        return kyHoc_namHoc;
    }

    public void setKyHoc_namHoc(KyHoc_NamHoc kyHoc_namHoc) {
        this.kyHoc_namHoc = kyHoc_namHoc;
    }
}
