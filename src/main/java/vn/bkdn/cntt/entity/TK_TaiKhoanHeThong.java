package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class TK_TaiKhoanHeThong {
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private String hoVaTen;

    private Set<TK_TaiKhoanHeThong_VaiTro> tk_taiKhoanHeThong_vaiTros;

    public TK_TaiKhoanHeThong() {
    }

    public TK_TaiKhoanHeThong(int id, String tenDangNhap, String matKhau, String hoVaTen) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoVaTen = hoVaTen;
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

    @OneToMany(mappedBy = "tk_taiKhoanHeThong")
    public Set<TK_TaiKhoanHeThong_VaiTro> getTk_taiKhoanHeThong_vaiTros() {
        return tk_taiKhoanHeThong_vaiTros;
    }

    public void setTk_taiKhoanHeThong_vaiTros(Set<TK_TaiKhoanHeThong_VaiTro> tk_taiKhoanHeThong_vaiTros) {
        this.tk_taiKhoanHeThong_vaiTros = tk_taiKhoanHeThong_vaiTros;
    }

    @Column(columnDefinition = "NVARCHAR(70) NOT NULL")
    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }
}
