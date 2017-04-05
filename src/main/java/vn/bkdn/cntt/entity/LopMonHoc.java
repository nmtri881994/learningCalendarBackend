package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
@JsonFilter("filter.LopMonHoc")
public class LopMonHoc {
    private int id;
    private MonHoc monHoc;
    private GiaoVien giaoVien;
    private KiHoc_NamHoc kiHoc_namHoc;
    private int soTietLyThuyet;
    private int soTietThucHanh;
    private int soLuongToiDa;

    private Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays;
    private Set<LopMonHoc_SinhVien> lopMonHoc_sinhViens;
    private Set<LopMonHoc_SinhVien_CoTheDangKy> lopMonHoc_sinhVien_coTheDangKies;

    public LopMonHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "monHocId")
    @NotNull
    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    @ManyToOne
    @JoinColumn(name = "giaoVienId")
    @NotNull
    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    @ManyToOne
    @JoinColumn(name = "kiHoc_NamHocId")
    @NotNull
    public KiHoc_NamHoc getKiHoc_namHoc() {
        return kiHoc_namHoc;
    }

    public void setKiHoc_namHoc(KiHoc_NamHoc kiHoc_namHoc) {
        this.kiHoc_namHoc = kiHoc_namHoc;
    }

    public int getSoTietLyThuyet() {
        return soTietLyThuyet;
    }

    public void setSoTietLyThuyet(int soTietLyThuyet) {
        this.soTietLyThuyet = soTietLyThuyet;
    }

    public int getSoTietThucHanh() {
        return soTietThucHanh;
    }

    public void setSoTietThucHanh(int soTietThucHanh) {
        this.soTietThucHanh = soTietThucHanh;
    }

    @Column(columnDefinition = "int(4) default 100")
    public int getSoLuongToiDa() {
        return soLuongToiDa;
    }

    public void setSoLuongToiDa(int soLuongToiDa) {
        this.soLuongToiDa = soLuongToiDa;
    }

    @OneToMany(mappedBy = "lopMonHoc")
    public Set<TKB_LichHocTheoNgay> getTkb_lichHocTheoNgays() {
        return tkb_lichHocTheoNgays;
    }

    public void setTkb_lichHocTheoNgays(Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays) {
        this.tkb_lichHocTheoNgays = tkb_lichHocTheoNgays;
    }

    @OneToMany(mappedBy = "lopMonHoc")
    @JsonIgnore
    public Set<LopMonHoc_SinhVien> getLopMonHoc_sinhViens() {
        return lopMonHoc_sinhViens;
    }

    public void setLopMonHoc_sinhViens(Set<LopMonHoc_SinhVien> lopMonHoc_sinhViens) {
        this.lopMonHoc_sinhViens = lopMonHoc_sinhViens;
    }

    @OneToMany(mappedBy = "lopMonHoc")
    @JsonIgnore
    public Set<LopMonHoc_SinhVien_CoTheDangKy> getLopMonHoc_sinhVien_coTheDangKies() {
        return lopMonHoc_sinhVien_coTheDangKies;
    }

    public void setLopMonHoc_sinhVien_coTheDangKies(Set<LopMonHoc_SinhVien_CoTheDangKy> lopMonHoc_sinhVien_coTheDangKies) {
        this.lopMonHoc_sinhVien_coTheDangKies = lopMonHoc_sinhVien_coTheDangKies;
    }
}
