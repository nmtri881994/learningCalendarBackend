package vn.bkdn.cntt.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class SinhVien {
    private int id;
    private String maSinhVien;
    private String hoDem;
    private String ten;
    private LopHoc lopHoc;
    private Nganh nganh;

    private Set<LopMonHoc_SinhVien> lopMonHoc_sinhViens;
    private Set<TKB_LichHocTheoNgay_SinhVienGhiChu> tkb_lichHocTheoNgay_sinhVienGhiChus;
    private Set<SinhVien_LoTrinhMonHoc> sinhVien_loTrinhMonHocs;

    public SinhVien() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    public String getHoDem() {
        return hoDem;
    }

    public void setHoDem(String hoDem) {
        this.hoDem = hoDem;
    }

    @Column(columnDefinition = "NVARCHAR(20) NOT NULL")
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @ManyToOne
    @JoinColumn(name = "lopHocId")
    @NotNull
    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }

    @ManyToOne
    @JoinColumn(name = "nganhId")
    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }

    @OneToMany(mappedBy = "sinhVien")
    public Set<LopMonHoc_SinhVien> getLopMonHoc_sinhViens() {
        return lopMonHoc_sinhViens;
    }

    public void setLopMonHoc_sinhViens(Set<LopMonHoc_SinhVien> lopMonHoc_sinhViens) {
        this.lopMonHoc_sinhViens = lopMonHoc_sinhViens;
    }

    @OneToMany(mappedBy = "sinhVien")
    public Set<TKB_LichHocTheoNgay_SinhVienGhiChu> getTkb_lichHocTheoNgay_sinhVienGhiChus() {
        return tkb_lichHocTheoNgay_sinhVienGhiChus;
    }

    public void setTkb_lichHocTheoNgay_sinhVienGhiChus(Set<TKB_LichHocTheoNgay_SinhVienGhiChu> tkb_lichHocTheoNgay_sinhVienGhiChus) {
        this.tkb_lichHocTheoNgay_sinhVienGhiChus = tkb_lichHocTheoNgay_sinhVienGhiChus;
    }

    @OneToMany(mappedBy = "sinhVien")
    public Set<SinhVien_LoTrinhMonHoc> getSinhVien_loTrinhMonHocs() {
        return sinhVien_loTrinhMonHocs;
    }

    public void setSinhVien_loTrinhMonHocs(Set<SinhVien_LoTrinhMonHoc> sinhVien_loTrinhMonHocs) {
        this.sinhVien_loTrinhMonHocs = sinhVien_loTrinhMonHocs;
    }
}
