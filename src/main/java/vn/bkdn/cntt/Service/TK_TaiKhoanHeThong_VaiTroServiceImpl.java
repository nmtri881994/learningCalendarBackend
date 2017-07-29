package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong;
import vn.bkdn.cntt.entity.TK_TaiKhoanHeThong_VaiTro;
import vn.bkdn.cntt.repository.TK_TaiKhoanHeThong_VaiTroRepository;

import java.util.List;

/**
 * Created by XuanVinh on 3/19/2017.
 */

@Service
public class TK_TaiKhoanHeThong_VaiTroServiceImpl implements TK_TaiKhoanHeThong_VaiTroService {

    @Autowired
    private TK_TaiKhoanHeThong_VaiTroRepository taiKhoanHeThong_vaiTroRepository;

    @Override
    public List<TK_TaiKhoanHeThong_VaiTro> getTaiKhoanHeThongVaiTrosByTaiKhoan(TK_TaiKhoanHeThong tk_taiKhoanHeThong) {
        return taiKhoanHeThong_vaiTroRepository.findByTK_TaiKhoanHeThong(tk_taiKhoanHeThong.getId());
    }

    @Override
    public List<TK_TaiKhoanHeThong_VaiTro> findAll() {
        return taiKhoanHeThong_vaiTroRepository.findAll();
    }

    @Override
    public void insertTKVT(TK_TaiKhoanHeThong_VaiTro tk_taiKhoanHeThong_vaiTro) {
        taiKhoanHeThong_vaiTroRepository.save(tk_taiKhoanHeThong_vaiTro);
    }

    @Override
    public void deleteTKVT(int id) {
        taiKhoanHeThong_vaiTroRepository.delete(id);
    }
}
