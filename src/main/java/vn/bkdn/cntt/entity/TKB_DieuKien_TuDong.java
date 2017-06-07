package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 5/15/2017.
 */

@Entity
public class TKB_DieuKien_TuDong {
    private int id;
    private int ma;
    private String noiDung;
    private int minPoint;
    private int maxPoint;
    private int batBuoc;

    private Set<TKB_KiHoc_NamHoc_DieuKien> tkb_kiHoc_namHoc_dieuKiens;

    public TKB_DieuKien_TuDong() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(int minPoint) {
        this.minPoint = minPoint;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int getBatBuoc() {
        return batBuoc;
    }

    public void setBatBuoc(int batBuoc) {
        this.batBuoc = batBuoc;
    }

    @OneToMany(mappedBy = "tkb_dieuKien_tuDong")
    @JsonIgnore
    public Set<TKB_KiHoc_NamHoc_DieuKien> getTkb_kiHoc_namHoc_dieuKiens() {
        return tkb_kiHoc_namHoc_dieuKiens;
    }

    public void setTkb_kiHoc_namHoc_dieuKiens(Set<TKB_KiHoc_NamHoc_DieuKien> tkb_kiHoc_namHoc_dieuKiens) {
        this.tkb_kiHoc_namHoc_dieuKiens = tkb_kiHoc_namHoc_dieuKiens;
    }
}
