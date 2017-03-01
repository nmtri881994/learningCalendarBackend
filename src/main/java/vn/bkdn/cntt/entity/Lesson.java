package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class Lesson {
    private Long id;
    private String name;
    private int startTime;
    private int endTime;
    private Set<WeekDayLesson> weekDayLessons;

    public Lesson() {
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

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @OneToMany(mappedBy = "lesson")
    @JsonIgnore
    public Set<WeekDayLesson> getWeekDayLessons() {
        return weekDayLessons;
    }

    public void setWeekDayLessons(Set<WeekDayLesson> weekDayLessons) {
        this.weekDayLessons = weekDayLessons;
    }
}
