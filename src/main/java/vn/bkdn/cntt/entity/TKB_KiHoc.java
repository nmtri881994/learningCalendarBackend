package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_KiHoc {
    private int id;
    private String ten;

    private Set<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs;

    public TKB_KiHoc() {
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

    @OneToMany(mappedBy = "tkb_kiHoc")
    public Set<TKB_KiHoc_NamHoc> getTkb_kiHoc_namHocs() {
        return tkb_kiHoc_namHocs;
    }

    public void setTkb_kiHoc_namHocs(Set<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs) {
        this.tkb_kiHoc_namHocs = tkb_kiHoc_namHocs;
    }
}
