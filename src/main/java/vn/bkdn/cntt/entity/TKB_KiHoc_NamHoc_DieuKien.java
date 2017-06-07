package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 5/15/2017.
 */

@Entity
public class TKB_KiHoc_NamHoc_DieuKien {
    private int id;
    private TKB_KiHoc_NamHoc tkb_kiHoc_namHoc;
    private TKB_DieuKien_TuDong tkb_dieuKien_tuDong;

    public TKB_KiHoc_NamHoc_DieuKien() {
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
    @JsonIgnore
    public TKB_KiHoc_NamHoc getTkb_kiHoc_namHoc() {
        return tkb_kiHoc_namHoc;
    }

    public void setTkb_kiHoc_namHoc(TKB_KiHoc_NamHoc tkb_kiHoc_namHoc) {
        this.tkb_kiHoc_namHoc = tkb_kiHoc_namHoc;
    }

    @ManyToOne
    @JoinColumn(name = "tkb_dieuKien_tuDongId")
    public TKB_DieuKien_TuDong getTkb_dieuKien_tuDong() {
        return tkb_dieuKien_tuDong;
    }

    public void setTkb_dieuKien_tuDong(TKB_DieuKien_TuDong tkb_dieuKien_tuDong) {
        this.tkb_dieuKien_tuDong = tkb_dieuKien_tuDong;
    }
}
