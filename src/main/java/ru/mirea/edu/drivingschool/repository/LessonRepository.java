package ru.mirea.edu.drivingschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mirea.edu.drivingschool.entity.Lesson;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query("select l from Lesson l join User u on u.id = l.id where u.id = :id")
    List<Lesson> findLessonsByStudentId(Integer id);
}
