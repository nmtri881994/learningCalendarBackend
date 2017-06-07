package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMLopMonHoc_SinhVien;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by XuanVinh on 4/22/2017.
 */

@Repository
public interface LopMonHoc_SinhVienRepository extends JpaRepository<DMLopMonHoc_SinhVien, Integer> {
    @Query(value = "select count(id) from dmlop_mon_hoc_sinh_vien where dm_lop_mon_hoc_id = ?1", nativeQuery = true)
    int getClassCurrentQuantity(int DMLopMonHocId);

    @Query(value = "select * from dmlop_mon_hoc_sinh_vien where dm_lop_mon_hoc_id = ?1 and dm_sinh_vien_id = ?2", nativeQuery = true)
    DMLopMonHoc_SinhVien findByClassIdAndStudentId(int classId, int studentId);

    @Query(value = "select dm_sinh_vien_id from dmlop_mon_hoc_sinh_vien where dm_lop_mon_hoc_id = ?1", nativeQuery = true)
    List<Integer> findSinhVienByClassId(int classId);
}
