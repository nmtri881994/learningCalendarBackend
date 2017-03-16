package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.VaiTro;
import vn.bkdn.cntt.repository.TaiKhoanHeThongRepository;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tri on 3/15/2017.
 */

@Service
public class TaiKhoanHeThongServiceImpl implements TaiKhoanHeThongService{

    @Autowired
    private TaiKhoanHeThongRepository taiKhoanHeThongRepository;

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
        List<VaiTro> list = this.taiKhoanHeThongRepository.findOne(taiKhoanHeThongId).getTaiKhoanHeThong_vaiTros().stream().map(x -> x.getVaiTro()).collect(Collectors.toList());
        return list;
    }

}
