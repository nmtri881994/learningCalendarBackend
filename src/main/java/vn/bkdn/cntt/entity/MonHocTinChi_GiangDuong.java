package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class MonHocTinChi_GiangDuong {
    private int ID;
    private MonHocTinChi monHocTinChi;
    private GiangDuong giangDuong;

    public MonHocTinChi_GiangDuong() {
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
    @JoinColumn(name = "MonHocTinChiID")
    @JsonIgnore
    public MonHocTinChi getMonHocTinChi() {
        return monHocTinChi;
    }

    public void setMonHocTinChi(MonHocTinChi monHocTinChi) {
        this.monHocTinChi = monHocTinChi;
    }

    @ManyToOne
    @JoinColumn(name = "GiangDuongID")
    public GiangDuong getGiangDuong() {
        return giangDuong;
    }

    public void setGiangDuong(GiangDuong giangDuong) {
        this.giangDuong = giangDuong;
    }
}
