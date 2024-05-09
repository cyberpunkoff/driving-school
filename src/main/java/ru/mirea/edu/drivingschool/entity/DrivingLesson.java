package ru.mirea.edu.drivingschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivingLesson {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime dateTime;
    @ManyToOne
    private User student;
    @ManyToOne
    private User teacher;
}
