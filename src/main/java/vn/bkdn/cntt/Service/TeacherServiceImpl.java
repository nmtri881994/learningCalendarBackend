package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.Teacher;
import vn.bkdn.cntt.repository.TeacherRepository;

import java.util.List;

/**
 * Created by Tri on 2/25/2017.
 */

@Service
public class TeacherServiceImpl implements  TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
