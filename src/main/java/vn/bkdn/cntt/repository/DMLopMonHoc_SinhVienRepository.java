package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMLopMonHoc_SinhVien;

import java.util.List;

/**
 * Created by Tri on 7/7/2017.
 */

@Repository
public interface DMLopMonHoc_SinhVienRepository extends JpaRepository<DMLopMonHoc_SinhVien, Integer> {
    @Query(value = "select dm_sinh_vien_id from dmlop_mon_hoc_sinh_vien where dm_lop_mon_hoc_id = ?1", nativeQuery = true)
    List<Integer> getSinhVienIdsOfLopMonHoc(int lopMonHocId);
}
