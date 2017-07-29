package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.controller.APIEntity.LopHoc;
import vn.bkdn.cntt.entity.DMLopHoc;
import vn.bkdn.cntt.repository.DMLopHocRepository;

import java.util.List;

/**
 * Created by Tri on 7/29/2017.
 */

@Service
public class DMLopHocServiceImpl implements DMLopHocService {

    @Autowired
    private DMLopHocRepository dmLopHocRepository;

    @Override
    public DMLopHoc findOne(int id) {
        return dmLopHocRepository.findOne(id);
    }

    @Override
    public List<DMLopHoc> findAll() {
        return dmLopHocRepository.findAll();
    }

    @Override
    public void insertLopHoc(DMLopHoc dmLopHoc) {
        dmLopHocRepository.save(dmLopHoc);
    }

    @Override
    public void editLopHoc(LopHoc lopHoc) {
        dmLopHocRepository.editLopHoc(lopHoc.getId(), lopHoc.getMa(), lopHoc.getTen(), lopHoc.getKhoaKhoaHoc().getId());
    }

    @Override
    public void deleteLopHoc(int id) {
        dmLopHocRepository.delete(id);
    }
}
