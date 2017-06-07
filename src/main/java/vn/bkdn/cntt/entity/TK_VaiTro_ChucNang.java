package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class TK_VaiTro_ChucNang {
    private int id;
    private TK_VaiTro tk_vaiTro;
    private TK_ChucNang tk_chucNang;

    public TK_VaiTro_ChucNang() {
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
    @JoinColumn(name = "tk_vaiTroId")
    @NotNull
    @JsonIgnore
    public TK_VaiTro getTk_vaiTro() {
        return tk_vaiTro;
    }

    public void setTk_vaiTro(TK_VaiTro tk_vaiTro) {
        this.tk_vaiTro = tk_vaiTro;
    }

    @ManyToOne
    @JoinColumn(name = "tk_chucNangId")
    @NotNull
    public TK_ChucNang getTk_chucNang() {
        return tk_chucNang;
    }

    public void setTk_chucNang(TK_ChucNang tk_chucNang) {
        this.tk_chucNang = tk_chucNang;
    }
}
