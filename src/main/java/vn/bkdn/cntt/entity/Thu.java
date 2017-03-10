package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class Thu {
    private Long id;
    private String Ten;
    private Set<TietCuaThu> tietCuaThus;

    public Thu() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    @OneToMany(mappedBy = "thu")
    @JsonIgnore
    public Set<TietCuaThu> getTietCuaThus() {
        return tietCuaThus;
    }

    public void setTietCuaThus(Set<TietCuaThu> tietCuaThus) {
        this.tietCuaThus = tietCuaThus;
    }
}
