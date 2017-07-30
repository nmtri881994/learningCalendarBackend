package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMNganh;
import vn.bkdn.cntt.entity.TKB_Khoa_KhoaHoc_Nganh;

/**
 * Created by Tri on 7/30/2017.
 */
public class KhoaKhoaHocNganh {
    private int id;
    private KhoaKhoaHoc khoaKhoaHoc;
    private DMNganh dmNganh;

    public KhoaKhoaHocNganh() {
    }

    public KhoaKhoaHocNganh(int id, KhoaKhoaHoc khoaKhoaHoc, DMNganh dmNganh) {
        this.id = id;
        this.khoaKhoaHoc = khoaKhoaHoc;
        this.dmNganh = dmNganh;
    }

    public KhoaKhoaHocNganh(TKB_Khoa_KhoaHoc_Nganh tkb_khoa_khoaHoc_nganh){
        this.id = tkb_khoa_khoaHoc_nganh.getId();
        this.khoaKhoaHoc = new KhoaKhoaHoc(tkb_khoa_khoaHoc_nganh.getTkb_khoa_khoaHoc());
        this.dmNganh = tkb_khoa_khoaHoc_nganh.getDmNganh();
    }

    public KhoaKhoaHocNganh(KhoaKhoaHocNganh khoaKhoaHocNganho){
        this.id = khoaKhoaHocNganho.getId();
        this.khoaKhoaHoc = khoaKhoaHocNganho.getKhoaKhoaHoc();
        this.dmNganh = khoaKhoaHocNganho.getDmNganh();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KhoaKhoaHoc getKhoaKhoaHoc() {
        return khoaKhoaHoc;
    }

    public void setKhoaKhoaHoc(KhoaKhoaHoc khoaKhoaHoc) {
        this.khoaKhoaHoc = khoaKhoaHoc;
    }

    public DMNganh getDmNganh() {
        return dmNganh;
    }

    public void setDmNganh(DMNganh dmNganh) {
        this.dmNganh = dmNganh;
    }
}
