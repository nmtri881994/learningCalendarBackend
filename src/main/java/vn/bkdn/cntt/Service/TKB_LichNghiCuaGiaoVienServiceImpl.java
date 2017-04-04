package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.GiaoVien;
import vn.bkdn.cntt.entity.TKB_LichNghiCuaGiaoVien;
import vn.bkdn.cntt.repository.TKB_LichNghiCuaGiaoVienRepository;

import java.sql.Date;

/**
 * Created by Tri on 4/4/2017.
 */

@Service
public class TKB_LichNghiCuaGiaoVienServiceImpl implements TKB_LichNghiCuaGiaoVienService {

    @Autowired
    private TKB_LichNghiCuaGiaoVienRepository tkb_lichNghiCuaGiaoVienRepository;

    @Override
    public TKB_LichNghiCuaGiaoVien findByGiaoVienAndFindNgay(GiaoVien giaoVien, Date ngay) {
        return tkb_lichNghiCuaGiaoVienRepository.findByGiaoVienAndNgay(giaoVien, ngay);
    }
}
