package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMMonHoc;
import vn.bkdn.cntt.repository.MonHocRepository;

/**
 * Created by XuanVinh on 3/28/2017.
 */

@Service
public class MonHocServiceImpl implements MonHocService{

    @Autowired
    private MonHocRepository monHocRepository;

    @Override
    public DMMonHoc findOne(int id) {
        return monHocRepository.findOne(id);
    }
}
