package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong;

/**
 * Created by Tri on 7/29/2017.
 */
public class TaiKhoan {
    private int id;
    private String tenDangNhap;
    private String hoVaTen;

    public TaiKhoan() {
    }

    public TaiKhoan(int id, String tenDangNhap, String hoVaTen) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.hoVaTen = hoVaTen;
    }

    public TaiKhoan(TK_TaiKhoanHeThong tk_taiKhoanHeThong){
        this.id = tk_taiKhoanHeThong.getId();
        this.tenDangNhap = tk_taiKhoanHeThong.getTenDangNhap();
        this.hoVaTen = tk_taiKhoanHeThong.getHoVaTen();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }
}
