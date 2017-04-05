package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 4/4/2017.
 */

@Entity
public class Khoa_KhoaHoc {
    private int id;
    private Khoa khoa;
    private KhoaHoc khoaHoc;
    private KiHoc_NamHoc kiPhanNganh;

    private Set<Khoa_KhoaHoc_Nganh> khoa_khoaHoc_nganhs;

    public Khoa_KhoaHoc() {
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
    @JsonIgnore
    @JoinColumn(name = "khoaId")
    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    @ManyToOne
    @JoinColumn(name = "khoaHocId")
    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    @ManyToOne
    @JoinColumn(name = "kiPhanNganhId")
    public KiHoc_NamHoc getKiPhanNganh() {
        return kiPhanNganh;
    }

    public void setKiPhanNganh(KiHoc_NamHoc kiPhanNganh) {
        this.kiPhanNganh = kiPhanNganh;
    }

    @OneToMany(mappedBy = "khoa_khoaHoc")
    public Set<Khoa_KhoaHoc_Nganh> getKhoa_khoaHoc_nganhs() {
        return khoa_khoaHoc_nganhs;
    }

    public void setKhoa_khoaHoc_nganhs(Set<Khoa_KhoaHoc_Nganh> khoa_khoaHoc_nganhs) {
        this.khoa_khoaHoc_nganhs = khoa_khoaHoc_nganhs;
    }
}
