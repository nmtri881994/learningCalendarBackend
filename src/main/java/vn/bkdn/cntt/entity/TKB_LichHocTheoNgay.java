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
    private DMLopMonHoc dmLopMonHoc;
    private DMGiangDuong dmGiangDuong;
    private TKB_Tiet tkb_tietDauTien;
    private TKB_Tiet tkb_tietCuoiCung;
    private boolean thiGiuaKy;
    private boolean thiCuoiKy;
    private String giaoVienNhan;
    private String giaoVienGhiChu;

    private Set<TKB_LichHocTheoNgay_SinhVienGhiChu> tkb_lichHocTheoNgay_sinhVienGhiChus;

    public TKB_LichHocTheoNgay() {
    }

    public TKB_LichHocTheoNgay(Date ngay, TKB_Thu tkb_thu, DMLopMonHoc dmLopMonHoc, DMGiangDuong dmGiangDuong,
                               TKB_Tiet tkb_tietDauTien, TKB_Tiet tkb_tietCuoiCung, boolean thiGiuaKy, boolean thiCuoiKy,
                               String giaoVienNhan, String giaoVienGhiChu) {
        this.ngay = ngay;
        this.tkb_thu = tkb_thu;
        this.dmLopMonHoc = dmLopMonHoc;
        this.dmGiangDuong = dmGiangDuong;
        this.tkb_tietDauTien = tkb_tietDauTien;
        this.tkb_tietCuoiCung = tkb_tietCuoiCung;
        this.thiGiuaKy = thiGiuaKy;
        this.thiCuoiKy = thiCuoiKy;
        this.giaoVienNhan = giaoVienNhan;
        this.giaoVienGhiChu = giaoVienGhiChu;
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
    @JoinColumn(name = "dmLopMonHocId")
    @NotNull
    public DMLopMonHoc getDmLopMonHoc() {
        return dmLopMonHoc;
    }

    public void setDmLopMonHoc(DMLopMonHoc dmLopMonHoc) {
        this.dmLopMonHoc = dmLopMonHoc;
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

    @ManyToOne
    @JoinColumn(name = "tkb_thuId")
    @NotNull
    public TKB_Thu getTkb_thu() {
        return tkb_thu;
    }

    public void setTkb_thu(TKB_Thu tkb_thu) {
        this.tkb_thu = tkb_thu;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_tietDauTienId")
    @NotNull
    public TKB_Tiet getTkb_tietDauTien() {
        return tkb_tietDauTien;
    }

    public void setTkb_tietDauTien(TKB_Tiet tkb_tietDauTien) {
        this.tkb_tietDauTien = tkb_tietDauTien;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_tietCuoiCungId")
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

    @Column(columnDefinition = "nvarchar(500)")
    public String getGiaoVienGhiChu() {
        return giaoVienGhiChu;
    }

    public void setGiaoVienGhiChu(String giaoVienGhiChu) {
        this.giaoVienGhiChu = giaoVienGhiChu;
    }

    @OneToMany(mappedBy = "tkb_lichHocTheoNgay")
    public Set<TKB_LichHocTheoNgay_SinhVienGhiChu> getTkb_lichHocTheoNgay_sinhVienGhiChus() {
        return tkb_lichHocTheoNgay_sinhVienGhiChus;
    }

    public void setTkb_lichHocTheoNgay_sinhVienGhiChus(Set<TKB_LichHocTheoNgay_SinhVienGhiChu> tkb_lichHocTheoNgay_sinhVienGhiChus) {
        this.tkb_lichHocTheoNgay_sinhVienGhiChus = tkb_lichHocTheoNgay_sinhVienGhiChus;
    }
}
