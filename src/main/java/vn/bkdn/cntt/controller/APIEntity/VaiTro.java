package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.TK_VaiTro;

/**
 * Created by Tri on 7/29/2017.
 */
public class VaiTro {
    private int id;
    private String tenVaiTro;

    public VaiTro() {
    }

    public VaiTro(int id, String tenVaiTro) {
        this.id = id;
        this.tenVaiTro = tenVaiTro;
    }

    public VaiTro(TK_VaiTro tk_vaiTro){
        this.id = tk_vaiTro.getId();
        this.tenVaiTro = tk_vaiTro.getTenVaiTro();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }
}
