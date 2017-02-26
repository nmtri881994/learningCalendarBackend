package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.Teacher;

import java.util.List;

/**
 * Created by Tri on 2/25/2017.
 */

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    List<Teacher> findAll();
}
