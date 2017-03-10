package vn.bkdn.cntt.entity;

/**
 * Created by XuanVinh on 3/6/2017.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Lop {
    private int ID;
    private String MaLop;
    private String TenLop;
    private Nganh nganh;
    private BigDecimal NamTuyenSinh;

    private Set<MonHocCuaLop> monHocCuaLops;
    private Set<DTSinhVien> dtSinhViens;

    public Lop() {
    }

    @Id
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String maLop) {
        MaLop = maLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }

    @ManyToOne
    @JoinColumn(name = "NganhID")
    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }

    public BigDecimal getNamTuyenSinh() {
        return NamTuyenSinh;
    }

    public void setNamTuyenSinh(BigDecimal namTuyenSinh) {
        NamTuyenSinh = namTuyenSinh;
    }

    @OneToMany(mappedBy = "lop")
    public Set<MonHocCuaLop> getMonHocCuaLops() {
        return monHocCuaLops;
    }

    public void setMonHocCuaLops(Set<MonHocCuaLop> monHocCuaLops) {
        this.monHocCuaLops = monHocCuaLops;
    }

    @OneToMany(mappedBy = "lop")
    @JsonIgnore
    public Set<DTSinhVien> getDtSinhViens() {
        return dtSinhViens;
    }

    public void setDtSinhViens(Set<DTSinhVien> dtSinhViens) {
        this.dtSinhViens = dtSinhViens;
    }
}
