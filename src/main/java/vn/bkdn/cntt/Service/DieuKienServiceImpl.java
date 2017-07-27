package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_DieuKien_TuDong;
import vn.bkdn.cntt.repository.TKB_DieuKien_TuDongRepository;

import java.util.List;

/**
 * Created by XuanVinh on 5/15/2017.
 */

@Service
public class DieuKienServiceImpl implements DieuKienService {

    @Autowired
    TKB_DieuKien_TuDongRepository dieuKienRepository;

    @Override
    public List<TKB_DieuKien_TuDong> findAll() {
        return dieuKienRepository.findAll();
    }
}
