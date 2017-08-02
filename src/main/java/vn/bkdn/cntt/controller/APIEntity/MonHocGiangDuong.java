package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMGiangDuong;
import vn.bkdn.cntt.entity.DMMonHoc;
import vn.bkdn.cntt.entity.DMMonHoc_GiangDuong;

/**
 * Created by XuanVinh on 8/2/2017.
 */
public class MonHocGiangDuong {
    private int id;
    private MonHoc dmMonHoc;
    private GiangDuong dmGiangDuong;

    public MonHocGiangDuong() {
    }

    public MonHocGiangDuong(int id, MonHoc dmMonHoc, GiangDuong dmGiangDuong) {
        this.id = id;
        this.dmMonHoc = dmMonHoc;
        this.dmGiangDuong = dmGiangDuong;
    }

    public MonHocGiangDuong(DMMonHoc_GiangDuong dmMonHoc_giangDuong) {
        this.id = dmMonHoc_giangDuong.getId();
        this.dmMonHoc = new MonHoc(dmMonHoc_giangDuong.getDmMonHoc());
        this.dmGiangDuong = new GiangDuong(dmMonHoc_giangDuong.getDmGiangDuong());
    }

    public MonHocGiangDuong(MonHocGiangDuong monHocGiangDuong) {
        this.id = monHocGiangDuong.getId();
        this.dmMonHoc = monHocGiangDuong.getDmMonHoc();
        this.dmGiangDuong = monHocGiangDuong.getDmGiangDuong();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MonHoc getDmMonHoc() {
        return dmMonHoc;
    }

    public void setDmMonHoc(MonHoc dmMonHoc) {
        this.dmMonHoc = dmMonHoc;
    }

    public GiangDuong getDmGiangDuong() {
        return dmGiangDuong;
    }

    public void setDmGiangDuong(GiangDuong dmGiangDuong) {
        this.dmGiangDuong = dmGiangDuong;
    }
}
