package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by XuanVinh on 3/20/2017.
 */

@Entity
public class TKB_LichHocTheoNgay_Tiet {
    private int id;
    private TKB_LichHocTheoNgay tkb_lichHocTheoNgay;
    private TKB_Tiet tkb_tiet;

    public TKB_LichHocTheoNgay_Tiet() {
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
    @JoinColumn(name = "tkbLichHocTheoNgayId")
    @JsonIgnore
    @NotNull
    public TKB_LichHocTheoNgay getTkb_lichHocTheoNgay() {
        return tkb_lichHocTheoNgay;
    }

    public void setTkb_lichHocTheoNgay(TKB_LichHocTheoNgay tkb_lichHocTheoNgay) {
        this.tkb_lichHocTheoNgay = tkb_lichHocTheoNgay;
    }

    @ManyToOne
    @JoinColumn(name = "tkbTietId")
    @NotNull
    public TKB_Tiet getTkb_tiet() {
        return tkb_tiet;
    }

    public void setTkb_tiet(TKB_Tiet tkb_tiet) {
        this.tkb_tiet = tkb_tiet;
    }
}
