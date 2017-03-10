package vn.bkdn.cntt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class DTDanhMucMonHoc {
    private int MonHocID;
    private String MaMonHoc;
    private String TenMonHoc;

    private int SoBuoiLyThuyet;
    private int SoTietLyThuyet;
    private int SoBuoiThucHanh;
    private int SoTietThucHanh;

    public DTDanhMucMonHoc() {
    }

    @Id
    public int getMonHocID() {
        return MonHocID;
    }

    public void setMonHocID(int monHocID) {
        MonHocID = monHocID;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        MaMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        TenMonHoc = tenMonHoc;
    }

    public int getSoBuoiLyThuyet() {
        return SoBuoiLyThuyet;
    }

    public void setSoBuoiLyThuyet(int soBuoiLyThuyet) {
        SoBuoiLyThuyet = soBuoiLyThuyet;
    }

    public int getSoTietLyThuyet() {
        return SoTietLyThuyet;
    }

    public void setSoTietLyThuyet(int soTietLyThuyet) {
        SoTietLyThuyet = soTietLyThuyet;
    }

    public int getSoBuoiThucHanh() {
        return SoBuoiThucHanh;
    }

    public void setSoBuoiThucHanh(int soBuoiThucHanh) {
        SoBuoiThucHanh = soBuoiThucHanh;
    }

    public int getSoTietThucHanh() {
        return SoTietThucHanh;
    }

    public void setSoTietThucHanh(int soTietThucHanh) {
        SoTietThucHanh = soTietThucHanh;
    }
}
