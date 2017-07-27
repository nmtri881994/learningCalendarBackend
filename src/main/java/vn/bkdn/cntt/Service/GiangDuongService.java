package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.controller.APIEntity.GiangDuong;
import vn.bkdn.cntt.entity.DMGiangDuong;

import java.util.List;

/**
 * Created by XuanVinh on 4/10/2017.
 */
public interface GiangDuongService {
    DMGiangDuong findOne(int giangDuongId);

    List<DMGiangDuong> findAll();

    DMGiangDuong save(DMGiangDuong dmGiangDuong);

    void editGiangDuong(GiangDuong giangDuong);

    void deleteGiangDuong(int id);
}
