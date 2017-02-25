package vn.bkdn.cntt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 2/25/2017.
 */

@Entity
public class Building {
    private Long id;
    private String name;
    private String location;
    private Set<Room> rooms;

    public Building() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @OneToMany(mappedBy = "building")
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
