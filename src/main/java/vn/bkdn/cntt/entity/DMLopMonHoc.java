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
@JsonFilter("filter.DMLopMonHoc")
public class DMLopMonHoc {
    private int id;
    private DMMonHoc dmMonHoc;
    private DMNhanVien dmNhanVien;
    private TKB_KiHoc_NamHoc tkb_kiHoc_namHoc;
    private TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc;
    private DMNganh dmNganh;
    private int soTietLyThuyet;
    private int soTietThucHanh;
    private int soLuongToiDa;
    private int gioiHanTuanBatDau;
    private int gioiHanTuanKetThuc;

    private Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays;
    private Set<DMLopMonHoc_SinhVien> DMLopMonHoc_sinhViens;
    private Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans;

    public DMLopMonHoc() {
    }

    public DMLopMonHoc(DMLopMonHoc dmLopMonHoc) {
        this.id = dmLopMonHoc.getId();
        this.dmMonHoc = dmLopMonHoc.getDmMonHoc();
        this.dmNhanVien = dmLopMonHoc.getDmNhanVien();
        this.tkb_kiHoc_namHoc = dmLopMonHoc.getTkb_kiHoc_namHoc();
        this.tkb_khoa_khoaHoc = dmLopMonHoc.getTkb_khoa_khoaHoc();
        this.dmNganh = dmLopMonHoc.getDmNganh();
        this.soTietLyThuyet = dmLopMonHoc.getSoTietLyThuyet();
        this.soTietThucHanh = dmLopMonHoc.getSoTietThucHanh();
        this.soLuongToiDa = dmLopMonHoc.getSoLuongToiDa();
        this.gioiHanTuanBatDau = dmLopMonHoc.getGioiHanTuanBatDau();
        this.gioiHanTuanKetThuc = dmLopMonHoc.getGioiHanTuanKetThuc();
        this.tkb_lichHocTheoNgays = dmLopMonHoc.getTkb_lichHocTheoNgays();
        this.DMLopMonHoc_sinhViens = dmLopMonHoc.getDMLopMonHoc_sinhViens();
        this.tkb_lichHocTheoTuans = dmLopMonHoc.getTkb_lichHocTheoTuans();
    }

    public DMLopMonHoc(int id, DMMonHoc dmMonHoc, DMNhanVien dmNhanVien, TKB_KiHoc_NamHoc tkb_kiHoc_namHoc, TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc, DMNganh dmNganh, int soTietLyThuyet, int soTietThucHanh, int soLuongToiDa, int gioiHanTuanBatDau, int gioiHanTuanKetThuc) {
        this.id = id;
        this.dmMonHoc = dmMonHoc;
        this.dmNhanVien = dmNhanVien;
        this.tkb_kiHoc_namHoc = tkb_kiHoc_namHoc;
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
        this.dmNganh = dmNganh;
        this.soTietLyThuyet = soTietLyThuyet;
        this.soTietThucHanh = soTietThucHanh;
        this.soLuongToiDa = soLuongToiDa;
        this.gioiHanTuanBatDau = gioiHanTuanBatDau;
        this.gioiHanTuanKetThuc = gioiHanTuanKetThuc;
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
    @JoinColumn(name = "dmMonHocId")
    @NotNull
    public DMMonHoc getDmMonHoc() {
        return dmMonHoc;
    }

    public void setDmMonHoc(DMMonHoc dmMonHoc) {
        this.dmMonHoc = dmMonHoc;
    }

    @ManyToOne
    @JoinColumn(name = "dmNhanVienId")
    @NotNull
    public DMNhanVien getDmNhanVien() {
        return dmNhanVien;
    }

    public void setDmNhanVien(DMNhanVien dmNhanVien) {
        this.dmNhanVien = dmNhanVien;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_kiHoc_namHocId")
    @NotNull
    public TKB_KiHoc_NamHoc getTkb_kiHoc_namHoc() {
        return tkb_kiHoc_namHoc;
    }

    public void setTkb_kiHoc_namHoc(TKB_KiHoc_NamHoc tkb_kiHoc_namHoc) {
        this.tkb_kiHoc_namHoc = tkb_kiHoc_namHoc;
    }

    @ManyToOne
    @JoinColumn(name="tkb_khoa_khoaHocId")
    public TKB_Khoa_KhoaHoc getTkb_khoa_khoaHoc() {

        return tkb_khoa_khoaHoc;
    }

    public void setTkb_khoa_khoaHoc(TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc) {
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
    }

    @ManyToOne
    @JoinColumn(name = "dmNganhId")
    public DMNganh getDmNganh() {
        return dmNganh;
    }

    public void setDmNganh(DMNganh dmNganh) {
        this.dmNganh = dmNganh;
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

    public int getGioiHanTuanBatDau() {
        return gioiHanTuanBatDau;
    }

    public void setGioiHanTuanBatDau(int gioiHanTuanBatDau) {
        this.gioiHanTuanBatDau = gioiHanTuanBatDau;
    }

    public int getGioiHanTuanKetThuc() {
        return gioiHanTuanKetThuc;
    }

    public void setGioiHanTuanKetThuc(int gioiHanTuanKetThuc) {
        this.gioiHanTuanKetThuc = gioiHanTuanKetThuc;
    }

    @OneToMany(mappedBy = "dmLopMonHoc")
    public Set<TKB_LichHocTheoNgay> getTkb_lichHocTheoNgays() {
        return tkb_lichHocTheoNgays;
    }

    public void setTkb_lichHocTheoNgays(Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays) {
        this.tkb_lichHocTheoNgays = tkb_lichHocTheoNgays;
    }

    @OneToMany(mappedBy = "dmLopMonHoc")
    @JsonIgnore
    public Set<DMLopMonHoc_SinhVien> getDMLopMonHoc_sinhViens() {
        return DMLopMonHoc_sinhViens;
    }

    public void setDMLopMonHoc_sinhViens(Set<DMLopMonHoc_SinhVien> DMLopMonHoc_sinhViens) {
        this.DMLopMonHoc_sinhViens = DMLopMonHoc_sinhViens;
    }

    @OneToMany(mappedBy = "dmLopMonHoc", fetch = FetchType.EAGER)
    public Set<TKB_LichHocTheoTuan> getTkb_lichHocTheoTuans() {
        return tkb_lichHocTheoTuans;
    }

    public void setTkb_lichHocTheoTuans(Set<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans) {
        this.tkb_lichHocTheoTuans = tkb_lichHocTheoTuans;
    }
}
