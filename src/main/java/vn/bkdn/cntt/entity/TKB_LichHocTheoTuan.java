package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by XuanVinh on 4/6/2017.
 */

@Entity
public class TKB_LichHocTheoTuan {
    private int id;
    private LopMonHoc lopMonHoc;
    private TKB_Thu tkb_thu;
    private TKB_Tiet tkb_tietDauTien;
    private TKB_Tiet tkb_tietCuoiCung;
    private GiangDuong giangDuong;

    public TKB_LichHocTheoTuan() {
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
    @JoinColumn(name = "lopMonHocId")
    @JsonIgnore
    public LopMonHoc getLopMonHoc() {
        return lopMonHoc;
    }

    public void setLopMonHoc(LopMonHoc lopMonHoc) {
        this.lopMonHoc = lopMonHoc;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_thuId")
    public TKB_Thu getTkb_thu() {
        return tkb_thu;
    }

    public void setTkb_thu(TKB_Thu tkb_thu) {
        this.tkb_thu = tkb_thu;
    }

    @ManyToOne
    @JoinColumn(name = "tkbTietDauTienId")
    @NotNull
    public TKB_Tiet getTkb_tietDauTien() {
        return tkb_tietDauTien;
    }

    public void setTkb_tietDauTien(TKB_Tiet tkb_tietDauTien) {
        this.tkb_tietDauTien = tkb_tietDauTien;
    }

    @ManyToOne
    @JoinColumn(name = "tkbTietCuoiCungId")
    @NotNull
    public TKB_Tiet getTkb_tietCuoiCung() {
        return tkb_tietCuoiCung;
    }

    public void setTkb_tietCuoiCung(TKB_Tiet tkb_tietCuoiCung) {
        this.tkb_tietCuoiCung = tkb_tietCuoiCung;
    }

    @ManyToOne
    @JoinColumn(name = "giangDuongId")
    public GiangDuong getGiangDuong() {
        return giangDuong;
    }

    public void setGiangDuong(GiangDuong giangDuong) {
        this.giangDuong = giangDuong;
    }
}
