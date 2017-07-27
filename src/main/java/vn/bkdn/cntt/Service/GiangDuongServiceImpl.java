package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.controller.APIEntity.GiangDuong;
import vn.bkdn.cntt.entity.DMGiangDuong;
import vn.bkdn.cntt.repository.GiangDuongRepository;

import java.util.List;

/**
 * Created by XuanVinh on 4/10/2017.
 */

@Service
public class GiangDuongServiceImpl implements GiangDuongService {

    @Autowired
    private GiangDuongRepository giangDuongRepository;

    @Override
    public DMGiangDuong findOne(int giangDuongId) {
        return giangDuongRepository.findOne(giangDuongId);
    }

    @Override
    public List<DMGiangDuong> findAll() {
        return giangDuongRepository.findAll();
    }

    @Override
    public DMGiangDuong save(DMGiangDuong dmGiangDuong) {
        return giangDuongRepository.save(dmGiangDuong);
    }

    @Override
    public void editGiangDuong(GiangDuong giangDuong) {
        giangDuongRepository.editGiangDuong(giangDuong.getId(), giangDuong.getMaGiangDuong(), giangDuong.getTen(),
                giangDuong.getLoaiPhongId(), giangDuong.getTang(), giangDuong.getSoLuong());
    }

    @Override
    public void deleteGiangDuong(int id) {
        giangDuongRepository.delete(id);
    }
}
