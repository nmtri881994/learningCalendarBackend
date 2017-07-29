package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class DMLopHoc {
    private int id;
    private String ma;
    private String ten;
    private TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc;

    private Set<DMSinhVien> dmSinhVien;

    public DMLopHoc() {
    }

    public DMLopHoc(int id, String ma, String ten, TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    public String getMa() {
        return ma;
    }

    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_khoa_KhoaHocId")
    public TKB_Khoa_KhoaHoc getTkb_khoa_khoaHoc() {
        return tkb_khoa_khoaHoc;
    }

    public void setTkb_khoa_khoaHoc(TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc) {
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
    }

    @OneToMany(mappedBy = "dmLopHoc")
    @JsonIgnore
    public Set<DMSinhVien> getDmSinhVien() {
        return dmSinhVien;
    }

    public void setDmSinhVien(Set<DMSinhVien> dmSinhVien) {
        this.dmSinhVien = dmSinhVien;
    }
}
