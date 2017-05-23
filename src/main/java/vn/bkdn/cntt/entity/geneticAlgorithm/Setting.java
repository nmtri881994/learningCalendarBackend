package vn.bkdn.cntt.entity.geneticAlgorithm;

import vn.bkdn.cntt.entity.DieuKien;

import java.util.List;

/**
 * Created by XuanVinh on 5/12/2017.
 */
public class Setting {
    private int namHocId;
    private int kyHocId;

    private List<ChosenCondition> chosenConditions;

    private int soTheHe;
    private int diemThichNghiToiUu;

    public Setting(int namHocId, int kyHocId, List<ChosenCondition> chosenConditions, int soTheHe, int diemThichNghiToiUu) {
        this.namHocId = namHocId;
        this.kyHocId = kyHocId;
        this.chosenConditions = chosenConditions;
        this.soTheHe = soTheHe;
        this.diemThichNghiToiUu = diemThichNghiToiUu;
    }

    public Setting() {
    }

    public int getNamHocId() {
        return namHocId;
    }

    public void setNamHocId(int namHocId) {
        this.namHocId = namHocId;
    }

    public int getKyHocId() {
        return kyHocId;
    }

    public void setKyHocId(int kyHocId) {
        this.kyHocId = kyHocId;
    }

    public List<ChosenCondition> getChosenConditions() {
        return chosenConditions;
    }

    public void setChosenConditions(List<ChosenCondition> chosenConditions) {
        this.chosenConditions = chosenConditions;
    }

    public int getSoTheHe() {
        return soTheHe;
    }

    public void setSoTheHe(int soTheHe) {
        this.soTheHe = soTheHe;
    }

    public int getDiemThichNghiToiUu() {
        return diemThichNghiToiUu;
    }

    public void setDiemThichNghiToiUu(int diemThichNghiToiUu) {
        this.diemThichNghiToiUu = diemThichNghiToiUu;
    }
}
