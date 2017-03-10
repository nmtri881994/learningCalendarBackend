package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by XuanVinh on 3/6/2017.
 */

@Entity
public class Nganh {
    private int ID;
    private String MaNganh;
    private String TenNganh;

    private Set<Lop> lops;
    private Set<Khoa_Nganh> khoa_nganhs;
    private Set<MonHocCuaNganh> monHocCuaNganhs;

    public Nganh() {
    }

    @Id
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaNganh() {
        return MaNganh;
    }

    public void setMaNganh(String maNganh) {
        MaNganh = maNganh;
    }

    public String getTenNganh() {
        return TenNganh;
    }

    public void setTenNganh(String tenNganh) {
        TenNganh = tenNganh;
    }

    @OneToMany(mappedBy = "nganh")
    @JsonIgnore
    public Set<Lop> getLops() {
        return lops;
    }

    public void setLops(Set<Lop> lops) {
        this.lops = lops;
    }

    @OneToMany(mappedBy = "nganh")
    @JsonIgnore
    public Set<Khoa_Nganh> getKhoa_nganhs() {
        return khoa_nganhs;
    }

    public void setKhoa_nganhs(Set<Khoa_Nganh> khoa_nganhs) {
        this.khoa_nganhs = khoa_nganhs;
    }

    @OneToMany(mappedBy = "nganh")
    public Set<MonHocCuaNganh> getMonHocCuaNganhs() {
        return monHocCuaNganhs;
    }

    public void setMonHocCuaNganhs(Set<MonHocCuaNganh> monHocCuaNganhs) {
        this.monHocCuaNganhs = monHocCuaNganhs;
    }
}
