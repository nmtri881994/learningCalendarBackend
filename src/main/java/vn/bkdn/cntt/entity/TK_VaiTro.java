package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class TK_VaiTro {
    private int id;
    private String tenVaiTro;

    private Set<TK_VaiTro_ChucNang> tk_vaiTro_chucNangs;
    private Set<TK_TaiKhoanHeThong_VaiTro> tk_taiKhoanHeThong_vaiTros;

    public TK_VaiTro() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "NVARCHAR(20) NOT NULL")
    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    @OneToMany(mappedBy = "tk_vaiTro")
    public Set<TK_VaiTro_ChucNang> getTk_vaiTro_chucNangs() {
        return tk_vaiTro_chucNangs;
    }

    public void setTk_vaiTro_chucNangs(Set<TK_VaiTro_ChucNang> tk_vaiTro_chucNangs) {
        this.tk_vaiTro_chucNangs = tk_vaiTro_chucNangs;
    }

    @OneToMany(mappedBy = "tk_vaiTro")
    @JsonIgnore
    public Set<TK_TaiKhoanHeThong_VaiTro> getTk_taiKhoanHeThong_vaiTros() {
        return tk_taiKhoanHeThong_vaiTros;
    }

    public void setTk_taiKhoanHeThong_vaiTros(Set<TK_TaiKhoanHeThong_VaiTro> tk_taiKhoanHeThong_vaiTros) {
        this.tk_taiKhoanHeThong_vaiTros = tk_taiKhoanHeThong_vaiTros;
    }
}
