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
    private TKB_Thu tkb_thu;
    private LopMonHoc lopMonHoc;
    private GiangDuong giangDuong;

    private Set<TKB_LichHocTheoNgay_Tiet> tkb_lichHocTheoNgay_tiets;

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

    @ManyToOne
    @JoinColumn(name = "giangDuongId")
    @NotNull
    public GiangDuong getGiangDuong() {
        return giangDuong;
    }

    public void setGiangDuong(GiangDuong giangDuong) {
        this.giangDuong = giangDuong;
    }

    @ManyToOne
    @JoinColumn(name = "tkbThuId")
    @NotNull
    public TKB_Thu getTkb_thu() {
        return tkb_thu;
    }

    public void setTkb_thu(TKB_Thu tkb_thu) {
        this.tkb_thu = tkb_thu;
    }

    @OneToMany(mappedBy = "tkb_lichHocTheoNgay")
    public Set<TKB_LichHocTheoNgay_Tiet> getTkb_lichHocTheoNgay_tiets() {
        return tkb_lichHocTheoNgay_tiets;
    }

    public void setTkb_lichHocTheoNgay_tiets(Set<TKB_LichHocTheoNgay_Tiet> tkb_lichHocTheoNgay_tiets) {
        this.tkb_lichHocTheoNgay_tiets = tkb_lichHocTheoNgay_tiets;
    }
}
