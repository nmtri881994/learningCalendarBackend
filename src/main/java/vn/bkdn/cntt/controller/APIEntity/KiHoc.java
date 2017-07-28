package vn.bkdn.cntt.controller.APIEntity;

/**
 * Created by Tri on 7/28/2017.
 */
public class KiHoc {
    private int id;
    private String ten;

    public KiHoc() {
    }

    public KiHoc(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
