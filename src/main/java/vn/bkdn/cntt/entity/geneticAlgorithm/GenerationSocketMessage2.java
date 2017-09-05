package vn.bkdn.cntt.entity.geneticAlgorithm;

/**
 * Created by XuanVinh on 5/21/2017.
 */
public class GenerationSocketMessage2 {
    private int messType;
    private String khoaKhoaHocNganhNhom;
    private int kyHocId;
    private int namHocId;

    public GenerationSocketMessage2(int messType, String khoaKhoaHocNganhNhom, int kyHocId, int namHocId) {
        this.messType = messType;
        this.khoaKhoaHocNganhNhom = khoaKhoaHocNganhNhom;
        this.kyHocId = kyHocId;
        this.namHocId = namHocId;
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

    public int getMessType() {
        return messType;
    }

    public void setMessType(int messType) {
        this.messType = messType;
    }

    public String getKhoaKhoaHocNganhNhom() {
        return khoaKhoaHocNganhNhom;
    }

    public void setKhoaKhoaHocNganhNhom(String khoaKhoaHocNganhNhom) {
        this.khoaKhoaHocNganhNhom = khoaKhoaHocNganhNhom;
    }
}
