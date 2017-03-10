package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class Khoa {
    private int ID;
    private String MaKhoa;
    private String Ten;
    private String MoTa;
    private int NamTuyenSinh;

    private Set<Khoa_Nganh> khoa_nganhs;

    public Khoa() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaKhoa() {
        return MaKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        MaKhoa = maKhoa;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getNamTuyenSinh() {
        return NamTuyenSinh;
    }

    public void setNamTuyenSinh(int namTuyenSinh) {
        NamTuyenSinh = namTuyenSinh;
    }

    @OneToMany(mappedBy = "nganh")
    @JsonIgnore
    public Set<Khoa_Nganh> getKhoa_nganhs() {
        return khoa_nganhs;
    }

    public void setKhoa_nganhs(Set<Khoa_Nganh> khoa_nganhs) {
        this.khoa_nganhs = khoa_nganhs;
    }
}
