package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class MonHoc_GiangDuong {
    private int id;
    private MonHoc monHoc;
    private GiangDuong giangDuong;

    public MonHoc_GiangDuong() {
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
    @JoinColumn(name = "monHocId")
    @JsonIgnore
    @NotNull
    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    @ManyToOne
    @JoinColumn(name = "giangDuongId")
    @JsonIgnore
    @NotNull
    public GiangDuong getGiangDuong() {
        return giangDuong;
    }

    public void setGiangDuong(GiangDuong giangDuong) {
        this.giangDuong = giangDuong;
    }
}
