package vn.bkdn.cntt.entity;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/2/2017.
 */

@Entity
public class SystemAccount {
    private Long id;
    private String username;
    private String password;
    private AccountType accountType;
    private int status;

    private Teacher teacher;
    private Student student;
    private EducatingStaff educatingStaff;

    public SystemAccount() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "accountType_id")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @OneToOne(mappedBy = "systemAccount")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToOne(mappedBy = "systemAccount")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToOne(mappedBy = "systemAccount")
    public EducatingStaff getEducatingStaff() {
        return educatingStaff;
    }

    public void setEducatingStaff(EducatingStaff educatingStaff) {
        this.educatingStaff = educatingStaff;
    }
}
