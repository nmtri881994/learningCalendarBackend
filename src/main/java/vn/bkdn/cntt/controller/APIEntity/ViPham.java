package vn.bkdn.cntt.controller.APIEntity;

/**
 * Created by XuanVinh on 8/6/2017.
 */
public class ViPham {
    private int dkNumber;
    private int diem;

    public ViPham() {
    }

    public ViPham(int dkNumber, int diem) {
        this.dkNumber = dkNumber;
        this.diem = diem;
    }

    public int getDkNumber() {
        return dkNumber;
    }

    public void setDkNumber(int dkNumber) {
        this.dkNumber = dkNumber;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}
