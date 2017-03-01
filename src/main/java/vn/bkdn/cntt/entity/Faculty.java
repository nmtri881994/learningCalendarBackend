package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 2/24/2017.
 */

@Entity
public class Faculty {
    private Long id;
    private String facultyName;

    private Set<Teacher> teachers;
    private Set<FacultyCourse> facultyCourses;

    public Faculty() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @OneToMany(mappedBy = "faculty")
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @OneToMany(mappedBy = "faculty")
    public Set<FacultyCourse> getFacultyCourses() {
        return facultyCourses;
    }

    public void setFacultyCourses(Set<FacultyCourse> facultyCourses) {
        this.facultyCourses = facultyCourses;
    }
}
