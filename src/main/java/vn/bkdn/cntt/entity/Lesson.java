package vn.bkdn.cntt.entity;

import javax.persistence.*;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class Lesson {
    private Long id;
    private WeekDay weekDay;
    private String name;
    private String time;

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

    @ManyToOne
    @JoinColumn(name = "weekDay_id")
    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
