package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class TKB_Khoa_KhoaHoc {
    private int id;
    private DMDonVi dmDonVi;
    private TKB_KhoaHoc tkb_khoaHoc;
    private TKB_KiHoc_NamHoc kiPhanNganh;
    private TKB_KiHoc_NamHoc kiBatDau;
    private TKB_KiHoc_NamHoc kiKetThuc;

    private Set<TKB_Khoa_KhoaHoc_Nganh> TKB_khoa_khoaHoc_nganhs;
    private Set<DMLopMonHoc> dmLopMonHocs;
    private Set<TKB_ThoiGianDangKy> tkbThoiGianDangKies;
    private Set<DMLopHoc> dmLopHocs;

    public TKB_Khoa_KhoaHoc() {
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
    @JsonIgnore
    @JoinColumn(name = "dmDonViId")
    public DMDonVi getDmDonVi() {
        return dmDonVi;
    }

    public void setDmDonVi(DMDonVi dmDonVi) {
        this.dmDonVi = dmDonVi;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_khoaHocId")
    public TKB_KhoaHoc getTkb_khoaHoc() {
        return tkb_khoaHoc;
    }

    public void setTkb_khoaHoc(TKB_KhoaHoc tkb_khoaHoc) {
        this.tkb_khoaHoc = tkb_khoaHoc;
    }

    @ManyToOne
    @JoinColumn(name = "kiPhanNganhId")
    public TKB_KiHoc_NamHoc getKiPhanNganh() {
        return kiPhanNganh;
    }

    public void setKiPhanNganh(TKB_KiHoc_NamHoc kiPhanNganh) {
        this.kiPhanNganh = kiPhanNganh;
    }

    @ManyToOne
    @JoinColumn(name = "kiBatDauId")
    public TKB_KiHoc_NamHoc getKiBatDau() {
        return kiBatDau;
    }

    public void setKiBatDau(TKB_KiHoc_NamHoc kiBatDau) {
        this.kiBatDau = kiBatDau;
    }

    @ManyToOne
    @JoinColumn(name = "kiKetThucId")
    public TKB_KiHoc_NamHoc getKiKetThuc() {
        return kiKetThuc;
    }

    public void setKiKetThuc(TKB_KiHoc_NamHoc kiKetThuc) {
        this.kiKetThuc = kiKetThuc;
    }

    @OneToMany(mappedBy = "tkb_khoa_khoaHoc")
    public Set<TKB_Khoa_KhoaHoc_Nganh> getTKB_khoa_khoaHoc_nganhs() {
        return TKB_khoa_khoaHoc_nganhs;
    }

    public void setTKB_khoa_khoaHoc_nganhs(Set<TKB_Khoa_KhoaHoc_Nganh> TKB_khoa_khoaHoc_nganhs) {
        this.TKB_khoa_khoaHoc_nganhs = TKB_khoa_khoaHoc_nganhs;
    }

    @OneToMany(mappedBy = "tkb_khoa_khoaHoc")
    @JsonIgnore
    public Set<DMLopMonHoc> getDmLopMonHocs() {
        return dmLopMonHocs;
    }

    public void setDmLopMonHocs(Set<DMLopMonHoc> dmLopMonHocs) {
        this.dmLopMonHocs = dmLopMonHocs;
    }

    @OneToMany(mappedBy = "tkb_khoa_khoaHoc")
    public Set<TKB_ThoiGianDangKy> getTkbThoiGianDangKies() {
        return tkbThoiGianDangKies;
    }

    public void setTkbThoiGianDangKies(Set<TKB_ThoiGianDangKy> tkbThoiGianDangKies) {
        this.tkbThoiGianDangKies = tkbThoiGianDangKies;
    }

    @OneToMany(mappedBy = "tkb_khoa_khoaHoc")
    @JsonIgnore
    public Set<DMLopHoc> getDmLopHocs() {
        return dmLopHocs;
    }

    public void setDmLopHocs(Set<DMLopHoc> dmLopHocs) {
        this.dmLopHocs = dmLopHocs;
    }
}