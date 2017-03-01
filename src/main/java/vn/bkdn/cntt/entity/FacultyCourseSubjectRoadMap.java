package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/2/2017.
 */

@Entity
public class FacultyCourseSubjectRoadMap {
    private Long id;
    private FacultyCourse facultyCourse;
    private int semesterNumber;
    private Subject subject;

    public FacultyCourseSubjectRoadMap() {
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
    @JoinColumn(name = "facultyCourse_id")
    @JsonIgnore
    public FacultyCourse getFacultyCourse() {
        return facultyCourse;
    }

    public void setFacultyCourse(FacultyCourse facultyCourse) {
        this.facultyCourse = facultyCourse;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
