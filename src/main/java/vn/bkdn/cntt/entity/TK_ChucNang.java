package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class TK_ChucNang {
    private int id;
    private String tenChucNang;

    private Set<TK_VaiTro_ChucNang> tk_vaiTro_chucNangs;

    public TK_ChucNang() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    @OneToMany(mappedBy = "tk_chucNang")
    @JsonIgnore
    public Set<TK_VaiTro_ChucNang> getTk_vaiTro_chucNangs() {
        return tk_vaiTro_chucNangs;
    }

    public void setTk_vaiTro_chucNangs(Set<TK_VaiTro_ChucNang> tk_vaiTro_chucNangs) {
        this.tk_vaiTro_chucNangs = tk_vaiTro_chucNangs;
    }
}
