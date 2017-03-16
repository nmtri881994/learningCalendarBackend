package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class VaiTro {
    private int id;
    private String tenVaiTro;

    private Set<VaiTro_ChucNang> vaiTro_chucNangs;
    private Set<TaiKhoanHeThong_VaiTro> taiKhoanHeThong_vaiTros;

    public VaiTro() {
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

    @OneToMany(mappedBy = "vaiTro")
    public Set<VaiTro_ChucNang> getVaiTro_chucNangs() {
        return vaiTro_chucNangs;
    }

    public void setVaiTro_chucNangs(Set<VaiTro_ChucNang> vaiTro_chucNangs) {
        this.vaiTro_chucNangs = vaiTro_chucNangs;
    }

    @OneToMany(mappedBy = "vaiTro")
    @JsonIgnore
    public Set<TaiKhoanHeThong_VaiTro> getTaiKhoanHeThong_vaiTros() {
        return taiKhoanHeThong_vaiTros;
    }

    public void setTaiKhoanHeThong_vaiTros(Set<TaiKhoanHeThong_VaiTro> taiKhoanHeThong_vaiTros) {
        this.taiKhoanHeThong_vaiTros = taiKhoanHeThong_vaiTros;
    }
}
