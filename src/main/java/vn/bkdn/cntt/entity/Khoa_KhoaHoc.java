package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class Khoa_KhoaHoc {
    private int id;
    private Khoa khoa;
    private KhoaHoc khoaHoc;
    private KiHoc_NamHoc kiPhanNganh;
    private KiHoc_NamHoc kiBatDau;
    private KiHoc_NamHoc kiKetThuc;

    private Set<Khoa_KhoaHoc_Nganh> khoa_khoaHoc_nganhs;
    private Set<LopMonHoc> lopMonHocs;
    private Set<RegisterTime> registerTimes;
    private Set<LopHoc> lopHocs;

    public Khoa_KhoaHoc() {
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
    @JoinColumn(name = "khoaId")
    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    @ManyToOne
    @JoinColumn(name = "khoaHocId")
    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    @ManyToOne
    @JoinColumn(name = "kiPhanNganhId")
    public KiHoc_NamHoc getKiPhanNganh() {
        return kiPhanNganh;
    }

    public void setKiPhanNganh(KiHoc_NamHoc kiPhanNganh) {
        this.kiPhanNganh = kiPhanNganh;
    }

    @ManyToOne
    @JoinColumn(name = "kiBatDauId")
    public KiHoc_NamHoc getKiBatDau() {
        return kiBatDau;
    }

    public void setKiBatDau(KiHoc_NamHoc kiBatDau) {
        this.kiBatDau = kiBatDau;
    }

    @ManyToOne
    @JoinColumn(name = "kiKetThucId")
    public KiHoc_NamHoc getKiKetThuc() {
        return kiKetThuc;
    }

    public void setKiKetThuc(KiHoc_NamHoc kiKetThuc) {
        this.kiKetThuc = kiKetThuc;
    }

    @OneToMany(mappedBy = "khoa_khoaHoc")
    public Set<Khoa_KhoaHoc_Nganh> getKhoa_khoaHoc_nganhs() {
        return khoa_khoaHoc_nganhs;
    }

    public void setKhoa_khoaHoc_nganhs(Set<Khoa_KhoaHoc_Nganh> khoa_khoaHoc_nganhs) {
        this.khoa_khoaHoc_nganhs = khoa_khoaHoc_nganhs;
    }

    @OneToMany(mappedBy = "khoa_khoaHoc")
    @JsonIgnore
    public Set<LopMonHoc> getLopMonHocs() {
        return lopMonHocs;
    }

    public void setLopMonHocs(Set<LopMonHoc> lopMonHocs) {
        this.lopMonHocs = lopMonHocs;
    }

    @OneToMany(mappedBy = "khoa_khoaHoc")
    public Set<RegisterTime> getRegisterTimes() {
        return registerTimes;
    }

    public void setRegisterTimes(Set<RegisterTime> registerTimes) {
        this.registerTimes = registerTimes;
    }

    @OneToMany(mappedBy = "khoa_khoaHoc")
    @JsonIgnore
    public Set<LopHoc> getLopHocs() {
        return lopHocs;
    }

    public void setLopHocs(Set<LopHoc> lopHocs) {
        this.lopHocs = lopHocs;
    }
}
