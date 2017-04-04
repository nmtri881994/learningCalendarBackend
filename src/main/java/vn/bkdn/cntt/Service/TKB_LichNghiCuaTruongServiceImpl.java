package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_LichNghiCuaTruong;
import vn.bkdn.cntt.repository.TKB_LichNghiCuaTruongRepository;

import java.sql.Date;

/**
 * Created by Tri on 4/4/2017.
 */

@Service
public class TKB_LichNghiCuaTruongServiceImpl implements TKB_LichNghiCuaTruongService{

    @Autowired
    private TKB_LichNghiCuaTruongRepository tkb_lichNghiCuaTruongRepository;
    @Override
    public TKB_LichNghiCuaTruong findByNgay(Date ngay) {
        return tkb_lichNghiCuaTruongRepository.findByNgay(ngay);
    }
}
