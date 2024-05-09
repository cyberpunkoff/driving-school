package ru.mirea.edu.drivingschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "_user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String patronymic;
    @ManyToMany
    private List<Lesson> lessons = new ArrayList<>();
    @OneToMany(mappedBy = "student")
    private List<DrivingLesson> drivingLessons;
    private Role role;
}
