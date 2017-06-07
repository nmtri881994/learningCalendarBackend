package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by XuanVinh on 4/19/2017.
 */

@Entity
public class TKB_ThoiGianDangKy {
    private int id;
    private TKB_KiHoc_NamHoc tkb_kiHoc_namHoc;
    private TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc;
    private boolean status;
    private Date startTime;
    private Date endTime;

    public TKB_ThoiGianDangKy() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_kiHoc_namHocId")
    public TKB_KiHoc_NamHoc getTkb_kiHoc_namHoc() {
        return tkb_kiHoc_namHoc;
    }

    public void setTkb_kiHoc_namHoc(TKB_KiHoc_NamHoc tkb_kiHoc_namHoc) {
        this.tkb_kiHoc_namHoc = tkb_kiHoc_namHoc;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_khoa_khoaHocId")
    @JsonIgnore
    public TKB_Khoa_KhoaHoc getTkb_khoa_khoaHoc() {
        return tkb_khoa_khoaHoc;
    }

    public void setTkb_khoa_khoaHoc(TKB_Khoa_KhoaHoc tkb_khoa_khoaHoc) {
        this.tkb_khoa_khoaHoc = tkb_khoa_khoaHoc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
