package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class GiangDuong {
    private int ID;
    private String MaGiangDuong;
    private String TenGiangDuong;
    private int SoLuongToiDa;
    private String DiaChi;

    private Set<MonHocTinChi_GiangDuong> monHocTinChi_giangDuongs;

    public GiangDuong() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaGiangDuong() {
        return MaGiangDuong;
    }

    public void setMaGiangDuong(String maGiangDuong) {
        MaGiangDuong = maGiangDuong;
    }

    public String getTenGiangDuong() {
        return TenGiangDuong;
    }

    public void setTenGiangDuong(String tenGiangDuong) {
        TenGiangDuong = tenGiangDuong;
    }

    public int getSoLuongToiDa() {
        return SoLuongToiDa;
    }

    public void setSoLuongToiDa(int soLuongToiDa) {
        SoLuongToiDa = soLuongToiDa;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    @OneToMany(mappedBy = "giangDuong")
    @JsonIgnore
    public Set<MonHocTinChi_GiangDuong> getMonHocTinChi_giangDuongs() {
        return monHocTinChi_giangDuongs;
    }

    public void setMonHocTinChi_giangDuongs(Set<MonHocTinChi_GiangDuong> monHocTinChi_giangDuongs) {
        this.monHocTinChi_giangDuongs = monHocTinChi_giangDuongs;
    }
}
