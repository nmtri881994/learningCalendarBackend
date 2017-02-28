package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.Room;
import vn.bkdn.cntt.entity.Teacher;
import vn.bkdn.cntt.entity.UniversityOffSchedule;
import vn.bkdn.cntt.repository.RoomRepository;
import vn.bkdn.cntt.repository.SemesterRepository;
import vn.bkdn.cntt.repository.TeacherRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Tri on 2/25/2017.
 */

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void autoArrangeTeacherTimeTableByTeacherId(int teacherId) {
        List<Teacher> teachers = teacherRepository.findAll();
        System.out.println("---Danh sach giao vien---");
        for (Teacher teacher :
                teachers) {
            System.out.println(teacher.getName());
        }
        List<Room> rooms = roomRepository.findAll();
        System.out.println("---Danh sach phong---");
        for (Room room :
                rooms) {
            System.out.println(room.getName() + ": " + room.getRoomType().getName());
        }

        Set<UniversityOffSchedule> universityOffSchedules = semesterRepository.findOne(Long.valueOf(1)).getUniversityOffSchedules();
        System.out.println("---Danh sach phong---");
        for (UniversityOffSchedule universityOffSchedule :
                universityOffSchedules) {
            System.out.println(universityOffSchedule.getStartDate() + "-" + universityOffSchedule.getEndDate());
        }
    }
}
