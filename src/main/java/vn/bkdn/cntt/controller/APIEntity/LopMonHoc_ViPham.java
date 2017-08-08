package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.TKB_LichHocTheoTuan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XuanVinh on 8/7/2017.
 */
public class LopMonHoc_ViPham {
    private LopMonHoc lopMonHoc;
    private List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans;
    private List<ViPham> viPhams = new ArrayList<>();

    public LopMonHoc_ViPham() {
    }

    public LopMonHoc_ViPham(LopMonHoc lopMonHoc, List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans, List<ViPham> viPhams) {
        this.lopMonHoc = lopMonHoc;
        this.tkb_lichHocTheoTuans = tkb_lichHocTheoTuans;
        this.viPhams = viPhams;
    }

    public List<TKB_LichHocTheoTuan> getTkb_lichHocTheoTuans() {
        return tkb_lichHocTheoTuans;
    }

    public void setTkb_lichHocTheoTuans(List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans) {
        this.tkb_lichHocTheoTuans = tkb_lichHocTheoTuans;
    }

    public LopMonHoc getLopMonHoc() {
        return lopMonHoc;
    }

    public void setLopMonHoc(LopMonHoc lopMonHoc) {
        this.lopMonHoc = lopMonHoc;
    }

    public List<ViPham> getViPhams() {
        return viPhams;
    }

    public void setViPhams(List<ViPham> viPhams) {
        this.viPhams = viPhams;
    }
}
