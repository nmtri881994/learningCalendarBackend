package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.DMLoaiPhong;

import java.util.List;

/**
 * Created by XuanVinh on 4/9/2017.
 */
public interface DMDayNhaService {
    List<DMLoaiPhong> findAll();
    DMLoaiPhong findOne(int id);
}
