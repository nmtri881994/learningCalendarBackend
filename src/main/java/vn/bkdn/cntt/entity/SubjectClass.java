package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/1/2017.
 */
@Entity
public class SubjectClass {
    private Long id;
    private Subject subject;
    private Teacher teacher;
    private Semester semester;
    private int studentQuantity;

    private Set<SubjectClassStudentCanRegister> subjectClassStudentCanRegisters;
    private Set<SubjectClassStudentRegistered> subjectClassStudentRegistereds;

    public SubjectClass() {
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
    @JoinColumn(name = "subject_id")
    @JsonIgnore
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @ManyToOne
    @JoinColumn(name = "semester_id")
    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getStudentQuantity() {
        return studentQuantity;
    }

    public void setStudentQuantity(int studentQuantity) {
        this.studentQuantity = studentQuantity;
    }

    @OneToMany(mappedBy = "subjectClass")
    public Set<SubjectClassStudentCanRegister> getSubjectClassStudentCanRegisters() {
        return subjectClassStudentCanRegisters;
    }

    public void setSubjectClassStudentCanRegisters(Set<SubjectClassStudentCanRegister> subjectClassStudentCanRegisters) {
        this.subjectClassStudentCanRegisters = subjectClassStudentCanRegisters;
    }

    @OneToMany(mappedBy = "subjectClass")
    @JsonIgnore
    public Set<SubjectClassStudentRegistered> getSubjectClassStudentRegistereds() {
        return subjectClassStudentRegistereds;
    }

    public void setSubjectClassStudentRegistereds(Set<SubjectClassStudentRegistered> subjectClassStudentRegistereds) {
        this.subjectClassStudentRegistereds = subjectClassStudentRegistereds;
    }
}
