package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/16/2017.
 */

@Entity
public class TK_TaiKhoanHeThong_VaiTro {
    private int id;
    private TK_TaiKhoanHeThong tk_taiKhoanHeThong;
    private TK_VaiTro tk_vaiTro;

    public TK_TaiKhoanHeThong_VaiTro() {
    }

    public TK_TaiKhoanHeThong_VaiTro(int id, TK_TaiKhoanHeThong tk_taiKhoanHeThong, TK_VaiTro tk_vaiTro) {
        this.id = id;
        this.tk_taiKhoanHeThong = tk_taiKhoanHeThong;
        this.tk_vaiTro = tk_vaiTro;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "tk_taiKhoanHeThongId")
    @JsonIgnore
    public TK_TaiKhoanHeThong getTk_taiKhoanHeThong() {
        return tk_taiKhoanHeThong;
    }

    public void setTk_taiKhoanHeThong(TK_TaiKhoanHeThong tk_taiKhoanHeThong) {
        this.tk_taiKhoanHeThong = tk_taiKhoanHeThong;
    }

    @ManyToOne
    @JoinColumn(name = "tk_vaiTroId")
    public TK_VaiTro getTk_vaiTro() {
        return tk_vaiTro;
    }

    public void setTk_vaiTro(TK_VaiTro tk_vaiTro) {
        this.tk_vaiTro = tk_vaiTro;
    }
}
