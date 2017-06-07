package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong_VaiTro;
import vn.bkdn.cntt.entity.TK_VaiTro;
import vn.bkdn.cntt.repository.TaiKhoanHeThongRepository;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tri on 3/15/2017.
 */

@Service
public class TaiKhoanHeThongServiceImpl implements TaiKhoanHeThongService{

    @Autowired
    private TaiKhoanHeThongRepository taiKhoanHeThongRepository;

    @Autowired
    private TaiKhoanHeThong_VaiTroService taiKhoanHeThong_vaiTroService;

    @Override
    public TK_TaiKhoanHeThong findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau) {
        return taiKhoanHeThongRepository.findByTenDangNhapAndMatKhau(tenDangNhap, matKhau);
    }

    @Override
    public TK_TaiKhoanHeThong findByTenDangNhap(String tenDangNhap) {
        return taiKhoanHeThongRepository.findByTenDangNhap(tenDangNhap);
    }

    @Override
    public List<TK_VaiTro> getAllVaiTroByTaiKhoanId(int taiKhoanHeThongId) {
        TK_TaiKhoanHeThong tk_taiKhoanHeThong = taiKhoanHeThongRepository.findOne(taiKhoanHeThongId);
        List<TK_TaiKhoanHeThong_VaiTro> tk_taiKhoanHeThong_vaiTros = taiKhoanHeThong_vaiTroService.getTaiKhoanHeThongVaiTrosByTaiKhoan(tk_taiKhoanHeThong);
        List<TK_VaiTro> tk_vaiTros = new ArrayList<TK_VaiTro>();
        for (TK_TaiKhoanHeThong_VaiTro tk_taiKhoanHeThong_vaiTro :
                tk_taiKhoanHeThong_vaiTros) {
            tk_vaiTros.add(tk_taiKhoanHeThong_vaiTro.getTk_vaiTro());
        }
        return tk_vaiTros;
    }

}
