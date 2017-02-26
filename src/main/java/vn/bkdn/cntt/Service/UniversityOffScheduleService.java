package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.UniversityOffSchedule;

import java.util.List;

/**
 * Created by Tri on 2/26/2017.
 */
public interface UniversityOffScheduleService {
    List<UniversityOffSchedule> findAll();

    List<UniversityOffSchedule> findBySemesterId(int semesterId);
}
