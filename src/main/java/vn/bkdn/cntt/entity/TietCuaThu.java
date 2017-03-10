package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/1/2017.
 */

@Entity
public class TietCuaThu {
    private Long id;
    private Thu thu;
    private Tiet tiet;

    private Set<ThoiKhoaBieuTrongTuan> thoiKhoaBieuTrongTuans;

    public TietCuaThu() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="thu_id")
    public Thu getThu() {
        return thu;
    }

    public void setThu(Thu thu) {
        this.thu = thu;
    }

    @ManyToOne
    @JoinColumn(name="tiet_id")
    public Tiet getTiet() {
        return tiet;
    }

    public void setTiet(Tiet tiet) {
        this.tiet = tiet;
    }

    @OneToMany(mappedBy = "tietCuaThu")
    @JsonIgnore
    public Set<ThoiKhoaBieuTrongTuan> getThoiKhoaBieuTrongTuans() {
        return thoiKhoaBieuTrongTuans;
    }

    public void setThoiKhoaBieuTrongTuans(Set<ThoiKhoaBieuTrongTuan> thoiKhoaBieuTrongTuans) {
        this.thoiKhoaBieuTrongTuans = thoiKhoaBieuTrongTuans;
    }
}
