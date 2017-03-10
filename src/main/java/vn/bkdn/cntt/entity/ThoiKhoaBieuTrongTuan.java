package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class ThoiKhoaBieuTrongTuan {
    private int ID;
    private LichHocTheoNgay lichHocTheoNgay;
    private TietCuaThu tietCuaThu;

    public ThoiKhoaBieuTrongTuan() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @ManyToOne
    @JoinColumn(name = "LichHocTheoNgayID")
    @JsonIgnore
    public LichHocTheoNgay getLichHocTheoNgay() {
        return lichHocTheoNgay;
    }

    public void setLichHocTheoNgay(LichHocTheoNgay lichHocTheoNgay) {
        this.lichHocTheoNgay = lichHocTheoNgay;
    }

    @ManyToOne
    @JoinColumn(name = "TietCuaThuID")
    public TietCuaThu getTietCuaThu() {
        return tietCuaThu;
    }

    public void setTietCuaThu(TietCuaThu tietCuaThu) {
        this.tietCuaThu = tietCuaThu;
    }
}
