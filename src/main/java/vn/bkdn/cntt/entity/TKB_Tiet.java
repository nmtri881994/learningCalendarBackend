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

    private Set<TKB_LichHocTheoNgay_Tiet> tkb_lichHocTheoNgay_tiets;

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

    @OneToMany(mappedBy = "tkb_tiet")
    @JsonIgnore
    public Set<TKB_LichHocTheoNgay_Tiet> getTkb_lichHocTheoNgay_tiets() {
        return tkb_lichHocTheoNgay_tiets;
    }

    public void setTkb_lichHocTheoNgay_tiets(Set<TKB_LichHocTheoNgay_Tiet> tkb_lichHocTheoNgay_tiets) {
        this.tkb_lichHocTheoNgay_tiets = tkb_lichHocTheoNgay_tiets;
    }
}
