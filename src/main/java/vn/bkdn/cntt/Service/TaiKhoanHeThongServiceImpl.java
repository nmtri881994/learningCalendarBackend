package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.Repository.TaiKhoanHeThongRepository;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;

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
}
