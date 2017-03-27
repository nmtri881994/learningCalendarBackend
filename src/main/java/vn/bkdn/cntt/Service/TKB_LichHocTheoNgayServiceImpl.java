package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;
import vn.bkdn.cntt.repository.TKB_LichHocTheoNgayRepository;

/**
 * Created by XuanVinh on 3/27/2017.
 */

@Service
public class TKB_LichHocTheoNgayServiceImpl implements TKB_LichHocTheoNgayService {

    @Autowired
    private TKB_LichHocTheoNgayRepository tkb_lichHocTheoNgayRepository;

    @Override
    public TKB_LichHocTheoNgay findOne(int id) {
        return tkb_lichHocTheoNgayRepository.findOne(id);
    }
}
