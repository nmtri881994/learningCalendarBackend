package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_KiHoc_NamHoc_DieuKien;
import vn.bkdn.cntt.repository.TKB_KiHoc_NamHoc_DieuKienRepository;

/**
 * Created by XuanVinh on 5/23/2017.
 */

@Service
public class KiHoc_NamHoc_DieuKienServiceImpl implements KiHoc_NamHoc_DieuKienService {

    @Autowired
    private TKB_KiHoc_NamHoc_DieuKienRepository kiHoc_namHoc_dieuKienRepository;

    @Override
    public TKB_KiHoc_NamHoc_DieuKien findOne(int id) {
        return kiHoc_namHoc_dieuKienRepository.findOne(id);
    }
}
