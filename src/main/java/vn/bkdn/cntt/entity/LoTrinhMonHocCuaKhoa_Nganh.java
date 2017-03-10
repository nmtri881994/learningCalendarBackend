package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class LoTrinhMonHocCuaKhoa_Nganh {
    private int ID;
    private Khoa_Nganh khoa_nganh;
    private MonHocTinChi monHocTinChi;
    private int Ky;

    public LoTrinhMonHocCuaKhoa_Nganh() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @ManyToOne
    @JoinColumn(name = "Khoa_NganhID")
    @JsonIgnore
    public Khoa_Nganh getKhoa_nganh() {
        return khoa_nganh;
    }

    public void setKhoa_nganh(Khoa_Nganh khoa_nganh) {
        this.khoa_nganh = khoa_nganh;
    }

    @ManyToOne
    @JoinColumn(name = "MonHocTinChiID")
    public MonHocTinChi getMonHocTinChi() {
        return monHocTinChi;
    }

    public void setMonHocTinChi(MonHocTinChi monHocTinChi) {
        this.monHocTinChi = monHocTinChi;
    }

    public int getKy() {
        return Ky;
    }

    public void setKy(int ky) {
        Ky = ky;
    }
}
