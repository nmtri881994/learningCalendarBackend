package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/2/2017.
 */

@Entity
public class FacultyCourse {
    private Long id;
    private Faculty faculty;
    private Course course;

    private Set<FacultyCourseSubjectRoadMap> facultyCourseSubjectRoadMaps;
    private Set<CollegeClass> collegeClasses;

    public FacultyCourse() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonIgnore
    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @ManyToOne
    @JoinColumn(name = "course_id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @OneToMany(mappedBy = "facultyCourse")
    public Set<FacultyCourseSubjectRoadMap> getFacultyCourseSubjectRoadMaps() {
        return facultyCourseSubjectRoadMaps;
    }

    public void setFacultyCourseSubjectRoadMaps(Set<FacultyCourseSubjectRoadMap> facultyCourseSubjectRoadMaps) {
        this.facultyCourseSubjectRoadMaps = facultyCourseSubjectRoadMaps;
    }

    @OneToMany(mappedBy = "facultyCourse")
    public Set<CollegeClass> getCollegeClasses() {
        return collegeClasses;
    }

    public void setCollegeClasses(Set<CollegeClass> collegeClasses) {
        this.collegeClasses = collegeClasses;
    }
}
