package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class DMNganh {
    private int id;
    private String ten;

    private Set<TKB_Khoa_KhoaHoc_Nganh> TKB_khoa_khoaHoc_nganhs;
    private Set<DMLopMonHoc> dmLopMonHocs;
    private Set<DMSinhVien> dmSinhViens;

    public DMNganh() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @OneToMany(mappedBy = "dmNganh")
    @JsonIgnore
    public Set<TKB_Khoa_KhoaHoc_Nganh> getTKB_khoa_khoaHoc_nganhs() {
        return TKB_khoa_khoaHoc_nganhs;
    }

    public void setTKB_khoa_khoaHoc_nganhs(Set<TKB_Khoa_KhoaHoc_Nganh> TKB_khoa_khoaHoc_nganhs) {
        this.TKB_khoa_khoaHoc_nganhs = TKB_khoa_khoaHoc_nganhs;
    }

    @OneToMany(mappedBy = "dmNganh")
    @JsonIgnore
    public Set<DMLopMonHoc> getdmLopMonHocs() {
        return dmLopMonHocs;
    }

    public void setdmLopMonHocs(Set<DMLopMonHoc> dmLopMonHocs) {
        this.dmLopMonHocs = dmLopMonHocs;
    }

    @OneToMany(mappedBy = "dmNganh")
    @JsonIgnore
    public Set<DMSinhVien> getDmSinhViens() {
        return dmSinhViens;
    }

    public void setDmSinhViens(Set<DMSinhVien> dmSinhViens) {
        this.dmSinhViens = dmSinhViens;
    }
}
