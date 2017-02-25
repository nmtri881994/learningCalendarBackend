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
    private Set<UniversityClass> universityClass;
    private Set<Teacher> teachers;

    public Faculty() {
    }

    public Faculty(Long id, String facultyName, Set<UniversityClass> universityClass, Set<Teacher> teachers) {
        this.id = id;
        this.facultyName = facultyName;
        this.universityClass = universityClass;
        this.teachers = teachers;
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
    public Set<UniversityClass> getUniversityClass() {
        return universityClass;
    }

    public void setUniversityClass(Set<UniversityClass> universityClass) {
        this.universityClass = universityClass;
    }

    @OneToMany(mappedBy = "faculty")
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
