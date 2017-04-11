package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class Khoa {
    private int id;
    private String maKhoa;
    private String ten;

    private Set<Khoa_KhoaHoc> khoa_khoaHocs;

    public Khoa() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @OneToMany(mappedBy = "khoa")
    public Set<Khoa_KhoaHoc> getKhoa_khoaHocs() {
        return khoa_khoaHocs;
    }

    public void setKhoa_khoaHocs(Set<Khoa_KhoaHoc> khoa_khoaHocs) {
        this.khoa_khoaHocs = khoa_khoaHocs;
    }
}
