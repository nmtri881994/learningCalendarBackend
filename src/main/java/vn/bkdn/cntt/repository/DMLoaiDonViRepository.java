package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.DMLoaiDonVi;

/**
 * Created by Tri on 7/26/2017.
 */

@Repository
public interface DMLoaiDonViRepository extends JpaRepository<DMLoaiDonVi, Integer> {
}
