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
}
