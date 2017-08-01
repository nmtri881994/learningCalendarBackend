package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class DMMonHoc {
    private int id;
    private String maMonHoc;
    private String ten;
    private float soTinChi;

    private Set<DMLopMonHoc> dmLopMonHocs;
    private Set<DMMonHoc_GiangDuong> dm_monHoc_giangDuong;
    private Set<TKB_SinhVien_LoTrinhMonHoc> tkb_sinhVien_loTrinhMonHocs;

    public DMMonHoc() {
    }

    public DMMonHoc(int id, String maMonHoc, String ten, float soTinChi) {
        this.id = id;
        this.maMonHoc = maMonHoc;
        this.ten = ten;
        this.soTinChi = soTinChi;
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
    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    @Column(columnDefinition = "NVARCHAR(30) NOT NULL")
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(float soTinChi) {
        this.soTinChi = soTinChi;
    }

    @OneToMany(mappedBy = "dmMonHoc")
    @JsonIgnore
    public Set<DMLopMonHoc> getdmLopMonHocs() {
        return dmLopMonHocs;
    }

    public void setdmLopMonHocs(Set<DMLopMonHoc> dmLopMonHocs) {
        this.dmLopMonHocs = dmLopMonHocs;
    }

    @OneToMany(mappedBy = "dmMonHoc")
    public Set<DMMonHoc_GiangDuong> getDm_monHoc_giangDuong() {
        return dm_monHoc_giangDuong;
    }

    public void setDm_monHoc_giangDuong(Set<DMMonHoc_GiangDuong> dm_monHoc_giangDuong) {
        this.dm_monHoc_giangDuong = dm_monHoc_giangDuong;
    }

    @OneToMany(mappedBy = "dmMonHoc")
    @JsonIgnore
    public Set<TKB_SinhVien_LoTrinhMonHoc> getTkb_sinhVien_loTrinhMonHocs() {
        return tkb_sinhVien_loTrinhMonHocs;
    }

    public void setTkb_sinhVien_loTrinhMonHocs(Set<TKB_SinhVien_LoTrinhMonHoc> tkb_sinhVien_loTrinhMonHocs) {
        this.tkb_sinhVien_loTrinhMonHocs = tkb_sinhVien_loTrinhMonHocs;
    }
}
