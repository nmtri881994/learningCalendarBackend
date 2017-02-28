package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.Semester;

/**
 * Created by Tri on 2/27/2017.
 */

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long>{
}
