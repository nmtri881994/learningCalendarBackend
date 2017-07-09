package vn.bkdn.cntt.controller.APIEntity;

/**
 * Created by Tri on 7/9/2017.
 */
public class SimpleDiemDanh {
    private int id;
    private int studentId;
    private boolean presented;

    public SimpleDiemDanh(int id, int studentId, boolean presented) {
        this.id = id;
        this.studentId = studentId;
        this.presented = presented;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isPresented() {
        return presented;
    }

    public void setPresented(boolean presented) {
        this.presented = presented;
    }
}
