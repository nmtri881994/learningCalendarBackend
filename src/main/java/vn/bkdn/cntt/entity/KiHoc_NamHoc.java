package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class KiHoc_NamHoc {
    private int id;
    private KiHoc kiHoc;
    private NamHoc namHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    private Set<LopMonHoc> lopMonHocs;
    private Set<Khoa_KhoaHoc> khoaKhoaHocPhanNganhs;
    private Set<Khoa_KhoaHoc> khoaBatDauHocs;
    private Set<Khoa_KhoaHoc> khoaKetThucHocs;
    private Set<RegisterTime> registerTimes;
    private Set<KiHoc_NamHoc_DieuKien> kiHoc_namHoc_dieuKiens;

    public KiHoc_NamHoc() {
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
    @JoinColumn(name = "kiHocId")
    @NotNull
    @JsonIgnore
    public KiHoc getKiHoc() {
        return kiHoc;
    }

    public void setKiHoc(KiHoc kiHoc) {
        this.kiHoc = kiHoc;
    }

    @ManyToOne
    @JoinColumn(name = "namHocId")
    @NotNull
    @JsonIgnore
    public NamHoc getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(NamHoc namHoc) {
        this.namHoc = namHoc;
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

    @OneToMany(mappedBy = "kiHoc_namHoc")
    @JsonIgnore
    public Set<LopMonHoc> getLopMonHocs() {
        return lopMonHocs;
    }

    public void setLopMonHocs(Set<LopMonHoc> lopMonHocs) {
        this.lopMonHocs = lopMonHocs;
    }

    @OneToMany(mappedBy = "kiPhanNganh")
    @JsonIgnore
    public Set<Khoa_KhoaHoc> getKhoaKhoaHocPhanNganhs() {
        return khoaKhoaHocPhanNganhs;
    }

    public void setKhoaKhoaHocPhanNganhs(Set<Khoa_KhoaHoc> khoaKhoaHocPhanNganhs) {
        this.khoaKhoaHocPhanNganhs = khoaKhoaHocPhanNganhs;
    }

    @OneToMany(mappedBy = "kiBatDau")
    @JsonIgnore
    public Set<Khoa_KhoaHoc> getKhoaBatDauHocs() {
        return khoaBatDauHocs;
    }

    public void setKhoaBatDauHocs(Set<Khoa_KhoaHoc> khoaBatDauHocs) {
        this.khoaBatDauHocs = khoaBatDauHocs;
    }

    @OneToMany(mappedBy = "kiKetThuc")
    @JsonIgnore
    public Set<Khoa_KhoaHoc> getKhoaKetThucHocs() {
        return khoaKetThucHocs;
    }

    public void setKhoaKetThucHocs(Set<Khoa_KhoaHoc> khoaKetThucHocs) {
        this.khoaKetThucHocs = khoaKetThucHocs;
    }

    @OneToMany(mappedBy = "kiHoc_namHoc")
    @JsonIgnore
    public Set<RegisterTime> getRegisterTimes() {
        return registerTimes;
    }

    public void setRegisterTimes(Set<RegisterTime> registerTimes) {
        this.registerTimes = registerTimes;
    }

    @OneToMany(mappedBy = "kiHoc_namHoc")
    public Set<KiHoc_NamHoc_DieuKien> getKiHoc_namHoc_dieuKiens() {
        return kiHoc_namHoc_dieuKiens;
    }

    public void setKiHoc_namHoc_dieuKiens(Set<KiHoc_NamHoc_DieuKien> kiHoc_namHoc_dieuKiens) {
        this.kiHoc_namHoc_dieuKiens = kiHoc_namHoc_dieuKiens;
    }
}
