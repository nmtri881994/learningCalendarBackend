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
    private DMLopMonHoc dmLopMonHoc;
    private TKB_Thu tkb_thu;
    private int soTiet;
    private TKB_Tiet tkb_tietDauTien;
    private TKB_Tiet tkb_tietCuoiCung;
    private DMGiangDuong dmGiangDuong;
    private int tuanBatDau;
    private int tuanKetThuc;

    public TKB_LichHocTheoTuan() {
    }

    public TKB_LichHocTheoTuan(TKB_LichHocTheoTuan tkb_lichHocTheoTuan) {
        this.id = tkb_lichHocTheoTuan.getId();
        this.dmLopMonHoc = tkb_lichHocTheoTuan.getDmLopMonHoc();
        this.tkb_thu = tkb_lichHocTheoTuan.getTkb_thu();
        this.soTiet = tkb_lichHocTheoTuan.getSoTiet();
        this.tkb_tietDauTien = tkb_lichHocTheoTuan.getTkb_tietDauTien();
        this.tkb_tietCuoiCung = tkb_lichHocTheoTuan.getTkb_tietCuoiCung();
        this.dmGiangDuong = tkb_lichHocTheoTuan.getDmGiangDuong();
        this.tuanBatDau = tkb_lichHocTheoTuan.getTuanBatDau();
        this.tuanKetThuc = tkb_lichHocTheoTuan.getTuanKetThuc();
    }

    public TKB_LichHocTheoTuan(int soTiet) {
        this.soTiet = soTiet;
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
    @JoinColumn(name = "dmLopMonHocId")
    @JsonIgnore
    public DMLopMonHoc getDmLopMonHoc() {
        return dmLopMonHoc;
    }

    public void setDmLopMonHoc(DMLopMonHoc dmLopMonHoc) {
        this.dmLopMonHoc = dmLopMonHoc;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_thuId")
    public TKB_Thu getTkb_thu() {
        return tkb_thu;
    }

    public void setTkb_thu(TKB_Thu tkb_thu) {
        this.tkb_thu = tkb_thu;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
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

    @ManyToOne
    @JoinColumn(name = "dmGiangDuongId")
    public DMGiangDuong getDmGiangDuong() {
        return dmGiangDuong;
    }

    public void setDmGiangDuong(DMGiangDuong dmGiangDuong) {
        this.dmGiangDuong = dmGiangDuong;
    }

    public int getTuanBatDau() {
        return tuanBatDau;
    }

    public void setTuanBatDau(int tuanBatDau) {
        this.tuanBatDau = tuanBatDau;
    }

    public int getTuanKetThuc() {
        return tuanKetThuc;
    }

    public void setTuanKetThuc(int tuanKetThuc) {
        this.tuanKetThuc = tuanKetThuc;
    }
}
