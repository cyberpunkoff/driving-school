package ru.mirea.edu.drivingschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DrivingLesson {
    @Id
    private Integer id;
    @ManyToOne
    private User student;
    private Integer teacherId;
}
