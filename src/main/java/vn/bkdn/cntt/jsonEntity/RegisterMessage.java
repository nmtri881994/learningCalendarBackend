package vn.bkdn.cntt.jsonEntity;

/**
 * Created by XuanVinh on 5/28/2017.
 */
public class RegisterMessage {
    private boolean canRegister;
    private int registerTimeId;

    public RegisterMessage() {
    }

    public RegisterMessage(boolean canRegister, int registerTimeId) {
        this.canRegister = canRegister;
        this.registerTimeId = registerTimeId;
    }

    public boolean isCanRegister() {
        return canRegister;
    }

    public void setCanRegister(boolean canRegister) {
        this.canRegister = canRegister;
    }

    public int getRegisterTimeId() {
        return registerTimeId;
    }

    public void setRegisterTimeId(int registerTimeId) {
        this.registerTimeId = registerTimeId;
    }
}
