package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/2/2017.
 */

@Entity
public class Course {
    private Long id;
    private Long name;

    private Set<FacultyCourse> facultyCourses;

    public Course() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    public Set<FacultyCourse> getFacultyCourses() {
        return facultyCourses;
    }

    public void setFacultyCourses(Set<FacultyCourse> facultyCourses) {
        this.facultyCourses = facultyCourses;
    }
}
