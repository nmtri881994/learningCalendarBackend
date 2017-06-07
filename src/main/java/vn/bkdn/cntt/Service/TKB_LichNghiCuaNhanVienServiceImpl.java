package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.entity.TKB_LichNghiCuaNhanVien;
import vn.bkdn.cntt.repository.TKB_LichNghiCuaNhanVienRepository;

import java.sql.Date;

/**
 * Created by Tri on 4/4/2017.
 */

@Service
public class TKB_LichNghiCuaNhanVienServiceImpl implements TKB_LichNghiCuaNhanVienService {

    @Autowired
    private TKB_LichNghiCuaNhanVienRepository tkb_lichNghiCuaNhanVienRepository;

    @Override
    public TKB_LichNghiCuaNhanVien findByGiaoVienAndFindNgay(DMNhanVien nhanVien, Date ngay) {
        return tkb_lichNghiCuaNhanVienRepository.findByDmNhanVienAndNgay(nhanVien, ngay);
    }
}
