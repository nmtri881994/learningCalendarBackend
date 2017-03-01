package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/2/2017.
 */

@Entity
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private CollegeClass collegeClass;

    private SystemAccount systemAccount;

    private Set<SubjectClassStudentCanRegister> subjectClassStudentCanRegisters;
    private Set<SubjectClassStudentRegistered> subjectClassStudentRegistereds;

    public Student() {
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
    @JoinColumn(name = "collegeClass_id")
    @JsonIgnore
    public CollegeClass getCollegeClass() {
        return collegeClass;
    }

    public void setCollegeClass(CollegeClass collegeClass) {
        this.collegeClass = collegeClass;
    }

    @OneToOne
    @JoinColumn(name = "systemAccount_id")
    public SystemAccount getSystemAccount() {
        return systemAccount;
    }

    public void setSystemAccount(SystemAccount systemAccount) {
        this.systemAccount = systemAccount;
    }

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    public Set<SubjectClassStudentCanRegister> getSubjectClassStudentCanRegisters() {
        return subjectClassStudentCanRegisters;
    }

    public void setSubjectClassStudentCanRegisters(Set<SubjectClassStudentCanRegister> subjectClassStudentCanRegisters) {
        this.subjectClassStudentCanRegisters = subjectClassStudentCanRegisters;
    }

    @OneToMany(mappedBy = "student")
    public Set<SubjectClassStudentRegistered> getSubjectClassStudentRegistereds() {
        return subjectClassStudentRegistereds;
    }

    public void setSubjectClassStudentRegistereds(Set<SubjectClassStudentRegistered> subjectClassStudentRegistereds) {
        this.subjectClassStudentRegistereds = subjectClassStudentRegistereds;
    }
}
