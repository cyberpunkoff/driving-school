package ru.mirea.edu.drivingschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.edu.drivingschool.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
