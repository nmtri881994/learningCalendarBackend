package vn.bkdn.cntt.entity.geneticAlgorithm;

import vn.bkdn.cntt.entity.LopMonHoc;

import java.util.List;

/**
 * Created by XuanVinh on 5/5/2017.
 */
public class CaThe {
    private int diemThichNghi;
    private List<LopMonHoc> lopMonHocList;

    public CaThe(List<LopMonHoc> lopMonHocList) {
        this.lopMonHocList = lopMonHocList;
    }

    public int getDiemThichNghi() {
        return diemThichNghi;
    }

    public void setDiemThichNghi(int diemThichNghi) {
        this.diemThichNghi = diemThichNghi;
    }

    public List<LopMonHoc> getLopMonHocList() {
        return lopMonHocList;
    }

    public void setLopMonHocList(List<LopMonHoc> lopMonHocList) {
        this.lopMonHocList = lopMonHocList;
    }
}
