package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.*;
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
    public void autoArrangeTeacherTimeTableByTeacherId(Long teacherId) {
//        Set<Subject> teacherSubjects = teacherRepository.findOne(teacherId).getSubjects();
//        System.out.println("---Danh sach mon hoc cua giao vien---");
//        for (Subject subject :
//                teacherSubjects) {
//            System.out.println(subject.getName() + "-" + subject.getCapacity());
//        }

        Set<TeacherOffSchedule> teacherOffSchedules = teacherRepository.findOne(teacherId).getTeacherOffSchedules();
        System.out.println("---Lich cong tac cua giao vien---");
        for (TeacherOffSchedule teacherOffSchedule :
                teacherOffSchedules) {
            System.out.println(teacherOffSchedule.getStartDate()+":"+teacherOffSchedule.getEndDate());
        }

        List<Room> rooms = roomRepository.findAll();
        System.out.println("---Danh sach phong---");
        for (Room room :
                rooms) {
            System.out.println(room.getName() + ": " + room.getRoomType().getName());
        }

        Set<UniversityOffSchedule> universityOffSchedules = semesterRepository.findOne(Long.valueOf(1)).getUniversityOffSchedules();
        System.out.println("---Lich nghi truong---");
        for (UniversityOffSchedule universityOffSchedule :
                universityOffSchedules) {
            System.out.println(universityOffSchedule.getStartDate() + "-" + universityOffSchedule.getEndDate());
        }
    }
}
