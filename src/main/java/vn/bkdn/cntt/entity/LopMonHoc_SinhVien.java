package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class LopMonHoc_SinhVien {
    private int id;
    private LopMonHoc lopMonHoc;
    private SinhVien sinhVien;

    public LopMonHoc_SinhVien() {
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
    @JoinColumn(name = "lopMonHocId")
    @NotNull
    public LopMonHoc getLopMonHoc() {
        return lopMonHoc;
    }

    public void setLopMonHoc(LopMonHoc lopMonHoc) {
        this.lopMonHoc = lopMonHoc;
    }

    @ManyToOne
    @JoinColumn(name = "sinhVienId")
    @NotNull
    @JsonIgnore
    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }
}
