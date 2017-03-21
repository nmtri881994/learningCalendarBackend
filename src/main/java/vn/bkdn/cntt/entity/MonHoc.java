package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class MonHoc {
    private int id;
    private String maMonHoc;
    private String ten;

    private Set<LopMonHoc> lopMonHocs;
    private Set<MonHoc_GiangDuong> monHoc_giangDuongs;

    public MonHoc() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    @Column(columnDefinition = "NVARCHAR(30) NOT NULL")
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @OneToMany(mappedBy = "monHoc")
    @JsonIgnore
    public Set<LopMonHoc> getLopMonHocs() {
        return lopMonHocs;
    }

    public void setLopMonHocs(Set<LopMonHoc> lopMonHocs) {
        this.lopMonHocs = lopMonHocs;
    }

    @OneToMany(mappedBy = "monHoc")
    public Set<MonHoc_GiangDuong> getMonHoc_giangDuongs() {
        return monHoc_giangDuongs;
    }

    public void setMonHoc_giangDuongs(Set<MonHoc_GiangDuong> monHoc_giangDuongs) {
        this.monHoc_giangDuongs = monHoc_giangDuongs;
    }
}