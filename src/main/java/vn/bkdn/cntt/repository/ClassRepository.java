package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.UniversityClass;

import java.util.List;

/**
 * Created by Tri on 2/8/2017.
 */

@Repository
public interface ClassRepository extends JpaRepository<UniversityClass,Long>{
    List<UniversityClass> findAll();
}
