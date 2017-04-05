package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.Khoa;

/**
 * Created by Tri on 4/5/2017.
 */

@Repository
public interface KhoaRepository extends JpaRepository<Khoa, Integer> {
}
