package vn.bkdn.cntt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Tri on 2/25/2017.
 */

@Entity
public class capacity_lesson {
    private Long id;
    private float lessonsPerCapacity;

    public capacity_lesson() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getLessonsPerCapacity() {
        return lessonsPerCapacity;
    }

    public void setLessonsPerCapacity(float lessonsPerCapacity) {
        this.lessonsPerCapacity = lessonsPerCapacity;
    }
}
