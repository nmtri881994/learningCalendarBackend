package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class TKB_SinhVien_LoTrinhMonHoc {
    private int id;
    private int ki;
    private DMMonHoc dmMonHoc;
    private DMSinhVien dmSinhVien;
    private boolean coTheDangKy;

    public TKB_SinhVien_LoTrinhMonHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKi() {
        return ki;
    }

    public void setKi(int ki) {
        this.ki = ki;
    }

    @ManyToOne
    @JoinColumn(name = "dmMonHocId")
    @NotNull
    public DMMonHoc getDmMonHoc() {
        return dmMonHoc;
    }

    public void setDmMonHoc(DMMonHoc dmMonHoc) {
        this.dmMonHoc = dmMonHoc;
    }

    @ManyToOne
    @JoinColumn(name = "dmSinhVienId")
    @NotNull
    @JsonIgnore
    public DMSinhVien getDmSinhVien() {
        return dmSinhVien;
    }

    public void setDmSinhVien(DMSinhVien dmSinhVien) {
        this.dmSinhVien = dmSinhVien;
    }

    public boolean isCoTheDangKy() {
        return coTheDangKy;
    }

    public void setCoTheDangKy(boolean coTheDangKy) {
        this.coTheDangKy = coTheDangKy;
    }
}
