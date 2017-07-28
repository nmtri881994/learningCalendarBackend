package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import vn.bkdn.cntt.controller.APIEntity.KiHocNamHoc;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_KiHoc_NamHoc {
    private int id;
    private TKB_KiHoc tkb_kiHoc;
    private TKB_NamHoc tkb_namHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private boolean daSinhLich;

    private Set<DMLopMonHoc> dmLopMonHocs;
    private Set<TKB_Khoa_KhoaHoc> tkb_khoaKhoaHocPhanNganhs;
    private Set<TKB_Khoa_KhoaHoc> tkb_khoaBatDauHocs;
    private Set<TKB_Khoa_KhoaHoc> tkb_khoaKetThucHocs;
    private Set<TKB_ThoiGianDangKy> tkb_tkbThoiGianDangKies;
    private Set<TKB_KiHoc_NamHoc_DieuKien> tkb_kiHoc_namHoc_dieuKiens;

    public TKB_KiHoc_NamHoc() {
    }

    public TKB_KiHoc_NamHoc(int id, TKB_KiHoc tkb_kiHoc, TKB_NamHoc tkb_namHoc, Date ngayBatDau, Date ngayKetThuc, boolean daSinhLich) {
        this.id = id;
        this.tkb_kiHoc = tkb_kiHoc;
        this.tkb_namHoc = tkb_namHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.daSinhLich = daSinhLich;
    }

    public TKB_KiHoc_NamHoc(KiHocNamHoc kiHocNamHoc){
        this.id = id;
        this.tkb_kiHoc = tkb_kiHoc;
        this.tkb_namHoc = tkb_namHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.daSinhLich = false;
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
    @JoinColumn(name = "tkb_kiHocId")
    @NotNull
    @JsonIgnore
    public TKB_KiHoc getTkb_kiHoc() {
        return tkb_kiHoc;
    }

    public void setTkb_kiHoc(TKB_KiHoc tkb_kiHoc) {
        this.tkb_kiHoc = tkb_kiHoc;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_namHocId")
    @NotNull
    @JsonIgnore
    public TKB_NamHoc getTkb_namHoc() {
        return tkb_namHoc;
    }

    public void setTkb_namHoc(TKB_NamHoc tkb_namHoc) {
        this.tkb_namHoc = tkb_namHoc;
    }

    @NotNull
    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    @NotNull
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public boolean isDaSinhLich() {
        return daSinhLich;
    }

    public void setDaSinhLich(boolean daSinhLich) {
        this.daSinhLich = daSinhLich;
    }

    @OneToMany(mappedBy = "tkb_kiHoc_namHoc")
    @JsonIgnore
    public Set<DMLopMonHoc> getDmLopMonHocs() {

        return dmLopMonHocs;
    }

    public void setDmLopMonHocs(Set<DMLopMonHoc> dmLopMonHocs) {
        this.dmLopMonHocs = dmLopMonHocs;
    }

    @OneToMany(mappedBy = "kiPhanNganh")
    @JsonIgnore
    public Set<TKB_Khoa_KhoaHoc> getTkb_khoaKhoaHocPhanNganhs() {
        return tkb_khoaKhoaHocPhanNganhs;
    }

    public void setTkb_khoaKhoaHocPhanNganhs(Set<TKB_Khoa_KhoaHoc> tkb_khoaKhoaHocPhanNganhs) {
        this.tkb_khoaKhoaHocPhanNganhs = tkb_khoaKhoaHocPhanNganhs;
    }

    @OneToMany(mappedBy = "kiBatDau")
    @JsonIgnore
    public Set<TKB_Khoa_KhoaHoc> getTkb_khoaBatDauHocs() {
        return tkb_khoaBatDauHocs;
    }

    public void setTkb_khoaBatDauHocs(Set<TKB_Khoa_KhoaHoc> tkb_khoaBatDauHocs) {
        this.tkb_khoaBatDauHocs = tkb_khoaBatDauHocs;
    }

    @OneToMany(mappedBy = "kiKetThuc")
    @JsonIgnore
    public Set<TKB_Khoa_KhoaHoc> getTkb_khoaKetThucHocs() {
        return tkb_khoaKetThucHocs;
    }

    public void setTkb_khoaKetThucHocs(Set<TKB_Khoa_KhoaHoc> tkb_khoaKetThucHocs) {
        this.tkb_khoaKetThucHocs = tkb_khoaKetThucHocs;
    }

    @OneToMany(mappedBy = "tkb_kiHoc_namHoc")
    @JsonIgnore
    public Set<TKB_ThoiGianDangKy> getTkb_tkbThoiGianDangKies() {
        return tkb_tkbThoiGianDangKies;
    }

    public void setTkb_tkbThoiGianDangKies(Set<TKB_ThoiGianDangKy> tkb_tkbThoiGianDangKies) {
        this.tkb_tkbThoiGianDangKies = tkb_tkbThoiGianDangKies;
    }


    @OneToMany(mappedBy = "tkb_kiHoc_namHoc")
    public Set<TKB_KiHoc_NamHoc_DieuKien> getTkb_kiHoc_namHoc_dieuKiens() {
        return tkb_kiHoc_namHoc_dieuKiens;
    }

    public void setTkb_kiHoc_namHoc_dieuKiens(Set<TKB_KiHoc_NamHoc_DieuKien> tkb_kiHoc_namHoc_dieuKiens) {
        this.tkb_kiHoc_namHoc_dieuKiens = tkb_kiHoc_namHoc_dieuKiens;
    }
}
