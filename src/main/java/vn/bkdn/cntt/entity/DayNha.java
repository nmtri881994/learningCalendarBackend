package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class DayNha {
    private int id;
    private String ten;

    private Set<GiangDuong> giangDuongs;

    public DayNha() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "NVARCHAR(30) NOT NULL")
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @OneToMany(mappedBy = "dayNha")
    @JsonIgnore
    public Set<GiangDuong> getGiangDuongs() {
        return giangDuongs;
    }

    public void setGiangDuongs(Set<GiangDuong> giangDuongs) {
        this.giangDuongs = giangDuongs;
    }
}
