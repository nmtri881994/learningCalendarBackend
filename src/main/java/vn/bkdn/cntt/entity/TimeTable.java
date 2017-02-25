package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class TimeTable {
    private Long id;
    private Subject subject;
    private Room room;
    private Set<Lesson> lessons;
    private String note;
    private Date date;

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

    @ManyToMany
    @JoinTable(name = "timetable_lesson", joinColumns = @JoinColumn(name = "timeTable_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"))
    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
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
}
