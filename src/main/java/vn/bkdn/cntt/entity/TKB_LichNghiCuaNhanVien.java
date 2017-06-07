package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class TKB_LichNghiCuaNhanVien {
    private int id;
    private DMNhanVien dmNhanVien;
    private Date ngay;

    public TKB_LichNghiCuaNhanVien() {
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
    @NotNull
    public DMNhanVien getDmNhanVien() {
        return dmNhanVien;
    }

    public void setDmNhanVien(DMNhanVien dmNhanVien) {
        this.dmNhanVien = dmNhanVien;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
}
