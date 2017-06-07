package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMGiangDuong;
import vn.bkdn.cntt.repository.GiangDuongRepository;

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
}
