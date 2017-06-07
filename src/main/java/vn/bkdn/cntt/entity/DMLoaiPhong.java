package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class DMLoaiPhong {
    private int id;
    private String ten;

    private Set<DMGiangDuong> dmGiangDuongs;

    public DMLoaiPhong() {
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

    @OneToMany(mappedBy = "dmLoaiPhong")
    @JsonIgnore
    public Set<DMGiangDuong> getDmGiangDuongs() {
        return dmGiangDuongs;
    }

    public void setDmGiangDuongs(Set<DMGiangDuong> dmGiangDuongs) {
        this.dmGiangDuongs = dmGiangDuongs;
    }
}
