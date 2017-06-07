package vn.bkdn.cntt.entity.geneticAlgorithm;

import vn.bkdn.cntt.entity.DMLopMonHoc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XuanVinh on 5/5/2017.
 */
public class CaThe {
    private int diemThichNghi;
    private List<DMLopMonHoc> DMLopMonHocList;

    public CaThe(List<DMLopMonHoc> DMLopMonHocList) {
        List<DMLopMonHoc> dmLopMonHocs = new ArrayList<>();
        for (DMLopMonHoc DMLopMonHoc:
             DMLopMonHocList) {
            dmLopMonHocs.add(new DMLopMonHoc(DMLopMonHoc));
        }
        this.DMLopMonHocList = dmLopMonHocs;
    }

    public int getDiemThichNghi() {
        return diemThichNghi;
    }

    public void setDiemThichNghi(int diemThichNghi) {
        this.diemThichNghi = diemThichNghi;
    }

    public List<DMLopMonHoc> getDMLopMonHocList() {
        return DMLopMonHocList;
    }

    public void setDMLopMonHocList(List<DMLopMonHoc> DMLopMonHocList) {
        this.DMLopMonHocList = DMLopMonHocList;
    }
}
