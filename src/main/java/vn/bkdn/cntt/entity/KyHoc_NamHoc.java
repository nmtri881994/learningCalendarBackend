package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class KyHoc_NamHoc {
    private int ID;
    private KyHoc kyHoc;
    private NamHoc namHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    private Set<LopMonHocTinChi> lopMonHocTinChis;

    public KyHoc_NamHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @ManyToOne
    @JoinColumn(name = "KyHocID")
    public KyHoc getKyHoc() {
        return kyHoc;
    }

    public void setKyHoc(KyHoc kyHoc) {
        this.kyHoc = kyHoc;
    }

    @ManyToOne
    @JoinColumn(name = "NamHocID")
    public NamHoc getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(NamHoc namHoc) {
        this.namHoc = namHoc;
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

    @OneToMany(mappedBy = "kyHoc_namHoc")
    @JsonIgnore
    public Set<LopMonHocTinChi> getLopMonHocTinChis() {
        return lopMonHocTinChis;
    }

    public void setLopMonHocTinChis(Set<LopMonHocTinChi> lopMonHocTinChis) {
        this.lopMonHocTinChis = lopMonHocTinChis;
    }
}
