package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Tri on 6/7/2017.
 */

@Entity
public class DMNhanVien_ChucVu {
    private int id;
    private DMNhanVien dmNhanVien;
    private DMChucVu dmChucVu;

    public DMNhanVien_ChucVu() {
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
    @JoinColumn(name = "dmChucVuId")
    public DMChucVu getDmChucVu() {
        return dmChucVu;
    }

    public void setDmChucVu(DMChucVu dmChucVu) {
        this.dmChucVu = dmChucVu;
    }
}
