package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.TKB_KiHoc;

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

    public KiHoc(TKB_KiHoc tkb_kiHoc){
        this.id = tkb_kiHoc.getId();
        this.ten = tkb_kiHoc.getTen();
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
