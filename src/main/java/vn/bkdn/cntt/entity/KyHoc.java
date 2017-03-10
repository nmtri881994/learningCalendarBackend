package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class KyHoc {
    private int ID;
    private String TenKyHoc;

    private Set<KyHoc_NamHoc> kyHoc_namHocs;

    public KyHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenKyHoc() {
        return TenKyHoc;
    }

    public void setTenKyHoc(String tenKyHoc) {
        TenKyHoc = tenKyHoc;
    }

    @OneToMany(mappedBy = "kyHoc")
    @JsonIgnore
    public Set<KyHoc_NamHoc> getKyHoc_namHocs() {
        return kyHoc_namHocs;
    }

    public void setKyHoc_namHocs(Set<KyHoc_NamHoc> kyHoc_namHocs) {
        this.kyHoc_namHocs = kyHoc_namHocs;
    }
}
