package vn.bkdn.cntt.entity.geneticAlgorithm;

/**
 * Created by Tri on 5/4/2017.
 */
public class TKB_Tuan {
    private int thuId;
    private int giangDuongId;
    private int soTiet;
    private int tuanBatDau;
    private int tuanKetThuc;

    public TKB_Tuan() {
    }

    public TKB_Tuan(int soTiet) {
        this.soTiet = soTiet;
    }

    public int getThuId() {
        return thuId;
    }

    public void setThuId(int thuId) {
        this.thuId = thuId;
    }

    public int getGiangDuongId() {
        return giangDuongId;
    }

    public void setGiangDuongId(int giangDuongId) {
        this.giangDuongId = giangDuongId;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }

    public int getTuanBatDau() {
        return tuanBatDau;
    }

    public void setTuanBatDau(int tuanBatDau) {
        this.tuanBatDau = tuanBatDau;
    }

    public int getTuanKetThuc() {
        return tuanKetThuc;
    }

    public void setTuanKetThuc(int tuanKetThuc) {
        this.tuanKetThuc = tuanKetThuc;
    }
}
