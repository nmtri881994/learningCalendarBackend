package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/1/2017.
 */

@Entity
public class WeekDayLesson {
    private Long id;
    private WeekDay weekDay;
    private Lesson lesson;
    private Set<TimeTableWeekDayLesson> timeTableWeekDayLessons;

    public WeekDayLesson() {
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
    @JoinColumn(name="weekDay_id")
    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    @ManyToOne
    @JoinColumn(name="lesson_id")
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @OneToMany(mappedBy = "weekDayLesson")
    @JsonIgnore
    public Set<TimeTableWeekDayLesson> getTimeTableWeekDayLessons() {
        return timeTableWeekDayLessons;
    }

    public void setTimeTableWeekDayLessons(Set<TimeTableWeekDayLesson> timeTableWeekDayLessons) {
        this.timeTableWeekDayLessons = timeTableWeekDayLessons;
    }
}
