package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMNganh;
import vn.bkdn.cntt.repository.DMNganhRepository;

import java.util.List;

/**
 * Created by Tri on 7/30/2017.
 */

@Service
public class DMNganhServiceImpl implements DMNganhService {

    @Autowired
    private DMNganhRepository dmNganhRepository;

    @Override
    public DMNganh findOne(int id) {
        return dmNganhRepository.findOne(id);
    }

    @Override
    public List<DMNganh> findAll() {
        return dmNganhRepository.findAll();
    }

    @Override
    public void insertNganh(DMNganh dmNganh) {
        dmNganhRepository.save(dmNganh);
    }

    @Override
    public void editNganh(DMNganh dmNganh) {
        dmNganhRepository.editNganh(dmNganh.getId(), dmNganh.getTen());
    }

    @Override
    public void deleteNganh(int id) {
        dmNganhRepository.delete(id);
    }
}
