package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.LopMonHoc_SinhVien;

import java.util.List;

/**
 * Created by XuanVinh on 4/22/2017.
 */
public interface LopMonHoc_SinhVienService {
    int getClassCurrentQuantity(int lopMonHocId);
    int studentRegister(int classId, int studentId);
    boolean studentCancelRegister(int classId, int studentId);
    LopMonHoc_SinhVien findByClassIdAndStudentId(int classId, int studentId);

    List<Integer> findSinhVienByClassId(int classId);
}
