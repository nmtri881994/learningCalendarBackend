package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import vn.bkdn.cntt.entity.DMDonVi;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 6/6/2017.
 */

@Entity
public class DMLoaiDonVi {
    private int id;
    private String ten;
    private String ghiChu;

    private Set<DMDonVi> dmDonVis;

    public DMLoaiDonVi() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @OneToMany(mappedBy = "dmLoaiDonVi")
    @JsonIgnore
    public Set<DMDonVi> getDmDonVis() {
        return dmDonVis;
    }

    public void setDmDonVis(Set<DMDonVi> dmDonVis) {
        this.dmDonVis = dmDonVis;
    }
}
