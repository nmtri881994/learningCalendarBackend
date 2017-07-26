package vn.bkdn.cntt.controller.APIEntity;

/**
 * Created by Tri on 7/26/2017.
 */
public class Khoa {
    private int id;
    private String ma;
    private String ten;

    public Khoa() {
    }

    public Khoa(int id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
