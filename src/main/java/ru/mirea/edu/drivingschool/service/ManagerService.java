package ru.mirea.edu.drivingschool.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.internal.LegacySpecHelper;
import org.springframework.stereotype.Service;
import ru.mirea.edu.drivingschool.entity.Lesson;
import ru.mirea.edu.drivingschool.entity.User;
import ru.mirea.edu.drivingschool.repository.LessonRepository;
import ru.mirea.edu.drivingschool.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final LessonService lessonService;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public void addStudentToLesson(Integer studentId, Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        User student = userRepository.findById(studentId).orElseThrow();
        lesson.getStudents().add(student);
        student.getLessons().add(lesson);
        lessonRepository.save(lesson);
        userRepository.save(student);
    }
}
