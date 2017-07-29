package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.controller.APIEntity.NhanVien;
import vn.bkdn.cntt.entity.DMNhanVien;
import vn.bkdn.cntt.repository.DMNhanVienRepository;

import java.util.List;

/**
 * Created by Tri on 3/24/2017.
 */

@Service
public class DMNhanVienServiceImpl implements DMNhanVienService {

    @Autowired
    private DMNhanVienRepository nhanVienRepository;


    @Override
    public DMNhanVien findByMaNhanVien(String maNhanVien) {
        return nhanVienRepository.findByMaNhanVien(maNhanVien);
    }

    @Override
    public DMNhanVien findOne(int giaoVienId) {
        return nhanVienRepository.findOne(giaoVienId);
    }

    @Override
    public List<DMNhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public void insertNhanVien(DMNhanVien dmNhanVien) {
        nhanVienRepository.save(dmNhanVien);
    }

    @Override
    public void editNhanVien(NhanVien nhanVien) {
        nhanVienRepository.editNhanVien(nhanVien.getId(), nhanVien.getMaNhanVien(), nhanVien.getHoDem(), nhanVien.getTen(), nhanVien.getDmDonVi().getId());
    }

    @Override
    public void deleteNhanVien(int id) {
        nhanVienRepository.delete(id);
    }
}
