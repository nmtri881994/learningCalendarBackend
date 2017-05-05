package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 5/5/2017.
 */

@Entity
public class TKB_GiaoVien_NgayNghiTrongTuan {
    private int id;
    private GiaoVien giaoVien;
    private TKB_Thu tkb_thu;

    public TKB_GiaoVien_NgayNghiTrongTuan() {
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
    @JoinColumn(name = "giaoVienId")
    @JsonIgnore
    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_thuId")
    public TKB_Thu getTkb_thu() {
        return tkb_thu;
    }

    public void setTkb_thu(TKB_Thu tkb_thu) {
        this.tkb_thu = tkb_thu;
    }
}
