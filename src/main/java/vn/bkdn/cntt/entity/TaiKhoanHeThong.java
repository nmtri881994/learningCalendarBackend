package vn.bkdn.cntt.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class TaiKhoanHeThong {
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private VaiTro vaiTro;

    public TaiKhoanHeThong() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @ManyToOne
    @JoinColumn(name = "vaiTroId")
    @NotNull
    public VaiTro getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }
}
