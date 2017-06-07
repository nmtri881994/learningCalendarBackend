package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 5/5/2017.
 */

@Entity
public class TKB_NhanVien_NgayNghiTrongTuan {
    private int id;
    private DMNhanVien dmNhanVien;
    private TKB_Thu tkb_thu;

    public TKB_NhanVien_NgayNghiTrongTuan() {
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
    @JoinColumn(name = "dmNhanVienId")
    @JsonIgnore
    public DMNhanVien getDmNhanVien() {
        return dmNhanVien;
    }

    public void setDmNhanVien(DMNhanVien dmNhanVien) {
        this.dmNhanVien = dmNhanVien;
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
