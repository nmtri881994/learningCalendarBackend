package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMMonHoc;
import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.entity.TKB_LichHocTheoTuan;

/**
 * Created by Tri on 7/11/2017.
 */
public class ExcelTKB {
    private DMMonHoc dmMonHoc;
    private DMNhanVien dmNhanVien;
    private TKB_LichHocTheoTuan tkb_lichHocTheoTuan;

    public ExcelTKB() {
    }

    public ExcelTKB(DMMonHoc dmMonHoc, DMNhanVien dmNhanVien, TKB_LichHocTheoTuan tkb_lichHocTheoTuan) {
        this.dmMonHoc = dmMonHoc;
        this.dmNhanVien = dmNhanVien;
        this.tkb_lichHocTheoTuan = tkb_lichHocTheoTuan;
    }

    public DMMonHoc getDmMonHoc() {
        return dmMonHoc;
    }

    public void setDmMonHoc(DMMonHoc dmMonHoc) {
        this.dmMonHoc = dmMonHoc;
    }

    public DMNhanVien getDmNhanVien() {
        return dmNhanVien;
    }

    public void setDmNhanVien(DMNhanVien dmNhanVien) {
        this.dmNhanVien = dmNhanVien;
    }

    public TKB_LichHocTheoTuan getTkb_lichHocTheoTuan() {
        return tkb_lichHocTheoTuan;
    }

    public void setTkb_lichHocTheoTuan(TKB_LichHocTheoTuan tkb_lichHocTheoTuan) {
        this.tkb_lichHocTheoTuan = tkb_lichHocTheoTuan;
    }
}
