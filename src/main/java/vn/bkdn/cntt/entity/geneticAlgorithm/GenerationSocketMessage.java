package vn.bkdn.cntt.entity.geneticAlgorithm;

/**
 * Created by XuanVinh on 5/21/2017.
 */
public class GenerationSocketMessage {
    private int kyHocId;
    private int namHocId;
    private int theHe;
    private int diemThichNghi;

    public GenerationSocketMessage() {
    }

    public GenerationSocketMessage(int kyHocId, int namHocId, int theHe, int diemThichNghi) {
        this.kyHocId = kyHocId;
        this.namHocId = namHocId;
        this.theHe = theHe;
        this.diemThichNghi = diemThichNghi;
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
