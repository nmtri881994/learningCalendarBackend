package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay_DiemDanh;

import javax.transaction.Transactional;

/**
 * Created by Tri on 7/9/2017.
 */

@Repository
public interface TKB_LichHocTheoNgay_DiemDanhRepository extends JpaRepository<TKB_LichHocTheoNgay_DiemDanh, Integer> {

    @Query(value = "select * from tkb_lich_hoc_theo_ngay_diem_danh where tkb_lich_hoc_theo_ngay_id = ?1 and dm_sinh_vien_id = ?2", nativeQuery = true)
    public TKB_LichHocTheoNgay_DiemDanh findByLessonIdAndStudentId(int lessonId, int studentId);

    @Modifying
    @Transactional
    @Query(value = "update tkb_lich_hoc_theo_ngay_diem_danh set presented = ?3 where tkb_lich_hoc_theo_ngay_id = ?1 and dm_sinh_vien_id = ?2", nativeQuery = true)
    public void diemDanh2(int lessonId, int studentId, boolean status);
}
