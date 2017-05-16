package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 5/15/2017.
 */

@Entity
public class DieuKien {
    private int id;
    private int ma;
    private String noiDung;
    private int minPoint;
    private int maxPoint;

    private Set<KiHoc_NamHoc_DieuKien> kiHoc_namHoc_dieuKiens;

    public DieuKien() {
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

    @OneToMany(mappedBy = "dieuKien")
    @JsonIgnore
    public Set<KiHoc_NamHoc_DieuKien> getKiHoc_namHoc_dieuKiens() {
        return kiHoc_namHoc_dieuKiens;
    }

    public void setKiHoc_namHoc_dieuKiens(Set<KiHoc_NamHoc_DieuKien> kiHoc_namHoc_dieuKiens) {
        this.kiHoc_namHoc_dieuKiens = kiHoc_namHoc_dieuKiens;
    }
}
