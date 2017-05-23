package vn.bkdn.cntt.entity.geneticAlgorithm;

/**
 * Created by XuanVinh on 5/23/2017.
 */
public class ChosenCondition {
    private int id;
    private boolean status;
    private int value;

    public ChosenCondition() {
    }

    public ChosenCondition(int id, boolean status, int value) {
        this.id = id;
        this.status = status;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
