package vn.bkdn.cntt.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class TaiKhoanHeThong {
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private String hoVaTen;

    private Set<TaiKhoanHeThong_VaiTro> taiKhoanHeThong_vaiTros;

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

    @OneToMany(mappedBy = "taiKhoanHeThong")
    public Set<TaiKhoanHeThong_VaiTro> getTaiKhoanHeThong_vaiTros() {
        return taiKhoanHeThong_vaiTros;
    }

    public void setTaiKhoanHeThong_vaiTros(Set<TaiKhoanHeThong_VaiTro> taiKhoanHeThong_vaiTros) {
        this.taiKhoanHeThong_vaiTros = taiKhoanHeThong_vaiTros;
    }

    @Column(columnDefinition = "NVARCHAR(70) NOT NULL")
    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }
}
