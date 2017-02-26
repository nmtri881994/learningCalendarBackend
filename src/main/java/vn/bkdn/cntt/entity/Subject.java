package vn.bkdn.cntt.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class Subject {
    private Long id;
    private String name;
    private Teacher teacher;
    private RoomType roomType;
    private Set<TimeTable> timeTables;
    private Semester semester;
    private float capacity;

    public Subject() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @ManyToOne
    @JoinColumn(name = "roomType_id")
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    public Set<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(Set<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "semester_id")
    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }
}
