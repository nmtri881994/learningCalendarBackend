package vn.bkdn.cntt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by XuanVinh on 3/6/2017.
 */

@Entity
public class PhanCong {
    private int ID;
    private MonHocCuaLop monHocCuaLop;

    public PhanCong() {
    }

    @Id
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @OneToOne
    @JoinColumn(name = "MonHocLopID")
    public MonHocCuaLop getMonHocCuaLop() {
        return monHocCuaLop;
    }

    public void setMonHocCuaLop(MonHocCuaLop monHocCuaLop) {
        this.monHocCuaLop = monHocCuaLop;
    }
}
