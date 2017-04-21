package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class SinhVien_LoTrinhMonHoc {
    private int id;
    private int ki;
    private MonHoc monHoc;
    private SinhVien sinhVien;
    private boolean coTheDangKy;

    public SinhVien_LoTrinhMonHoc() {
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
    @JoinColumn(name = "monHocId")
    @NotNull
    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
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

    public boolean isCoTheDangKy() {
        return coTheDangKy;
    }

    public void setCoTheDangKy(boolean coTheDangKy) {
        this.coTheDangKy = coTheDangKy;
    }
}
