package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_Tiet {
    private int id;
    private String ten;

    private Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgay_tietDauTiens;
    private Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgay_tietCuoiCungs;

    public TKB_Tiet() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "NVARCHAR(20) NOT NULL")
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @OneToMany(mappedBy = "tkb_tietDauTien")
    @JsonIgnore
    public Set<TKB_LichHocTheoNgay> getTkb_lichHocTheoNgay_tietDauTiens() {
        return tkb_lichHocTheoNgay_tietDauTiens;
    }

    public void setTkb_lichHocTheoNgay_tietDauTiens(Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgay_tietDauTiens) {
        this.tkb_lichHocTheoNgay_tietDauTiens = tkb_lichHocTheoNgay_tietDauTiens;
    }

    @OneToMany(mappedBy = "tkb_tietCuoiCung")
    @JsonIgnore
    public Set<TKB_LichHocTheoNgay> getTkb_lichHocTheoNgay_tietCuoiCungs() {
        return tkb_lichHocTheoNgay_tietCuoiCungs;
    }

    public void setTkb_lichHocTheoNgay_tietCuoiCungs(Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgay_tietCuoiCungs) {
        this.tkb_lichHocTheoNgay_tietCuoiCungs = tkb_lichHocTheoNgay_tietCuoiCungs;
    }
}
