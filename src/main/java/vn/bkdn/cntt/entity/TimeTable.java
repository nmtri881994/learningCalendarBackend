package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class TimeTable {
    private Long id;
    private Subject subject;
    private Room room;
    private String note;
    private Date date;
    private Semester semester;
    private Set<TimeTableWeekDayLesson> timeTableWeekDayLessons;

    public TimeTable() {
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
    @JoinColumn(name = "subject_id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne
    @JoinColumn(name = "room_id")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @OneToMany(mappedBy = "timeTable")
    public Set<TimeTableWeekDayLesson> getTimeTableWeekDayLessons() {
        return timeTableWeekDayLessons;
    }

    public void setTimeTableWeekDayLessons(Set<TimeTableWeekDayLesson> timeTableWeekDayLessons) {
        this.timeTableWeekDayLessons = timeTableWeekDayLessons;
    }
}
