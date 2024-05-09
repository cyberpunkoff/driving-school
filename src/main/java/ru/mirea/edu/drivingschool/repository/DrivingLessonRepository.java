package ru.mirea.edu.drivingschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.edu.drivingschool.dto.response.DrivingLessonDto;
import ru.mirea.edu.drivingschool.entity.DrivingLesson;
import ru.mirea.edu.drivingschool.entity.User;
import java.util.Arrays;
import java.util.List;

public interface DrivingLessonRepository extends JpaRepository<DrivingLesson, Integer> {
    List<DrivingLesson> findByTeacher(User instructor);

    List<DrivingLesson> findByStudent(User student);
}
