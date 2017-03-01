package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class WeekDay {
    private Long id;
    private String name;
    private Set<WeekDayLesson> weekDayLessons;

    public WeekDay() {
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

    @OneToMany(mappedBy = "weekDay")
    @JsonIgnore
    public Set<WeekDayLesson> getWeekDayLessons() {
        return weekDayLessons;
    }

    public void setWeekDayLessons(Set<WeekDayLesson> weekDayLessons) {
        this.weekDayLessons = weekDayLessons;
    }
}
