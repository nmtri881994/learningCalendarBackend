package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 6/6/2017.
 */

@Entity
public class DMChucVu {
    private int id;
    private String ten;
    private String ghiChu;

    private Set<DMNhanVien_ChucVu> dmNhanVien_chucVus;

    public DMChucVu() {
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @OneToMany(mappedBy = "dmChucVu")
    @JsonIgnore
    public Set<DMNhanVien_ChucVu> getDmNhanVien_chucVus() {
        return dmNhanVien_chucVus;
    }

    public void setDmNhanVien_chucVus(Set<DMNhanVien_ChucVu> dmNhanVien_chucVus) {
        this.dmNhanVien_chucVus = dmNhanVien_chucVus;
    }
}
