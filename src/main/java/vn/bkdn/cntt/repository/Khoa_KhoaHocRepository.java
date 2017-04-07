package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.Khoa_KhoaHoc;

/**
 * Created by XuanVinh on 4/6/2017.
 */

@Repository
public interface Khoa_KhoaHocRepository extends JpaRepository<Khoa_KhoaHoc, Integer> {

    @Query(value = "select * from khoa_khoa_hoc where khoa_id = ?1 and khoa_hoc_id = ?2", nativeQuery = true)
    Khoa_KhoaHoc findByKhoaIdAndKhoaHocId(int khoaId, int khoaHocId);
}
