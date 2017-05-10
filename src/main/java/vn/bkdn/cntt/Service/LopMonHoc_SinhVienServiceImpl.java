package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.LopMonHoc;
import vn.bkdn.cntt.entity.LopMonHoc_SinhVien;
import vn.bkdn.cntt.repository.LopMonHoc_SinhVienRepository;

import java.util.List;

/**
 * Created by XuanVinh on 4/22/2017.
 */

@Service
public class LopMonHoc_SinhVienServiceImpl implements LopMonHoc_SinhVienService {

    @Autowired
    private LopMonHoc_SinhVienRepository lopMonHoc_sinhVienRepository;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private SinhVienService sinhVienService;

    @Override
    public int getClassCurrentQuantity(int lopMonHocId) {
        return lopMonHoc_sinhVienRepository.getClassCurrentQuantity(lopMonHocId);
    }

    @Override
    public int studentRegister(int classId, int studentId) {
        LopMonHoc lopMonHoc = lopMonHocService.findOne(classId);
        try{
            if(lopMonHoc_sinhVienRepository.getClassCurrentQuantity(classId) < lopMonHoc.getSoLuongToiDa()){
                if(lopMonHoc_sinhVienRepository.findByClassIdAndStudentId(classId, studentId) == null){
                    LopMonHoc_SinhVien lopMonHoc_sinhVien = new LopMonHoc_SinhVien();
                    lopMonHoc_sinhVien.setLopMonHoc(lopMonHoc);
                    lopMonHoc_sinhVien.setSinhVien(sinhVienService.findOne(studentId));
                    lopMonHoc_sinhVienRepository.save(lopMonHoc_sinhVien);
                    return 1;
                }else{
                    return 2;
                }
            }else{
                return 3;
            }
        } catch (Exception e){
            return 4;
        }

    }

    @Override
    public boolean studentCancelRegister(int classId, int studentId) {
        try{
            LopMonHoc_SinhVien lopMonHoc_sinhVien = lopMonHoc_sinhVienRepository.findByClassIdAndStudentId(classId, studentId);
            lopMonHoc_sinhVienRepository.delete(lopMonHoc_sinhVien.getId());
            return true;
        } catch (Exception e){
            return  false;
        }
    }

    @Override
    public LopMonHoc_SinhVien findByClassIdAndStudentId(int classId, int studentId) {
        return lopMonHoc_sinhVienRepository.findByClassIdAndStudentId(classId, studentId);
    }

    @Override
    public List<Integer> findSinhVienByClassId(int classId) {
        return lopMonHoc_sinhVienRepository.findSinhVienByClassId(classId);
    }
}
