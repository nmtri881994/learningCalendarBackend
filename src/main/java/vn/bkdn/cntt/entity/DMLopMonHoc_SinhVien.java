package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class DMLopMonHoc_SinhVien {
    private int id;
    private DMLopMonHoc dmLopMonHoc;
    private DMSinhVien dmSinhVien;

    public DMLopMonHoc_SinhVien() {
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
    @JoinColumn(name = "dmLopMonHocId")
    @NotNull
    public DMLopMonHoc getDmLopMonHoc() {
        return dmLopMonHoc;
    }

    public void setDmLopMonHoc(DMLopMonHoc dmLopMonHoc) {
        this.dmLopMonHoc = dmLopMonHoc;
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
}
