package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_NamHoc {
    private int id;
    private String name;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    private Set<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs;

    public TKB_NamHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    @OneToMany(mappedBy = "tkb_namHoc")
    public Set<TKB_KiHoc_NamHoc> getTkb_kiHoc_namHocs() {
        return tkb_kiHoc_namHocs;
    }

    public void setTkb_kiHoc_namHocs(Set<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs) {
        this.tkb_kiHoc_namHocs = tkb_kiHoc_namHocs;
    }
}
