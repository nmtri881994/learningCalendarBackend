package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.TKB_NamHoc;

import java.sql.Date;

/**
 * Created by Tri on 7/27/2017.
 */
public class NamHoc {
    private int id;
    private String name;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public NamHoc() {
    }

    public NamHoc(int id, String name, Date ngayBatDau, Date ngayKetThuc) {
        this.id = id;
        this.name = name;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public NamHoc(TKB_NamHoc tkb_namHoc){
        this.id = tkb_namHoc.getId();
        this.name = tkb_namHoc.getName();
        this.ngayBatDau = tkb_namHoc.getNgayBatDau();
        this.ngayKetThuc = tkb_namHoc.getNgayKetThuc();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
