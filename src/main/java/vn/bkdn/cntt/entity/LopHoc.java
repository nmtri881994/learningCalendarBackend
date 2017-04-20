package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class LopHoc {
    private int id;
    private String ma;
    private String ten;
    private Khoa_KhoaHoc khoa_khoaHoc;

    private Set<SinhVien> sinhViens;

    public LopHoc() {
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
    @JoinColumn(name = "khoa_khoaHocId")
    public Khoa_KhoaHoc getKhoa_khoaHoc() {
        return khoa_khoaHoc;
    }

    public void setKhoa_khoaHoc(Khoa_KhoaHoc khoa_khoaHoc) {
        this.khoa_khoaHoc = khoa_khoaHoc;
    }

    @OneToMany(mappedBy = "lopHoc")
    @JsonIgnore
    public Set<SinhVien> getSinhViens() {
        return sinhViens;
    }

    public void setSinhViens(Set<SinhVien> sinhViens) {
        this.sinhViens = sinhViens;
    }
}
