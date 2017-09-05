package vn.bkdn.cntt.controller.APIEntity;

/**
 * Created by XuanVinh on 9/5/2017.
 */
public class GenerateCalendarForClassResult {
    private boolean succeed;
    private String mess;

    public GenerateCalendarForClassResult(boolean succeed, String mess) {
        this.succeed = succeed;
        this.mess = mess;
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
