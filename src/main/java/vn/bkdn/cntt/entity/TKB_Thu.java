package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@Entity
public class TKB_Thu {
    private int id;
    private String ten;

    private Set<TKB_Tiet_Thu> tkb_tiet_thus;

    public TKB_Thu() {
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

    @OneToMany(mappedBy = "tkb_thu")
    public Set<TKB_Tiet_Thu> getTkb_tiet_thus() {
        return tkb_tiet_thus;
    }

    public void setTkb_tiet_thus(Set<TKB_Tiet_Thu> tkb_tiet_thus) {
        this.tkb_tiet_thus = tkb_tiet_thus;
    }
}
