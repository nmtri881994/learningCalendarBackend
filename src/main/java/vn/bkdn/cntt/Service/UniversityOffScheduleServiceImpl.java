package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import vn.bkdn.cntt.entity.UniversityOffSchedule;
import vn.bkdn.cntt.repository.UniversityOffScheduleRepository;

import java.util.List;

/**
 * Created by Tri on 2/26/2017.
 */
public class UniversityOffScheduleServiceImpl implements UniversityOffScheduleService{

    @Autowired
    private UniversityOffScheduleRepository universityOffScheduleRepository;

    @Override
    public List<UniversityOffSchedule> findAll() {
        return universityOffScheduleRepository.findAll();
    }

    @Override
    public List<UniversityOffSchedule> findBySemesterId(int semesterId) {
        return universityOffScheduleRepository.findBySemesterId(semesterId);
    }
}
