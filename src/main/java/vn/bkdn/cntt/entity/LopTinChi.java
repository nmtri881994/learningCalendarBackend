package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class LopTinChi {
    private int ID;
    private String MaLop;
    private String TenLop;
    private Khoa_Nganh khoa_nganh;

    private Set<SinhVienTinChi> sinhVienTinChiSet;

    public LopTinChi() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String maLop) {
        MaLop = maLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }

    @ManyToOne
    @JoinColumn(name = "Khoa_NganhID")
    public Khoa_Nganh getKhoa_nganh() {
        return khoa_nganh;
    }

    public void setKhoa_nganh(Khoa_Nganh khoa_nganh) {
        this.khoa_nganh = khoa_nganh;
    }

    @OneToMany(mappedBy = "lopTinChi")
    public Set<SinhVienTinChi> getSinhVienTinChiSet() {
        return sinhVienTinChiSet;
    }

    public void setSinhVienTinChiSet(Set<SinhVienTinChi> sinhVienTinChiSet) {
        this.sinhVienTinChiSet = sinhVienTinChiSet;
    }
}
