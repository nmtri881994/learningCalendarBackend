package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMMonHoc;
import vn.bkdn.cntt.repository.DMMonHocRepository;

/**
 * Created by XuanVinh on 3/28/2017.
 */

@Service
public class DMMonHocServiceImpl implements DMMonHocService {

    @Autowired
    private DMMonHocRepository monHocRepository;

    @Override
    public DMMonHoc findOne(int id) {
        return monHocRepository.findOne(id);
    }
}
