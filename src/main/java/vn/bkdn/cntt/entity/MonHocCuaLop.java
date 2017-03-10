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

    private PhanCong phanCong;

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
    @JsonIgnore
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

    @OneToOne(mappedBy = "monHocCuaLop")
    public PhanCong getPhanCong() {
        return phanCong;
    }

    public void setPhanCong(PhanCong phanCong) {
        this.phanCong = phanCong;
    }

}
