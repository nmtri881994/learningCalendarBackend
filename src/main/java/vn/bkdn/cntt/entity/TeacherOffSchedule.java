package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class TeacherOffSchedule {
    private Long id;
    private Teacher teacher;
    private Date startDate;
    private Date endDate;

    public TeacherOffSchedule() {
    }

    public TeacherOffSchedule(Long id, Teacher teacher, Date startDate, Date endDate) {
        this.id = id;
        this.teacher = teacher;
        this.startDate = startDate;
        this.endDate = endDate;
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
    @JsonIgnore
    @JoinColumn(name = "teacher_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
