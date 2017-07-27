package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMGiangDuong;

import javax.transaction.Transactional;

/**
 * Created by XuanVinh on 4/10/2017.
 */

@Repository
public interface DMGiangDuongRepository extends JpaRepository<DMGiangDuong, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update dmgiang_duong set ma_giang_duong = ?2, ten = ?3, dm_loai_phong_id= ?4, tang =?5, so_luong=?6 where id = ?1", nativeQuery = true)
    void editGiangDuong(int id, String maGiangDuong, String ten, int loaiPhongId, int tang, int soLuong);
}
