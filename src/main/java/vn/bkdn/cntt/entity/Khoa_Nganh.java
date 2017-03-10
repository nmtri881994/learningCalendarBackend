package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class Khoa_Nganh {
    private int ID;
    private Khoa khoa;
    private Nganh nganh;

    private Set<LopTinChi> lopTinChis;
    private Set<LoTrinhMonHocCuaKhoa_Nganh> loTrinhMonHocCuaKhoa_nganhs;

    public Khoa_Nganh() {
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
    @JoinColumn(name = "KhoaID")
    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    @ManyToOne
    @JoinColumn(name = "NganhID")
    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }

    @OneToMany(mappedBy = "khoa_nganh")
    public Set<LopTinChi> getLopTinChis() {
        return lopTinChis;
    }

    public void setLopTinChis(Set<LopTinChi> lopTinChis) {
        this.lopTinChis = lopTinChis;
    }

    @OneToMany(mappedBy = "khoa_nganh")
    public Set<LoTrinhMonHocCuaKhoa_Nganh> getLoTrinhMonHocCuaKhoa_nganhs() {
        return loTrinhMonHocCuaKhoa_nganhs;
    }

    public void setLoTrinhMonHocCuaKhoa_nganhs(Set<LoTrinhMonHocCuaKhoa_Nganh> loTrinhMonHocCuaKhoa_nganhs) {
        this.loTrinhMonHocCuaKhoa_nganhs = loTrinhMonHocCuaKhoa_nganhs;
    }
}
