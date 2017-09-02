package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class TKB_Khoa_KhoaHoc_Nganh {
    private int id;
    private TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc;
    private DMNganh dmNganh;
    private Set<TKB_Khoa_KhoaHoc_Nganh_Nhom>  tkb_khoa_khoaHoc_nganh_nhoms;

    public TKB_Khoa_KhoaHoc_Nganh() {
    }

    public TKB_Khoa_KhoaHoc_Nganh(int id, TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc, DMNganh dmNganh) {
        this.id = id;
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
        this.dmNganh = dmNganh;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_khoa_khoaHocId")
    @JsonIgnore
    public TKB_Khoa_KhoaHoc getTkb_khoa_khoaHoc() {
        return tkb_khoa_khoaHoc;
    }

    public void setTkb_khoa_khoaHoc(TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc) {
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
    }

    @ManyToOne
    @JoinColumn(name = "dmNganhId")
    public DMNganh getDmNganh() {
        return dmNganh;
    }

    public void setDmNganh(DMNganh dmNganh) {
        this.dmNganh = dmNganh;
    }

    @OneToMany(mappedBy = "tkb_khoa_khoaHoc_nganh")
    public Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> getTkb_khoa_khoaHoc_nganh_nhoms() {
        return tkb_khoa_khoaHoc_nganh_nhoms;
    }

    public void setTkb_khoa_khoaHoc_nganh_nhoms(Set<TKB_Khoa_KhoaHoc_Nganh_Nhom> tkb_khoa_khoaHoc_nganh_nhoms) {
        this.tkb_khoa_khoaHoc_nganh_nhoms = tkb_khoa_khoaHoc_nganh_nhoms;
    }
}
