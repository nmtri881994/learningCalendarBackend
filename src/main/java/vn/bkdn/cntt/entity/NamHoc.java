package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class NamHoc {
    private int ID;
    private int TenNamHoc;

    private Set<KyHoc_NamHoc> kyHoc_namHocs;

    public NamHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTenNamHoc() {
        return TenNamHoc;
    }

    public void setTenNamHoc(int tenNamHoc) {
        TenNamHoc = tenNamHoc;
    }

    @OneToMany(mappedBy = "namHoc")
    @JsonIgnore
    public Set<KyHoc_NamHoc> getKyHoc_namHocs() {
        return kyHoc_namHocs;
    }

    public void setKyHoc_namHocs(Set<KyHoc_NamHoc> kyHoc_namHocs) {
        this.kyHoc_namHocs = kyHoc_namHocs;
    }
}
