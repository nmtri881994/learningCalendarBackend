package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 3/9/2017.
 */

@Entity
public class LichHocTheoNgay {
    private int ID;
    private Date Ngay;
    private LopMonHocTinChi lopMonHocTinChi;

    private Set<ThoiKhoaBieuTrongTuan> thoiKhoaBieuTrongTuans;

    public LichHocTheoNgay() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    @ManyToOne
    @JoinColumn(name = "LopMonHocTinChiID")
    @JsonIgnore
    public LopMonHocTinChi getLopMonHocTinChi() {
        return lopMonHocTinChi;
    }

    public void setLopMonHocTinChi(LopMonHocTinChi lopMonHocTinChi) {
        this.lopMonHocTinChi = lopMonHocTinChi;
    }

    @OneToMany(mappedBy = "lichHocTheoNgay")
    public Set<ThoiKhoaBieuTrongTuan> getThoiKhoaBieuTrongTuans() {
        return thoiKhoaBieuTrongTuans;
    }

    public void setThoiKhoaBieuTrongTuans(Set<ThoiKhoaBieuTrongTuan> thoiKhoaBieuTrongTuans) {
        this.thoiKhoaBieuTrongTuans = thoiKhoaBieuTrongTuans;
    }
}
