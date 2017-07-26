package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class DMDonVi {
    private int id;
    private String ma;
    private String ten;
    private DMLoaiDonVi dmLoaiDonVi;

    private Set<TKB_Khoa_KhoaHoc> tkb_khoa_KhoaHocs;
    private Set<DMNhanVien> dmNhanViens;

    public DMDonVi() {
    }

    public DMDonVi(String ma, String ten, DMLoaiDonVi dmLoaiDonVi) {
        this.ma = ma;
        this.ten = ten;
        this.dmLoaiDonVi = dmLoaiDonVi;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

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
    @JoinColumn(name = "dmLoaiDonViId")
    public DMLoaiDonVi getDmLoaiDonVi() {
        return dmLoaiDonVi;
    }

    public void setDmLoaiDonVi(DMLoaiDonVi dmLoaiDonVi) {
        this.dmLoaiDonVi = dmLoaiDonVi;
    }

    @OneToMany(mappedBy = "dmDonVi")
    public Set<TKB_Khoa_KhoaHoc> getTkb_khoa_KhoaHocs() {
        return tkb_khoa_KhoaHocs;
    }

    public void setTkb_khoa_KhoaHocs(Set<TKB_Khoa_KhoaHoc> tkb_khoa_KhoaHocs) {
        this.tkb_khoa_KhoaHocs = tkb_khoa_KhoaHocs;
    }

    @OneToMany(mappedBy = "dmDonVi")
    public Set<DMNhanVien> getDmNhanViens() {
        return dmNhanViens;
    }

    public void setDmNhanViens(Set<DMNhanVien> dmNhanViens) {
        this.dmNhanViens = dmNhanViens;
    }
}
