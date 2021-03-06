package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_Thu {
    private int id;
    private String ten;

    private Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays;
    private Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans;
    private Set<TKB_NhanVien_NgayNghiTrongTuan> tkb_nhanVien_ngayNghiTrongTuans;

    public TKB_Thu() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(columnDefinition = "NVARCHAR(20) NOT NULL")
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @OneToMany(mappedBy = "tkb_thu")
    @JsonIgnore
    public Set<TKB_LichHocTheoNgay> getTkb_lichHocTheoNgays() {
        return tkb_lichHocTheoNgays;
    }

    public void setTkb_lichHocTheoNgays(Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays) {
        this.tkb_lichHocTheoNgays = tkb_lichHocTheoNgays;
    }

    @OneToMany(mappedBy = "tkb_thu")
    @JsonIgnore
    public Set<TKB_LichHocTheoTuan> getTkb_lichHocTheoTuans() {
        return tkb_lichHocTheoTuans;
    }

    public void setTkb_lichHocTheoTuans(Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans) {
        this.tkb_lichHocTheoTuans = tkb_lichHocTheoTuans;
    }

    @OneToMany(mappedBy = "tkb_thu")
    @JsonIgnore
    public Set<TKB_NhanVien_NgayNghiTrongTuan> getTkb_nhanVien_ngayNghiTrongTuans() {
        return tkb_nhanVien_ngayNghiTrongTuans;
    }

    public void setTkb_nhanVien_ngayNghiTrongTuans(Set<TKB_NhanVien_NgayNghiTrongTuan> tkb_nhanVien_ngayNghiTrongTuans) {
        this.tkb_nhanVien_ngayNghiTrongTuans = tkb_nhanVien_ngayNghiTrongTuans;
    }
}
