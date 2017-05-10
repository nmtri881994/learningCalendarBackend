package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.LopMonHoc_SinhVien;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by XuanVinh on 4/22/2017.
 */

@Repository
public interface LopMonHoc_SinhVienRepository extends JpaRepository<LopMonHoc_SinhVien, Integer> {
    @Query(value = "select count(id) from lop_mon_hoc_sinh_vien where lop_mon_hoc_id = ?1", nativeQuery = true)
    int getClassCurrentQuantity(int lopMonHocId);

    @Query(value = "select * from lop_mon_hoc_sinh_vien where lop_mon_hoc_id = ?1 and sinh_vien_id = ?2", nativeQuery = true)
    LopMonHoc_SinhVien findByClassIdAndStudentId(int classId, int studentId);

    @Query(value = "select sinh_vien_id from lop_mon_hoc_sinh_vien where lop_mon_hoc_id = ?1", nativeQuery = true)
    List<Integer> findSinhVienByClassId(int classId);
}
