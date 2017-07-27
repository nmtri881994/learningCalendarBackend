package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_KhoaHoc;
import vn.bkdn.cntt.repository.TKB_KhoaHocRepository;

import java.util.List;

/**
 * Created by Tri on 7/27/2017.
 */

@Service
public class TKB_KhoaHocServiceImpl implements TKB_KhoaHocService {

    @Autowired
    private TKB_KhoaHocRepository tkb_khoaHocRepository;


    @Override
    public List<TKB_KhoaHoc> findAll() {
        return tkb_khoaHocRepository.findAll();
    }

    @Override
    public void insertKhoaHoc(TKB_KhoaHoc tkb_khoaHoc) {
        tkb_khoaHocRepository.save(tkb_khoaHoc);
    }

    @Override
    public void deleteKhoaHoc(int id) {
        tkb_khoaHocRepository.delete(id);
    }

    @Override
    public void editKhoaHoc(TKB_KhoaHoc tkb_khoaHoc) {
        tkb_khoaHocRepository.editKhoaHoc(tkb_khoaHoc.getId(), tkb_khoaHoc.getNam());
    }
}
