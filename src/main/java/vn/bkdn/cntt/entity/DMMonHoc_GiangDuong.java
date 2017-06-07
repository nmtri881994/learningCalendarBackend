package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class DMMonHoc_GiangDuong {
    private int id;
    private DMMonHoc dmMonHoc;
    private DMGiangDuong dmGiangDuong;

    public DMMonHoc_GiangDuong() {
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
    @JoinColumn(name = "dmMonHocId")
    @NotNull
    @JsonIgnore
    public DMMonHoc getDmMonHoc() {
        return dmMonHoc;
    }

    public void setDmMonHoc(DMMonHoc dmMonHoc) {
        this.dmMonHoc = dmMonHoc;
    }

    @ManyToOne
    @JoinColumn(name = "dmGiangDuongId")
    @NotNull
    public DMGiangDuong getDmGiangDuong() {
        return dmGiangDuong;
    }

    public void setDmGiangDuong(DMGiangDuong dmGiangDuong) {
        this.dmGiangDuong = dmGiangDuong;
    }
}
