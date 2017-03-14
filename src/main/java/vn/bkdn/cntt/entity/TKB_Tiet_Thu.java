package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_Tiet_Thu {
    private int id;
    private TKB_Tiet tkb_tiet;
    private TKB_Thu tkb_thu;

    private Set<TKB_ThoiKhoaBieuTrongTuan> tkb_thoiKhoaBieuTrongTuans;

    public TKB_Tiet_Thu() {
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
    @JoinColumn(name = "TKB_TietId")
    @NotNull
    @JsonIgnore
    public TKB_Tiet getTkb_tiet() {
        return tkb_tiet;
    }

    public void setTkb_tiet(TKB_Tiet tkb_tiet) {
        this.tkb_tiet = tkb_tiet;
    }

    @ManyToOne
    @JoinColumn(name = "TKB_ThuId")
    @NotNull
    @JsonIgnore
    public TKB_Thu getTkb_thu() {
        return tkb_thu;
    }

    public void setTkb_thu(TKB_Thu tkb_thu) {
        this.tkb_thu = tkb_thu;
    }

    @OneToMany(mappedBy = "tkb_tiet_thu")
    public Set<TKB_ThoiKhoaBieuTrongTuan> getTkb_thoiKhoaBieuTrongTuans() {
        return tkb_thoiKhoaBieuTrongTuans;
    }

    public void setTkb_thoiKhoaBieuTrongTuans(Set<TKB_ThoiKhoaBieuTrongTuan> tkb_thoiKhoaBieuTrongTuans) {
        this.tkb_thoiKhoaBieuTrongTuans = tkb_thoiKhoaBieuTrongTuans;
    }
}
