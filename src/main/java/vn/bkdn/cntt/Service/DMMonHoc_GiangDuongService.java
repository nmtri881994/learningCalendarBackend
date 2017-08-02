package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMMonHoc_GiangDuong;

import java.util.List;

/**
 * Created by XuanVinh on 8/2/2017.
 */
public interface DMMonHoc_GiangDuongService {
    DMMonHoc_GiangDuong findOne(int id);
    List<DMMonHoc_GiangDuong> findAll();
    void insertMHGD(DMMonHoc_GiangDuong dmMonHoc_giangDuong);
    void deleteMHGD(int id);
}
