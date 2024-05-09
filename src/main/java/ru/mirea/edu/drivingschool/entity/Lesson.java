package ru.mirea.edu.drivingschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue
    private Integer id;
    private String theme;
    private LocalDateTime time;
    @ManyToOne
    private User teacher;
    @ManyToMany(mappedBy = "lessons")
    private List<User> students = new ArrayList<>();
}
