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
    private Khoa_KhoaHoc khoa_khoaHoc;
    private Nganh nganh;
    private int soTietLyThuyet;
    private int soTietThucHanh;
    private int soLuongToiDa;
    private int soBuoiMotTuan;
    private int soTietMotTuan;

    private Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays;
    private Set<LopMonHoc_SinhVien> lopMonHoc_sinhViens;
    private Set<LopMonHoc_SinhVien_CoTheDangKy> lopMonHoc_sinhVien_coTheDangKies;
    private Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans;

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

    @ManyToOne
    @JoinColumn(name="khoa_khoaHocId")
    public Khoa_KhoaHoc getKhoa_khoaHoc() {
        return khoa_khoaHoc;
    }

    public void setKhoa_khoaHoc(Khoa_KhoaHoc khoa_khoaHoc) {
        this.khoa_khoaHoc = khoa_khoaHoc;
    }

    @ManyToOne
    @JoinColumn(name="nganhId")
    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
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

    public int getSoBuoiMotTuan() {
        return soBuoiMotTuan;
    }

    public void setSoBuoiMotTuan(int soBuoiMotTuan) {
        this.soBuoiMotTuan = soBuoiMotTuan;
    }

    public int getSoTietMotTuan() {
        return soTietMotTuan;
    }

    public void setSoTietMotTuan(int soTietMotTuan) {
        this.soTietMotTuan = soTietMotTuan;
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

    @OneToMany(mappedBy = "lopMonHoc")
    public Set<TKB_LichHocTheoTuan> getTkb_lichHocTheoTuans() {
        return tkb_lichHocTheoTuans;
    }

    public void setTkb_lichHocTheoTuans(Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans) {
        this.tkb_lichHocTheoTuans = tkb_lichHocTheoTuans;
    }
}
