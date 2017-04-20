package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by XuanVinh on 4/19/2017.
 */

@Entity
public class RegisterTime {
    private int id;
    private KiHoc_NamHoc kiHoc_namHoc;
    private Khoa_KhoaHoc khoa_khoaHoc;
    private boolean status;
    private Date startTime;
    private Date endTime;

    public RegisterTime() {
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
    @JoinColumn(name = "kiHoc_namHocId")
    public KiHoc_NamHoc getKiHoc_namHoc() {
        return kiHoc_namHoc;
    }

    public void setKiHoc_namHoc(KiHoc_NamHoc kiHoc_namHoc) {
        this.kiHoc_namHoc = kiHoc_namHoc;
    }

    @ManyToOne
    @JoinColumn(name = "khoa_khoaHocId")
    @JsonIgnore
    public Khoa_KhoaHoc getKhoa_khoaHoc() {
        return khoa_khoaHoc;
    }

    public void setKhoa_khoaHoc(Khoa_KhoaHoc khoa_khoaHoc) {
        this.khoa_khoaHoc = khoa_khoaHoc;
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
