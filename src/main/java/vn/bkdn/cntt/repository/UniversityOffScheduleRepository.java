package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.UniversityOffSchedule;

import java.util.List;

/**
 * Created by Tri on 2/26/2017.
 */

@Repository
public interface UniversityOffScheduleRepository extends JpaRepository<UniversityOffSchedule, Long> {
    List<UniversityOffSchedule> findAll();

    List<UniversityOffSchedule> findBySemesterId(int semesterId);
}
