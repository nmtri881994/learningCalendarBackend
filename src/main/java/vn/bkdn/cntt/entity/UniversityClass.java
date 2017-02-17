package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tri on 2/8/2017.
 */

@Entity
public class UniversityClass {
    private Long id;
    private String className;
    private Set<Student> students;

    public UniversityClass() {
    }

    public UniversityClass(Long id, String className, Set<Student> students) {
        this.id = id;
        this.className = className;
        this.students = students;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @OneToMany(mappedBy = "universityClass")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
