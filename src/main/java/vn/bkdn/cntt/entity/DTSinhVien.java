package vn.bkdn.cntt.entity;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class DTSinhVien {
    private int SinhVienID;
    private String SoPhieu;
    private String HoLot;
    private String Ten;
    private Lop lop;

    public DTSinhVien() {
    }

    @Id
    public int getSinhVienID() {
        return SinhVienID;
    }

    public void setSinhVienID(int sinhVienID) {
        SinhVienID = sinhVienID;
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
    @JoinColumn(name = "LopID")
    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }
}
