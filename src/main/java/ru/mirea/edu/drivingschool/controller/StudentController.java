package ru.mirea.edu.drivingschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.edu.drivingschool.dto.response.LessonDto;
import ru.mirea.edu.drivingschool.entity.Lesson;
import ru.mirea.edu.drivingschool.entity.User;
import ru.mirea.edu.drivingschool.service.LessonService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private LessonService lessonService;

    @GetMapping("/lessons")
    public List<LessonDto> getMyLessons(@AuthenticationPrincipal User student) {
        return lessonService.getStudentLessons(student);
    }
}
