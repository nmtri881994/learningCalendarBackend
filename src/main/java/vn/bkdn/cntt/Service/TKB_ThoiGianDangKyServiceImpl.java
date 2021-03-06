package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_ThoiGianDangKy;
import vn.bkdn.cntt.repository.TKB_ThoiGianDangKyRepository;

import java.util.List;

/**
 * Created by XuanVinh on 4/20/2017.
 */

@Service
public class TKB_ThoiGianDangKyServiceImpl implements TKB_ThoiGianDangKyService {

    @Autowired
    private TKB_ThoiGianDangKyRepository thoiGianDangKyRepository;


    @Override
    public void udpateRegistering(int registerTimeId, boolean status) {
        thoiGianDangKyRepository.updateRegistering(registerTimeId, status);
    }

    @Override
    public TKB_ThoiGianDangKy findOne(int registerTimeId) {
        return thoiGianDangKyRepository.findOne(registerTimeId);
    }

    @Override
    public List<TKB_ThoiGianDangKy> findAll() {
        return thoiGianDangKyRepository.findAll();
    }
}
