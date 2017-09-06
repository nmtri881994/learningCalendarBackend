package vn.bkdn.cntt.controller.APIEntity;

import vn.bkdn.cntt.entity.DMLopMonHoc;

import java.util.List;

/**
 * Created by XuanVinh on 9/5/2017.
 */
public class GenerateCalendarForClassResult {
    private boolean succeed;
    private String mess;
    private List<DMLopMonHoc> dmLopMonHocs;

    public GenerateCalendarForClassResult(boolean succeed, String mess, List<DMLopMonHoc> dmLopMonHocs) {
        this.succeed = succeed;
        this.mess = mess;
        this.dmLopMonHocs = dmLopMonHocs;
    }

    public List<DMLopMonHoc> getDmLopMonHocs() {
        return dmLopMonHocs;
    }

    public void setDmLopMonHocs(List<DMLopMonHoc> dmLopMonHocs) {
        this.dmLopMonHocs = dmLopMonHocs;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
