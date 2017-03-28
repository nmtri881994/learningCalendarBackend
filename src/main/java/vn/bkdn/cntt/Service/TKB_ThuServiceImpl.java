package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_Thu;
import vn.bkdn.cntt.repository.TKB_ThuRepository;

/**
 * Created by Tri on 3/28/2017.
 */

@Service
public class TKB_ThuServiceImpl implements TKB_ThuService {

    @Autowired
    private TKB_ThuRepository tkb_thuRepository;

    @Override
    public TKB_Thu findByTen(String ten) {
        return tkb_thuRepository.findByTen(ten);
    }
}
