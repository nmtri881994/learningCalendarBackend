package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_LichHocTheoNgay {
    private int id;
    private Date ngay;
    private TKB_Thu tkb_thu;
    private LopMonHoc lopMonHoc;
    private GiangDuong giangDuong;
    private TKB_Tiet tkb_tietDauTien;
    private TKB_Tiet tkb_tietCuoiCung;
    private boolean thiGiuaKy;
    private boolean thiCuoiKy;
    private String giaoVienNhan;

    public TKB_LichHocTheoNgay() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lopMonHocId")
    @NotNull
    public LopMonHoc getLopMonHoc() {
        return lopMonHoc;
    }

    public void setLopMonHoc(LopMonHoc lopMonHoc) {
        this.lopMonHoc = lopMonHoc;
    }

    @ManyToOne
    @JoinColumn(name = "giangDuongId")
    @NotNull
    public GiangDuong getGiangDuong() {
        return giangDuong;
    }

    public void setGiangDuong(GiangDuong giangDuong) {
        this.giangDuong = giangDuong;
    }

    @ManyToOne
    @JoinColumn(name = "tkbThuId")
    @NotNull
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

    @NotNull
    @Column(columnDefinition = "bit default 0")
    public boolean isThiGiuaKy() {
        return thiGiuaKy;
    }

    public void setThiGiuaKy(boolean thiGiuaKy) {
        this.thiGiuaKy = thiGiuaKy;
    }

    @NotNull
    @Column(columnDefinition = "bit default 0")
    public boolean isThiCuoiKy() {
        return thiCuoiKy;
    }

    public void setThiCuoiKy(boolean thiCuoiKy) {
        this.thiCuoiKy = thiCuoiKy;
    }

    @Column(columnDefinition = "nvarchar(500)")
    public String getGiaoVienNhan() {
        return giaoVienNhan;
    }

    public void setGiaoVienNhan(String giaoVienNhan) {
        this.giaoVienNhan = giaoVienNhan;
    }
}
