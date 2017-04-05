package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class Khoa_KhoaHoc_Nganh {
    private int id;
    private Khoa_KhoaHoc khoa_khoaHoc;
    private Nganh nganh;

    public Khoa_KhoaHoc_Nganh() {
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
    @JoinColumn(name = "khoa_khoaHocId")
    @JsonIgnore
    public Khoa_KhoaHoc getKhoa_khoaHoc() {
        return khoa_khoaHoc;
    }

    public void setKhoa_khoaHoc(Khoa_KhoaHoc khoa_khoaHoc) {
        this.khoa_khoaHoc = khoa_khoaHoc;
    }

    @ManyToOne
    @JoinColumn(name = "nganhId")
    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }
}
