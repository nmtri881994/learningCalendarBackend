package vn.bkdn.cntt.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 2/24/2017.
 */

@Entity
public class Teacher {
    private Long id;
    private String name;
    private Faculty faculty;
    private Set<Subject> subjects;
    private Set<TeacherOffSchedule> teacherOffSchedules;

    public Teacher() {
    }

    public Teacher(Long id, String name, Faculty faculty, Set<Subject> subjects, Set<TeacherOffSchedule> teacherOffSchedules) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.subjects = subjects;
        this.teacherOffSchedules = teacherOffSchedules;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "faculty_id")
    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @OneToMany(mappedBy = "teacher")
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @OneToMany(mappedBy = "teacher")
    public Set<TeacherOffSchedule> getTeacherOffSchedules() {
        return teacherOffSchedules;
    }

    public void setTeacherOffSchedules(Set<TeacherOffSchedule> teacherOffSchedules) {
        this.teacherOffSchedules = teacherOffSchedules;
    }
}
