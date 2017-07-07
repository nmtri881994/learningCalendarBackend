package vn.bkdn.cntt.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class DMSinhVien {
    private int id;
    private String maSinhVien;
    private String hoDem;
    private String ten;
    private DMLopHoc dmLopHoc;
    private DMNganh dmNganh;

    private Set<DMLopMonHoc_SinhVien> DMLopMonHoc_sinhViens;
    private Set<TKB_LichHocTheoNgay_SinhVienGhiChu> tkb_lichHocTheoNgay_sinhVienGhiChus;
    private Set<TKB_SinhVien_LoTrinhMonHoc> tkb_sinhVien_loTrinhMonHocs;
    private Set<TKB_LichHocTheoNgay_DiemDanh> tkb_lichHocTheoNgay_diemDanhs;

    public DMSinhVien() {
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
    @JoinColumn(name = "dmLopHocId")
    @NotNull
    public DMLopHoc getDmLopHoc() {
        return dmLopHoc;
    }

    public void setDmLopHoc(DMLopHoc dmLopHoc) {
        this.dmLopHoc = dmLopHoc;
    }

    @ManyToOne
    @JoinColumn(name = "dmNganhId")
    public DMNganh getDmNganh() {
        return dmNganh;
    }

    public void setDmNganh(DMNganh dmNganh) {
        this.dmNganh = dmNganh;
    }

    @OneToMany(mappedBy = "dmSinhVien")
    public Set<DMLopMonHoc_SinhVien> getDMLopMonHoc_sinhViens() {
        return DMLopMonHoc_sinhViens;
    }

    public void setDMLopMonHoc_sinhViens(Set<DMLopMonHoc_SinhVien> DMLopMonHoc_sinhViens) {
        this.DMLopMonHoc_sinhViens = DMLopMonHoc_sinhViens;
    }

    @OneToMany(mappedBy = "dmSinhVien")
    public Set<TKB_LichHocTheoNgay_SinhVienGhiChu> getTkb_lichHocTheoNgay_sinhVienGhiChus() {
        return tkb_lichHocTheoNgay_sinhVienGhiChus;
    }

    public void setTkb_lichHocTheoNgay_sinhVienGhiChus(Set<TKB_LichHocTheoNgay_SinhVienGhiChu> tkb_lichHocTheoNgay_sinhVienGhiChus) {
        this.tkb_lichHocTheoNgay_sinhVienGhiChus = tkb_lichHocTheoNgay_sinhVienGhiChus;
    }

    @OneToMany(mappedBy = "dmSinhVien")
    public Set<TKB_SinhVien_LoTrinhMonHoc> getTkb_sinhVien_loTrinhMonHocs() {
        return tkb_sinhVien_loTrinhMonHocs;
    }

    public void setTkb_sinhVien_loTrinhMonHocs(Set<TKB_SinhVien_LoTrinhMonHoc> tkb_sinhVien_loTrinhMonHocs) {
        this.tkb_sinhVien_loTrinhMonHocs = tkb_sinhVien_loTrinhMonHocs;
    }

    @OneToMany(mappedBy = "dmSinhVien")
    public Set<TKB_LichHocTheoNgay_DiemDanh> getTkb_lichHocTheoNgay_diemDanhs() {
        return tkb_lichHocTheoNgay_diemDanhs;
    }

    public void setTkb_lichHocTheoNgay_diemDanhs(Set<TKB_LichHocTheoNgay_DiemDanh> tkb_lichHocTheoNgay_diemDanhs) {
        this.tkb_lichHocTheoNgay_diemDanhs = tkb_lichHocTheoNgay_diemDanhs;
    }
}
