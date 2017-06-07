package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class DMNhanVien {
    private int id;
    private String maNhanVien;
    private String hoDem;
    private String ten;
    private DMDonVi dmDonVi;
    private DMChucVu dmChucVu;

    private Set<DMLopMonHoc> dmLopMonHocs;
    private Set<TKB_LichNghiCuaNhanVien> tkb_lichNghiCuaNhanViens;
    private Set<TKB_NhanVien_NgayNghiTrongTuan> tkb_nhanVien_ngayNghiTrongTuans;

    public DMNhanVien() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
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
    @JoinColumn(name = "dmDonViId")
    @JsonIgnore
    public DMDonVi getDmDonVi() {
        return dmDonVi;
    }

    public void setDmDonVi(DMDonVi dmDonVi) {
        this.dmDonVi = dmDonVi;
    }

    @ManyToOne
    @JoinColumn(name = "dmChucVuId")
    @JsonIgnore
    public DMChucVu getDmChucVu() {
        return dmChucVu;
    }

    public void setDmChucVu(DMChucVu dmChucVu) {
        this.dmChucVu = dmChucVu;
    }

    @OneToMany(mappedBy = "dmNhanVien")
    @JsonIgnore
    public Set<DMLopMonHoc> getDmLopMonHocs() {
        return dmLopMonHocs;
    }

    public void setDmLopMonHocs(Set<DMLopMonHoc> dmLopMonHocs) {
        this.dmLopMonHocs = dmLopMonHocs;
    }

    @OneToMany(mappedBy = "dmNhanVien")
    public Set<TKB_LichNghiCuaNhanVien> getTkb_lichNghiCuaNhanViens() {
        return tkb_lichNghiCuaNhanViens;
    }

    public void setTkb_lichNghiCuaNhanViens(Set<TKB_LichNghiCuaNhanVien> tkb_lichNghiCuaNhanViens) {
        this.tkb_lichNghiCuaNhanViens = tkb_lichNghiCuaNhanViens;
    }

    @OneToMany(mappedBy = "dmNhanVien")
    public Set<TKB_NhanVien_NgayNghiTrongTuan> getTkb_nhanVien_ngayNghiTrongTuans() {
        return tkb_nhanVien_ngayNghiTrongTuans;
    }

    public void setTkb_nhanVien_ngayNghiTrongTuans(Set<TKB_NhanVien_NgayNghiTrongTuan> tkb_nhanVien_ngayNghiTrongTuans) {
        this.tkb_nhanVien_ngayNghiTrongTuans = tkb_nhanVien_ngayNghiTrongTuans;
    }
}
