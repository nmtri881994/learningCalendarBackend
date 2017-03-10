package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/10/2017.
 */

@Entity
public class MonHocTinChi {
    private int ID;
    private String MaMonHoc;
    private String TenMonHoc;

    private int SoBuoiLyThuyet;
    private int SoTietLyThuyet;
    private int SoBuoiThucHanh;
    private int SoTietThucHanh;

    private Set<LopMonHocTinChi> lopMonHocTinChis;
    private Set<LoTrinhMonHocCuaKhoa_Nganh> loTrinhMonHocCuaKhoa_nganhs;

    public MonHocTinChi() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        MaMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        TenMonHoc = tenMonHoc;
    }

    public int getSoBuoiLyThuyet() {
        return SoBuoiLyThuyet;
    }

    public void setSoBuoiLyThuyet(int soBuoiLyThuyet) {
        SoBuoiLyThuyet = soBuoiLyThuyet;
    }

    public int getSoTietLyThuyet() {
        return SoTietLyThuyet;
    }

    public void setSoTietLyThuyet(int soTietLyThuyet) {
        SoTietLyThuyet = soTietLyThuyet;
    }

    public int getSoBuoiThucHanh() {
        return SoBuoiThucHanh;
    }

    public void setSoBuoiThucHanh(int soBuoiThucHanh) {
        SoBuoiThucHanh = soBuoiThucHanh;
    }

    public int getSoTietThucHanh() {
        return SoTietThucHanh;
    }

    public void setSoTietThucHanh(int soTietThucHanh) {
        SoTietThucHanh = soTietThucHanh;
    }

    @OneToMany(mappedBy = "monHocTinChi")
    public Set<LopMonHocTinChi> getLopMonHocTinChis() {
        return lopMonHocTinChis;
    }

    public void setLopMonHocTinChis(Set<LopMonHocTinChi> lopMonHocTinChis) {
        this.lopMonHocTinChis = lopMonHocTinChis;
    }

    @OneToMany(mappedBy = "monHocTinChi")
    @JsonIgnore
    public Set<LoTrinhMonHocCuaKhoa_Nganh> getLoTrinhMonHocCuaKhoa_nganhs() {
        return loTrinhMonHocCuaKhoa_nganhs;
    }

    public void setLoTrinhMonHocCuaKhoa_nganhs(Set<LoTrinhMonHocCuaKhoa_Nganh> loTrinhMonHocCuaKhoa_nganhs) {
        this.loTrinhMonHocCuaKhoa_nganhs = loTrinhMonHocCuaKhoa_nganhs;
    }
}
