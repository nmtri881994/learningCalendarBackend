package vn.bkdn.cntt.entity.geneticAlgorithm;

/**
 * Created by XuanVinh on 5/21/2017.
 */
public class GenerationSocketMessage {
    private int messType;
    private int kyHocId;
    private int namHocId;
    private int theHe;
    private int diemThichNghi;
    private int nhomId;
    private String nhomName;

    public GenerationSocketMessage() {
    }

    public GenerationSocketMessage(int messType, int kyHocId, int namHocId, int theHe, int diemThichNghi, int nhomId, String nhomName) {
        this.messType = messType;
        this.kyHocId = kyHocId;
        this.namHocId = namHocId;
        this.theHe = theHe;
        this.diemThichNghi = diemThichNghi;
        this.nhomId = nhomId;
        this.nhomName = nhomName;
    }

    public int getNhomId() {
        return nhomId;
    }

    public void setNhomId(int nhomId) {
        this.nhomId = nhomId;
    }

    public String getNhomName() {
        return nhomName;
    }

    public void setNhomName(String nhomName) {
        this.nhomName = nhomName;
    }

    public int getMessType() {
        return messType;
    }

    public void setMessType(int messType) {
        this.messType = messType;
    }

    public int getKyHocId() {
        return kyHocId;
    }

    public void setKyHocId(int kyHocId) {
        this.kyHocId = kyHocId;
    }

    public int getNamHocId() {
        return namHocId;
    }

    public void setNamHocId(int namHocId) {
        this.namHocId = namHocId;
    }

    public int getTheHe() {
        return theHe;
    }

    public void setTheHe(int theHe) {
        this.theHe = theHe;
    }

    public int getDiemThichNghi() {
        return diemThichNghi;
    }

    public void setDiemThichNghi(int diemThichNghi) {
        this.diemThichNghi = diemThichNghi;
    }
}
