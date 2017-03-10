package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/6/2017.
 */

@Entity
public class MonHocCuaNganh {
    private int ID;
    private Nganh nganh;
    private String MaMonMhoc;
    private String TenMonMhoc;

    private Set<MonHocCuaLop> monHocCuaLops;

    public MonHocCuaNganh() {
    }

    @Id
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @ManyToOne
    @JoinColumn(name = "NganhID")
    @JsonIgnore
    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }

    public String getMaMonMhoc() {
        return MaMonMhoc;
    }

    public void setMaMonMhoc(String maMonMhoc) {
        MaMonMhoc = maMonMhoc;
    }

    public String getTenMonMhoc() {
        return TenMonMhoc;
    }

    public void setTenMonMhoc(String tenMonMhoc) {
        TenMonMhoc = tenMonMhoc;
    }

    @OneToMany(mappedBy = "monHocCuaNganh")
    @JsonIgnore
    public Set<MonHocCuaLop> getMonHocCuaLops() {
        return monHocCuaLops;
    }

    public void setMonHocCuaLops(Set<MonHocCuaLop> monHocCuaLops) {
        this.monHocCuaLops = monHocCuaLops;
    }
}
