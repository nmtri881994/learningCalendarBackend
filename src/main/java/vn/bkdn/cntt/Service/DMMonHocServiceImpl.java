package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.controller.APIEntity.MonHoc;
import vn.bkdn.cntt.entity.DMMonHoc;
import vn.bkdn.cntt.repository.DMMonHocRepository;

import java.util.List;

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

    @Override
    public List<DMMonHoc> findAll() {
        return monHocRepository.findAll();
    }

    @Override
    public void insertMonHoc(DMMonHoc dmMonHoc) {
        monHocRepository.save(dmMonHoc);
    }

    @Override
    public void editMonHoc(MonHoc monHoc) {
        monHocRepository.editMonHoc(monHoc.getId(), monHoc.getMaMonHoc(), monHoc.getTen(), monHoc.getSoTinChi());
    }

    @Override
    public void deleteMonHoc(int id) {
        monHocRepository.delete(id);
    }
}
