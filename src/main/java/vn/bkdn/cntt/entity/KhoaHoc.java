package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class KhoaHoc {
    private int id;
    private int nam;

    private Set<Khoa_KhoaHoc> khoa_khoaHocs;

    public KhoaHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "int(5)")
    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    @OneToMany(mappedBy = "khoaHoc")
    @JsonIgnore
    public Set<Khoa_KhoaHoc> getKhoa_khoaHocs() {
        return khoa_khoaHocs;
    }

    public void setKhoa_khoaHocs(Set<Khoa_KhoaHoc> khoa_khoaHocs) {
        this.khoa_khoaHocs = khoa_khoaHocs;
    }
}
