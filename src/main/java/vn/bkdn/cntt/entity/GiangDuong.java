package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class GiangDuong {
    private int id;
    private String maGiangDuong;
    private String ten;
    private DayNha dayNha;
    private int tang;
    private int soLuong;

    private Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays;
    private Set<MonHoc_GiangDuong> monHoc_giangDuongs;
    private Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans;

    public GiangDuong() {
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
    @JoinColumn(name = "dayNhaId")
    @NotNull
    public DayNha getDayNha() {
        return dayNha;
    }

    public void setDayNha(DayNha dayNha) {
        this.dayNha = dayNha;
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

    @OneToMany(mappedBy = "giangDuong")
    @JsonIgnore
    public Set<TKB_LichHocTheoNgay> getTkb_lichHocTheoNgays() {
        return tkb_lichHocTheoNgays;
    }

    public void setTkb_lichHocTheoNgays(Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays) {
        this.tkb_lichHocTheoNgays = tkb_lichHocTheoNgays;
    }

    @OneToMany(mappedBy = "giangDuong")
    @JsonIgnore
    public Set<MonHoc_GiangDuong> getMonHoc_giangDuongs() {
        return monHoc_giangDuongs;
    }

    public void setMonHoc_giangDuongs(Set<MonHoc_GiangDuong> monHoc_giangDuongs) {
        this.monHoc_giangDuongs = monHoc_giangDuongs;
    }

    @OneToMany(mappedBy = "giangDuong")
    @JsonIgnore
    public Set<TKB_LichHocTheoTuan> getTkb_lichHocTheoTuans() {
        return tkb_lichHocTheoTuans;
    }

    public void setTkb_lichHocTheoTuans(Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans) {
        this.tkb_lichHocTheoTuans = tkb_lichHocTheoTuans;
    }
}
