package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_ThoiKhoaBieuTrongTuan {
    private int id;
    private TKB_LichHocTheoNgay tkb_lichHocTheoNgay;
    private TKB_Tiet_Thu tkb_tiet_thu;

    public TKB_ThoiKhoaBieuTrongTuan() {
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
    @JoinColumn(name = "tkb_LichHocTheoNgayId")
    @NotNull
    @JsonIgnore
    public TKB_LichHocTheoNgay getTkb_lichHocTheoNgay() {
        return tkb_lichHocTheoNgay;
    }

    public void setTkb_lichHocTheoNgay(TKB_LichHocTheoNgay tkb_lichHocTheoNgay) {
        this.tkb_lichHocTheoNgay = tkb_lichHocTheoNgay;
    }

    @ManyToOne
    @JoinColumn(name = "TKB_Tiet_ThuId")
    @NotNull
    public TKB_Tiet_Thu getTkb_tiet_thu() {
        return tkb_tiet_thu;
    }

    public void setTkb_tiet_thu(TKB_Tiet_Thu tkb_tiet_thu) {
        this.tkb_tiet_thu = tkb_tiet_thu;
    }
}
