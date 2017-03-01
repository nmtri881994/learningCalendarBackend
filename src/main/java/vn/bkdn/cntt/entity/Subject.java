package vn.bkdn.cntt.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class Subject {
    private String id;
    private String name;
    private SubjectType subjectType;
    private int theoryLessons;
    private int theoryLessonsPerWeek;
    private int practiceLessons;
    private int practiceLessonsPerWeek;
    private RoomType practiceRoomType;

    private Set<SubjectClass> subjectClasses;
    private Set<FacultyCourseSubjectRoadMap> facultyCourseSubjectRoadMaps;

    public Subject() {
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "subjectType_id")
    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public int getTheoryLessons() {
        return theoryLessons;
    }

    public void setTheoryLessons(int theoryLessons) {
        this.theoryLessons = theoryLessons;
    }

    public int getTheoryLessonsPerWeek() {
        return theoryLessonsPerWeek;
    }

    public void setTheoryLessonsPerWeek(int theoryLessonsPerWeek) {
        this.theoryLessonsPerWeek = theoryLessonsPerWeek;
    }

    public int getPracticeLessons() {
        return practiceLessons;
    }

    public void setPracticeLessons(int practiceLessons) {
        this.practiceLessons = practiceLessons;
    }

    public int getPracticeLessonsPerWeek() {
        return practiceLessonsPerWeek;
    }

    public void setPracticeLessonsPerWeek(int practiceLessonsPerWeek) {
        this.practiceLessonsPerWeek = practiceLessonsPerWeek;
    }

    @ManyToOne
    @JoinColumn(name = "practiceRoomType_id")
    public RoomType getPracticeRoomType() {
        return practiceRoomType;
    }

    public void setPracticeRoomType(RoomType practiceRoomType) {
        this.practiceRoomType = practiceRoomType;
    }

    @OneToMany(mappedBy = "subject")
    public Set<SubjectClass> getSubjectClasses() {
        return subjectClasses;
    }

    public void setSubjectClasses(Set<SubjectClass> subjectClasses) {
        this.subjectClasses = subjectClasses;
    }

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    public Set<FacultyCourseSubjectRoadMap> getFacultyCourseSubjectRoadMaps() {
        return facultyCourseSubjectRoadMaps;
    }

    public void setFacultyCourseSubjectRoadMaps(Set<FacultyCourseSubjectRoadMap> facultyCourseSubjectRoadMaps) {
        this.facultyCourseSubjectRoadMaps = facultyCourseSubjectRoadMaps;
    }
}
