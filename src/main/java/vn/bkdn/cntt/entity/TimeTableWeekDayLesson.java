package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/1/2017.
 */

@Entity
public class TimeTableWeekDayLesson {
    private Long id;
    private TimeTable timeTable;
    private WeekDayLesson weekDayLesson;

    public TimeTableWeekDayLesson() {
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
    @JoinColumn(name = "timeTable_id")
    @JsonIgnore
    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    @ManyToOne
    @JoinColumn(name = "weekDayLesson_id")
    public WeekDayLesson getWeekDayLesson() {
        return weekDayLesson;
    }

    public void setWeekDayLesson(WeekDayLesson weekDayLesson) {
        this.weekDayLesson = weekDayLesson;
    }
}
