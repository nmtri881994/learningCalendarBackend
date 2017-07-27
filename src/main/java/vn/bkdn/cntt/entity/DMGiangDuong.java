package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class DMGiangDuong {
    private int id;
    private String maGiangDuong;
    private String ten;
    private DMLoaiPhong dmLoaiPhong;
    private int tang;
    private int soLuong;

    private Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays;
    private Set<DMMonHoc_GiangDuong> dm_monHoc_giangDuong;
    private Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans;

    public DMGiangDuong() {
    }

    public DMGiangDuong(int id, String maGiangDuong, String ten, DMLoaiPhong dmLoaiPhong, int tang, int soLuong) {
        this.id = id;
        this.maGiangDuong = maGiangDuong;
        this.ten = ten;
        this.dmLoaiPhong = dmLoaiPhong;
        this.tang = tang;
        this.soLuong = soLuong;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    public String getMaGiangDuong() {
        return maGiangDuong;
    }

    public void setMaGiangDuong(String maGiangDuong) {
        this.maGiangDuong = maGiangDuong;
    }

    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @ManyToOne
    @JoinColumn(name = "dmLoaiPhongId")
    @NotNull
    public DMLoaiPhong getDmLoaiPhong() {
        return dmLoaiPhong;
    }

    public void setDmLoaiPhong(DMLoaiPhong dmLoaiPhong) {
        this.dmLoaiPhong = dmLoaiPhong;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    @Column(columnDefinition = "int(4) default 50")
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @OneToMany(mappedBy = "dmGiangDuong")
    @JsonIgnore
    public Set<TKB_LichHocTheoNgay> getTkb_lichHocTheoNgays() {
        return tkb_lichHocTheoNgays;
    }

    public void setTkb_lichHocTheoNgays(Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays) {
        this.tkb_lichHocTheoNgays = tkb_lichHocTheoNgays;
    }

    @OneToMany(mappedBy = "dmGiangDuong")
    @JsonIgnore
    public Set<DMMonHoc_GiangDuong> getDm_monHoc_giangDuong() {
        return dm_monHoc_giangDuong;
    }

    public void setDm_monHoc_giangDuong(Set<DMMonHoc_GiangDuong> dm_monHoc_giangDuong) {
        this.dm_monHoc_giangDuong = dm_monHoc_giangDuong;
    }

    @OneToMany(mappedBy = "dmGiangDuong")
    @JsonIgnore
    public Set<TKB_LichHocTheoTuan> getTkb_lichHocTheoTuans() {
        return tkb_lichHocTheoTuans;
    }

    public void setTkb_lichHocTheoTuans(Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans) {
        this.tkb_lichHocTheoTuans = tkb_lichHocTheoTuans;
    }
}
