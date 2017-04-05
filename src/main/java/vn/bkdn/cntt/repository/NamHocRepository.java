package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.NamHoc;

import java.util.List;

/**
 * Created by Tri on 3/21/2017.
 */

@Repository
public interface NamHocRepository extends JpaRepository<NamHoc, Integer> {

    @Query(value = "select * from nam_hoc nh where nh.ngay_ket_thuc > current_date()", nativeQuery = true)
    List<NamHoc> getYearsNotEnd();
}
