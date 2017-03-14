package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_LichHocTheoNgay {
    private int id;
    private Date ngay;
    private LopMonHoc lopMonHoc;
    private GiangDuong giangDuong;

    private Set<TKB_ThoiKhoaBieuTrongTuan> tkb_thoiKhoaBieuTrongTuans;

    public TKB_LichHocTheoNgay() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lopMonHocId")
    @NotNull
    public LopMonHoc getLopMonHoc() {
        return lopMonHoc;
    }

    public void setLopMonHoc(LopMonHoc lopMonHoc) {
        this.lopMonHoc = lopMonHoc;
    }

    @OneToMany(mappedBy = "tkb_lichHocTheoNgay")
    public Set<TKB_ThoiKhoaBieuTrongTuan> getTkb_thoiKhoaBieuTrongTuans() {
        return tkb_thoiKhoaBieuTrongTuans;
    }

    public void setTkb_thoiKhoaBieuTrongTuans(Set<TKB_ThoiKhoaBieuTrongTuan> tkb_thoiKhoaBieuTrongTuans) {
        this.tkb_thoiKhoaBieuTrongTuans = tkb_thoiKhoaBieuTrongTuans;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "giangDuongId")
    @NotNull
    public GiangDuong getGiangDuong() {
        return giangDuong;
    }

    public void setGiangDuong(GiangDuong giangDuong) {
        this.giangDuong = giangDuong;
    }
}
