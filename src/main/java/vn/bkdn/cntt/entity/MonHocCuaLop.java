package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Created by XuanVinh on 3/6/2017.
 */

@Entity
public class MonHocCuaLop {
    private int ID;
    @NotNull
    private Lop lop;
    @NotNull
    private MonHocCuaNganh monHocCuaNganh;
    private int HocKi;
    private int TongSoTiet;
    private int TongSoBuoi;
    private int SoBuoiLyThuyet;

    @Column(columnDefinition="INT NULLABLE")
    private int SoTietLyTuyet;
    private int SoBuoiThucHanh;
    private int SoTietThucHanh;

    private PhanCong phanCong;

    private int abc;
    private String aaaa;

    public MonHocCuaLop() {
    }

    @Id
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @ManyToOne
    @JoinColumn(name = "LopID")
    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    @ManyToOne
    @JoinColumn(name = "MonHocNganhID")
    public MonHocCuaNganh getMonHocCuaNganh() {
        return monHocCuaNganh;
    }

    public void setMonHocCuaNganh(MonHocCuaNganh monHocCuaNganh) {
        this.monHocCuaNganh = monHocCuaNganh;
    }

    public int getHocKi() {
        return HocKi;
    }

    public void setHocKi(int hocKi) {
        HocKi = hocKi;
    }

    public int getTongSoTiet() {
        return TongSoTiet;
    }

    public void setTongSoTiet(int tongSoTiet) {
        TongSoTiet = tongSoTiet;
    }

    public int getTongSoBuoi() {
        return TongSoBuoi;
    }

    public void setTongSoBuoi(int tongSoBuoi) {
        TongSoBuoi = tongSoBuoi;
    }

    public int getSoBuoiLyThuyet() {
        return SoBuoiLyThuyet;
    }

    public void setSoBuoiLyThuyet(int soBuoiLyThuyet) {
        SoBuoiLyThuyet = soBuoiLyThuyet;
    }

    public int getSoTietLyTuyet() {
        return SoTietLyTuyet;
    }

    public void setSoTietLyTuyet(int soTietLyTuyet) {
        SoTietLyTuyet = soTietLyTuyet;
    }

    public int getSoBuoiThucHanh() {
        return SoBuoiThucHanh;
    }

    public void setSoBuoiThucHanh(int soBuoiThucHanh) {
        SoBuoiThucHanh = soBuoiThucHanh;
    }

    public int getSoTietThucHanh() {
        return SoTietThucHanh;
    }

    public void setSoTietThucHanh(int soTietThucHanh) {
        SoTietThucHanh = soTietThucHanh;
    }

    @OneToOne(mappedBy = "monHocCuaLop")
    @JsonIgnore
    public PhanCong getPhanCong() {
        return phanCong;
    }

    public void setPhanCong(PhanCong phanCong) {
        this.phanCong = phanCong;
    }

    public int getAbc() {
        return abc;
    }

    public void setAbc(int abc) {
        this.abc = abc;
    }

    public String getAaaa() {
        return aaaa;
    }

    public void setAaaa(String aaaa) {
        this.aaaa = aaaa;
    }
}
