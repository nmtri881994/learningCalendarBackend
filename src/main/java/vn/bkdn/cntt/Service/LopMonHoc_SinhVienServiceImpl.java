package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMLopMonHoc;
import vn.bkdn.cntt.entity.DMLopMonHoc_SinhVien;
import vn.bkdn.cntt.repository.LopMonHoc_SinhVienRepository;

import java.util.List;

/*
 * Created by XuanVinh on 4/22/2017.
 */

@Service
public class LopMonHoc_SinhVienServiceImpl implements LopMonHoc_SinhVienService {

    @Autowired
    private LopMonHoc_SinhVienRepository DMLopMonHoc_sinhVienRepository;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private SinhVienService sinhVienService;

    @Override
    public int getClassCurrentQuantity(int DMLopMonHocId) {
        return DMLopMonHoc_sinhVienRepository.getClassCurrentQuantity(DMLopMonHocId);
    }

    @Override
    public int studentRegister(int classId, int studentId) {
        DMLopMonHoc DMLopMonHoc = lopMonHocService.findOne(classId);
        try{
            if(DMLopMonHoc_sinhVienRepository.getClassCurrentQuantity(classId) < DMLopMonHoc.getSoLuongToiDa()){
                if(DMLopMonHoc_sinhVienRepository.findByClassIdAndStudentId(classId, studentId) == null){
                    DMLopMonHoc_SinhVien DMLopMonHoc_sinhVien = new DMLopMonHoc_SinhVien();
                    DMLopMonHoc_sinhVien.setDmLopMonHoc(DMLopMonHoc);
                    DMLopMonHoc_sinhVien.setDmSinhVien(sinhVienService.findOne(studentId));
                    DMLopMonHoc_sinhVienRepository.save(DMLopMonHoc_sinhVien);
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
            DMLopMonHoc_SinhVien DMLopMonHoc_sinhVien = DMLopMonHoc_sinhVienRepository.findByClassIdAndStudentId(classId, studentId);
            DMLopMonHoc_sinhVienRepository.delete(DMLopMonHoc_sinhVien.getId());
            return true;
        } catch (Exception e){
            return  false;
        }
    }

    @Override
    public DMLopMonHoc_SinhVien findByClassIdAndStudentId(int classId, int studentId) {
        return DMLopMonHoc_sinhVienRepository.findByClassIdAndStudentId(classId, studentId);
    }

    @Override
    public List<Integer> findSinhVienByClassId(int classId) {
        return DMLopMonHoc_sinhVienRepository.findSinhVienByClassId(classId);
    }
}
