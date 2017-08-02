package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMMonHoc_GiangDuong;
import vn.bkdn.cntt.repository.DMMonHoc_GiangDuongRepository;

import java.util.List;

/**
 * Created by XuanVinh on 8/2/2017.
 */

@Service
public class DMMonHoc_GiangDuongServiceImpl implements DMMonHoc_GiangDuongService {

    @Autowired
    private DMMonHoc_GiangDuongRepository dmMonHoc_giangDuongRepository;
    @Override
    public DMMonHoc_GiangDuong findOne(int id) {
        return dmMonHoc_giangDuongRepository.findOne(id);
    }

    @Override
    public List<DMMonHoc_GiangDuong> findAll() {
        return dmMonHoc_giangDuongRepository.findAll();
    }

    @Override
    public void insertMHGD(DMMonHoc_GiangDuong dmMonHoc_giangDuong) {
        dmMonHoc_giangDuongRepository.save(dmMonHoc_giangDuong);
    }

    @Override
    public void deleteMHGD(int id) {
        dmMonHoc_giangDuongRepository.delete(id);
    }
}
