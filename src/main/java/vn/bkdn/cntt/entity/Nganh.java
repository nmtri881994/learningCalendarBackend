package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class Nganh {
    private int id;
    private String ten;

    private Set<Khoa_KhoaHoc_Nganh> khoa_khoaHoc_nganhs;

    public Nganh() {
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

    @OneToMany(mappedBy = "nganh")
    @JsonIgnore
    public Set<Khoa_KhoaHoc_Nganh> getKhoa_khoaHoc_nganhs() {
        return khoa_khoaHoc_nganhs;
    }

    public void setKhoa_khoaHoc_nganhs(Set<Khoa_KhoaHoc_Nganh> khoa_khoaHoc_nganhs) {
        this.khoa_khoaHoc_nganhs = khoa_khoaHoc_nganhs;
    }
}
