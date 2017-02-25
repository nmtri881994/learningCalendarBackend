package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class RoomType {
    private Long id;
    private String name;
    private Set<Subject> subjects;
    private Set<Room> rooms;

    public RoomType() {
    }

    public RoomType(Long id, String name, Set<Subject> subjects, Set<Room> rooms) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.rooms = rooms;
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

    @OneToMany(mappedBy = "roomType")
    @JsonIgnore
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @OneToMany(mappedBy = "roomType")
    @JsonIgnore
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
