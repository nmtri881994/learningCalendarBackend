package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/2/2017.
 */

@Entity
public class CollegeClass {
    private Long id;
    private String name;
    private FacultyCourse facultyCourse;

    private Set<CollegeClassSemester> collegeClassSemesters;
    private Set<Student> students;

    public CollegeClass() {
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
    @JoinColumn(name = "facultyCourse_id")
    @JsonIgnore
    public FacultyCourse getFacultyCourse() {
        return facultyCourse;
    }

    public void setFacultyCourse(FacultyCourse facultyCourse) {
        this.facultyCourse = facultyCourse;
    }

    @OneToMany(mappedBy = "collegeClass")
    public Set<CollegeClassSemester> getCollegeClassSemesters() {
        return collegeClassSemesters;
    }

    public void setCollegeClassSemesters(Set<CollegeClassSemester> collegeClassSemesters) {
        this.collegeClassSemesters = collegeClassSemesters;
    }

    @OneToMany(mappedBy = "collegeClass")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
