package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class SinhVienTinChi {
    private int ID;
    private String SoPhieu;
    private String HoLot;
    private String Ten;
    private LopTinChi lopTinChi;

    public SinhVienTinChi() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSoPhieu() {
        return SoPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        SoPhieu = soPhieu;
    }

    public String getHoLot() {
        return HoLot;
    }

    public void setHoLot(String hoLot) {
        HoLot = hoLot;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    @ManyToOne
    @JoinColumn(name = "LopTinChiID")
    @JsonIgnore
    public LopTinChi getLopTinChi() {
        return lopTinChi;
    }

    public void setLopTinChi(LopTinChi lopTinChi) {
        this.lopTinChi = lopTinChi;
    }
}
