package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class TKB_KhoaHoc {
    private int id;
    private int nam;

    private Set<TKB_Khoa_KhoaHoc> tkb_khoa_khoaHocs;

    public TKB_KhoaHoc() {
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

    @OneToMany(mappedBy = "tkb_khoaHoc")
    @JsonIgnore
    public Set<TKB_Khoa_KhoaHoc> getTkb_khoa_khoaHocs() {
        return tkb_khoa_khoaHocs;
    }

    public void setTkb_khoa_khoaHocs(Set<TKB_Khoa_KhoaHoc> tkb_khoa_khoaHocs) {
        this.tkb_khoa_khoaHocs = tkb_khoa_khoaHocs;
    }
}
