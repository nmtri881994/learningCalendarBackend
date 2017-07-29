package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong_VaiTro;

/**
 * Created by Tri on 7/29/2017.
 */
public class TaiKhoanVaiTro {
    private int id;
    private TaiKhoan taiKhoan;
    private VaiTro vaiTro;

    public TaiKhoanVaiTro() {
    }

    public TaiKhoanVaiTro(int id, TaiKhoan taiKhoan, VaiTro vaiTro) {
        this.id = id;
        this.taiKhoan = taiKhoan;
        this.vaiTro = vaiTro;
    }

    public TaiKhoanVaiTro(TK_TaiKhoanHeThong_VaiTro tk_taiKhoanHeThong_vaiTro){
        this.id = tk_taiKhoanHeThong_vaiTro.getId();
        this.taiKhoan = new TaiKhoan(tk_taiKhoanHeThong_vaiTro.getTk_taiKhoanHeThong());
        this.vaiTro = new VaiTro(tk_taiKhoanHeThong_vaiTro.getTk_vaiTro());
    }

    public TaiKhoanVaiTro(TaiKhoanVaiTro taiKhoanVaiTro){
        this.id = taiKhoanVaiTro.getId();
        this.taiKhoan = taiKhoanVaiTro.getTaiKhoan();
        this.vaiTro = taiKhoanVaiTro.getVaiTro();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public VaiTro getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }
}
