package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.controller.APIEntity.NamHoc;
import vn.bkdn.cntt.entity.TKB_KhoaHoc;
import vn.bkdn.cntt.entity.TKB_NamHoc;
import vn.bkdn.cntt.repository.TKB_NamHocRepository;

import java.util.List;

/**
 * Created by Tri on 7/27/2017.
 */

@Service
public class TKB_NamHocServiceImpl implements TKB_NamHocService {

    @Autowired
    private TKB_NamHocRepository tkb_namHocRepository;

    @Override
    public List<TKB_NamHoc> findAll() {
        return tkb_namHocRepository.findAll();
    }

    @Override
    public void insertNamHoc(NamHoc namHoc) {
        tkb_namHocRepository.save(new TKB_NamHoc(namHoc.getId(), namHoc.getName(), namHoc.getNgayBatDau(), namHoc.getNgayKetThuc()));
    }

    @Override
    public void deleteNamHoc(int id) {
        tkb_namHocRepository.delete(id);
    }

    @Override
    public void editNamHoc(NamHoc namHoc) {
        tkb_namHocRepository.editNamHoc(namHoc.getId(), namHoc.getName(), namHoc.getNgayBatDau(), namHoc.getNgayKetThuc());
    }

    @Override
    public TKB_NamHoc findOne(int id) {
        return tkb_namHocRepository.findOne(id);
    }
}
