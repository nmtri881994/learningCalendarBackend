package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 3/14/2017.
 */

@Entity
public class ChucNang {
    private int id;
    private String tenChucNang;

    private Set<VaiTro_ChucNang> vaiTro_chucNangs;

    public ChucNang() {
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

    @OneToMany(mappedBy = "chucNang")
    @JsonIgnore
    public Set<VaiTro_ChucNang> getVaiTro_chucNangs() {
        return vaiTro_chucNangs;
    }

    public void setVaiTro_chucNangs(Set<VaiTro_ChucNang> vaiTro_chucNangs) {
        this.vaiTro_chucNangs = vaiTro_chucNangs;
    }
}
