package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class KiHoc {
    private int id;
    private String ten;

    private Set<KiHoc_NamHoc> kiHoc_namHocs;

    public KiHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "NVARCHAR(20) NOT NULL")
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @OneToMany(mappedBy = "kiHoc")
    public Set<KiHoc_NamHoc> getKiHoc_namHocs() {
        return kiHoc_namHocs;
    }

    public void setKiHoc_namHocs(Set<KiHoc_NamHoc> kiHoc_namHocs) {
        this.kiHoc_namHocs = kiHoc_namHocs;
    }
}
