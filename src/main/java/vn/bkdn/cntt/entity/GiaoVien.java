package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class GiaoVien {
    private int id;
    private String maGiaoVien;
    private String hoDem;
    private String ten;

    private Set<LopMonHoc> lopMonHocs;
    private Set<TKB_LichNghiCuaGiaoVien> tkb_lichNghiCuaGiaoViens;
    private Set<TKB_GiaoVien_NgayNghiTrongTuan> tkb_giaoVien_ngayNghiTrongTuans;

    public GiaoVien() {
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
    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
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

    @OneToMany(mappedBy = "giaoVien")
    @JsonIgnore
    public Set<LopMonHoc> getLopMonHocs() {
        return lopMonHocs;
    }

    public void setLopMonHocs(Set<LopMonHoc> lopMonHocs) {
        this.lopMonHocs = lopMonHocs;
    }

    @OneToMany(mappedBy = "giaoVien")
    public Set<TKB_LichNghiCuaGiaoVien> getTkb_lichNghiCuaGiaoViens() {
        return tkb_lichNghiCuaGiaoViens;
    }

    public void setTkb_lichNghiCuaGiaoViens(Set<TKB_LichNghiCuaGiaoVien> tkb_lichNghiCuaGiaoViens) {
        this.tkb_lichNghiCuaGiaoViens = tkb_lichNghiCuaGiaoViens;
    }

    @OneToMany(mappedBy = "giaoVien")
    public Set<TKB_GiaoVien_NgayNghiTrongTuan> getTkb_giaoVien_ngayNghiTrongTuans() {
        return tkb_giaoVien_ngayNghiTrongTuans;
    }

    public void setTkb_giaoVien_ngayNghiTrongTuans(Set<TKB_GiaoVien_NgayNghiTrongTuan> tkb_giaoVien_ngayNghiTrongTuans) {
        this.tkb_giaoVien_ngayNghiTrongTuans = tkb_giaoVien_ngayNghiTrongTuans;
    }
}
