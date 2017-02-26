package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Tri on 2/25/2017.
 */

@Entity
public class Semester {
    private Long id;
    private Date startTime;
    private Date endTime;
    private int number;
    private Set<UniversityOffSchedule> universityOffSchedules;
    private Set<TeacherOffSchedule> teacherOffSchedules;
    private Set<Subject> subjects;
    private Set<TimeTable> timeTables;

    public Semester() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @OneToMany(mappedBy = "semester")
    public Set<UniversityOffSchedule> getUniversityOffSchedules() {
        return universityOffSchedules;
    }

    public void setUniversityOffSchedules(Set<UniversityOffSchedule> universityOffSchedules) {
        this.universityOffSchedules = universityOffSchedules;
    }

    @OneToMany(mappedBy = "semester")
    public Set<TeacherOffSchedule> getTeacherOffSchedules() {
        return teacherOffSchedules;
    }

    public void setTeacherOffSchedules(Set<TeacherOffSchedule> teacherOffSchedules) {
        this.teacherOffSchedules = teacherOffSchedules;
    }

    @OneToMany(mappedBy = "semester")
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @OneToMany(mappedBy = "semester")
    public Set<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(Set<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }
}