package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Tri on 2/17/2017.
 */

@Entity
public class Student {
    private Long id;
    private String studentName;
    private UniversityClass universityClass;

    public Student() {
    }

    public Student(Long id, String studentName, UniversityClass universityClass) {
        this.id = id;
        this.studentName = studentName;
        this.universityClass = universityClass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ManyToOne
    @JoinColumn(name="universityClass_id")
    @JsonIgnore
    public UniversityClass getUniversityClass() {
        return universityClass;
    }

    public void setUniversityClass(UniversityClass universityClass) {
        this.universityClass = universityClass;
    }
}
