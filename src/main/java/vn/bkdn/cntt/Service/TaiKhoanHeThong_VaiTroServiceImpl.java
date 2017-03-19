package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TaiKhoanHeThong;
import vn.bkdn.cntt.entity.TaiKhoanHeThong_VaiTro;
import vn.bkdn.cntt.repository.TaiKhoanHeThong_VaiTroRepository;

import java.util.List;

/**
 * Created by XuanVinh on 3/19/2017.
 */

@Service
public class TaiKhoanHeThong_VaiTroServiceImpl implements TaiKhoanHeThong_VaiTroService {

    @Autowired
    private TaiKhoanHeThong_VaiTroRepository taiKhoanHeThong_vaiTroRepository;

    @Override
    public List<TaiKhoanHeThong_VaiTro> getTaiKhoanHeThongVaiTrosByTaiKhoan(TaiKhoanHeThong taiKhoanHeThong) {
        return taiKhoanHeThong_vaiTroRepository.findByTaiKhoanHeThong(taiKhoanHeThong);
    }
}
