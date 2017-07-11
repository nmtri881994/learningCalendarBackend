package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.TKB_KiHoc;
import vn.bkdn.cntt.entity.TKB_NamHoc;

/**
 * Created by Tri on 7/11/2017.
 */
public class SemesterYear {
    private TKB_KiHoc tkb_kiHoc;
    private TKB_NamHoc tkb_namHoc;

    public SemesterYear() {
    }

    public SemesterYear(TKB_KiHoc tkb_kiHoc, TKB_NamHoc tkb_namHoc) {
        this.tkb_kiHoc = tkb_kiHoc;
        this.tkb_namHoc = tkb_namHoc;
    }

    public TKB_KiHoc getTkb_kiHoc() {
        return tkb_kiHoc;
    }

    public void setTkb_kiHoc(TKB_KiHoc tkb_kiHoc) {
        this.tkb_kiHoc = tkb_kiHoc;
    }

    public TKB_NamHoc getTkb_namHoc() {
        return tkb_namHoc;
    }

    public void setTkb_namHoc(TKB_NamHoc tkb_namHoc) {
        this.tkb_namHoc = tkb_namHoc;
    }
}
