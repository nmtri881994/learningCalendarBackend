package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/16/2017.
 */

@Entity
public class TaiKhoanHeThong_VaiTro {
    private int id;
    private TaiKhoanHeThong taiKhoanHeThong;
    private VaiTro vaiTro;

    public TaiKhoanHeThong_VaiTro() {
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
    @JoinColumn(name = "taiKhoanHeThongId")
    @JsonIgnore
    public TaiKhoanHeThong getTaiKhoanHeThong() {
        return taiKhoanHeThong;
    }

    public void setTaiKhoanHeThong(TaiKhoanHeThong taiKhoanHeThong) {
        this.taiKhoanHeThong = taiKhoanHeThong;
    }

    @ManyToOne
    @JoinColumn(name = "vaiTroId")
    public VaiTro getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }
}
