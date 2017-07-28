package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_KiHoc;
import vn.bkdn.cntt.repository.TKB_KiHocRepository;

import java.util.List;

/**
 * Created by Tri on 7/28/2017.
 */

@Service
public class TKB_KiHocServiceImpl implements TKB_KiHocService {

    @Autowired
    private TKB_KiHocRepository tkb_kiHocRepository;

    @Override
    public List<TKB_KiHoc> findAll() {
        return tkb_kiHocRepository.findAll();
    }

    @Override
    public TKB_KiHoc findOne(int id) {
        return tkb_kiHocRepository.findOne(id);
    }
}
