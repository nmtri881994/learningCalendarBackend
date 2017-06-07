package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_NamHoc;
import vn.bkdn.cntt.repository.NamHocRepository;

import java.util.List;

/**
 * Created by Tri on 3/21/2017.
 */

@Service
public class NamHocServiceImpl implements NamHocService {

    @Autowired
    private NamHocRepository namHocRepository;

    @Override
    public List<TKB_NamHoc> findAll() {
        return namHocRepository.findAll();
    }

    @Override
    public List<TKB_NamHoc> getYearsNotEnd() {
        return namHocRepository.getYearsNotEnd();
    }

    @Override
    public TKB_NamHoc findOne(int namHocId) {
        return namHocRepository.findOne(namHocId);
    }
}
