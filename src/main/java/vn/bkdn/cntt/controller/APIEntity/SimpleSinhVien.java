package vn.bkdn.cntt.controller.APIEntity;

/**
 * Created by Tri on 7/7/2017.
 */
public class SimpleSinhVien {
    private int id;
    private String hoDem;
    private String ten;
    private String lop;

    public SimpleSinhVien(int id, String hoDem, String ten, String lop) {
        this.id = id;
        this.hoDem = hoDem;
        this.ten = ten;
        this.lop = lop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoDem() {
        return hoDem;
    }

    public void setHoDem(String hoDem) {
        this.hoDem = hoDem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
