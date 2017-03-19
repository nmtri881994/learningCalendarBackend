package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TaiKhoanHeThong_VaiTro;
import vn.bkdn.cntt.entity.VaiTro;
import vn.bkdn.cntt.repository.TaiKhoanHeThongRepository;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public TaiKhoanHeThong findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau) {
        return taiKhoanHeThongRepository.findByTenDangNhapAndMatKhau(tenDangNhap, matKhau);
    }

    @Override
    public TaiKhoanHeThong findByTenDangNhap(String tenDangNhap) {
        return taiKhoanHeThongRepository.findByTenDangNhap(tenDangNhap);
    }

    @Override
    public List<VaiTro> getAllVaiTroByTaiKhoanId(int taiKhoanHeThongId) {
        TaiKhoanHeThong taiKhoanHeThong = taiKhoanHeThongRepository.findOne(taiKhoanHeThongId);
        List<TaiKhoanHeThong_VaiTro> taiKhoanHeThong_vaiTros = taiKhoanHeThong_vaiTroService.getTaiKhoanHeThongVaiTrosByTaiKhoan(taiKhoanHeThong);
        List<VaiTro> vaiTros = new ArrayList<VaiTro>();
        for (TaiKhoanHeThong_VaiTro taiKhoanHeThong_vaiTro:
             taiKhoanHeThong_vaiTros) {
            vaiTros.add(taiKhoanHeThong_vaiTro.getVaiTro());
        }
        return vaiTros;
    }

}
