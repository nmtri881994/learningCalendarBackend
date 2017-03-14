package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class VaiTro_ChucNang {
    private int id;
    private VaiTro vaiTro;
    private ChucNang chucNang;

    public VaiTro_ChucNang() {
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
    @JoinColumn(name = "vaiTroId")
    @NotNull
    @JsonIgnore
    public VaiTro getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }

    @ManyToOne
    @JoinColumn(name = "chucNangId")
    @NotNull
    public ChucNang getChucNang() {
        return chucNang;
    }

    public void setChucNang(ChucNang chucNang) {
        this.chucNang = chucNang;
    }
}
