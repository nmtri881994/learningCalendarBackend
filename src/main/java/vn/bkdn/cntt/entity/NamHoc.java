package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class NamHoc {
    private int id;
    private int name;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    private Set<KiHoc_NamHoc> kiHoc_namHocs;

    public NamHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
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

    @OneToMany(mappedBy = "namHoc")
    public Set<KiHoc_NamHoc> getKiHoc_namHocs() {
        return kiHoc_namHocs;
    }

    public void setKiHoc_namHocs(Set<KiHoc_NamHoc> kiHoc_namHocs) {
        this.kiHoc_namHocs = kiHoc_namHocs;
    }
}
