package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMLoaiDonVi;
import vn.bkdn.cntt.repository.DMLoaiDonViRepository;

/**
 * Created by Tri on 7/26/2017.
 */

@Service
public class DMLoaiDonViServiceImpl implements DMLoaiDonViService {

    @Autowired
    private DMLoaiDonViRepository dmLoaiDonViRepository;

    @Override
    public DMLoaiDonVi findOne(int id) {
        return dmLoaiDonViRepository.findOne(id);
    }
}
