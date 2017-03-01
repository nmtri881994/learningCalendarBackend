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
    private String firstName;
    private String lastName;
    private Faculty faculty;

    private Set<TeacherOffSchedule> teacherOffSchedules;
    private Set<SubjectClass> subjectClasses;

    private SystemAccount systemAccount;

    public Teacher() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public Set<TeacherOffSchedule> getTeacherOffSchedules() {
        return teacherOffSchedules;
    }

    public void setTeacherOffSchedules(Set<TeacherOffSchedule> teacherOffSchedules) {
        this.teacherOffSchedules = teacherOffSchedules;
    }

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    public Set<SubjectClass> getSubjectClasses() {
        return subjectClasses;
    }

    public void setSubjectClasses(Set<SubjectClass> subjectClasses) {
        this.subjectClasses = subjectClasses;
    }

    @OneToOne
    @JoinColumn(name="systemAccount_id")
    public SystemAccount getSystemAccount() {
        return systemAccount;
    }

    public void setSystemAccount(SystemAccount systemAccount) {
        this.systemAccount = systemAccount;
    }
}
