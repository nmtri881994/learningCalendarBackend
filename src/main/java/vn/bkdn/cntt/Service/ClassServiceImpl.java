package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.UniversityClass;
import vn.bkdn.cntt.repository.ClassRepository;

import java.util.List;

/**
 * Created by Tri on 2/8/2017.
 */

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<UniversityClass> getAll() {
        return classRepository.findAll();
    }
}
