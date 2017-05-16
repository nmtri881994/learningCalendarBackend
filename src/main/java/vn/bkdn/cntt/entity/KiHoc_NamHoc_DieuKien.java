package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 5/15/2017.
 */

@Entity
public class KiHoc_NamHoc_DieuKien {
    private int id;
    private KiHoc_NamHoc kiHoc_namHoc;
    private DieuKien dieuKien;

    public KiHoc_NamHoc_DieuKien() {
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
    @JoinColumn(name = "kiHoc_namHocId")
    @JsonIgnore
    public KiHoc_NamHoc getKiHoc_namHoc() {
        return kiHoc_namHoc;
    }

    public void setKiHoc_namHoc(KiHoc_NamHoc kiHoc_namHoc) {
        this.kiHoc_namHoc = kiHoc_namHoc;
    }

    @ManyToOne
    @JoinColumn(name = "dieuKienId")
    public DieuKien getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(DieuKien dieuKien) {
        this.dieuKien = dieuKien;
    }
}
