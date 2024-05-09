package ru.mirea.edu.drivingschool.controller;

import  java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.edu.drivingschool.dto.response.DrivingLessonDto;
import ru.mirea.edu.drivingschool.dto.response.LessonDto;
import ru.mirea.edu.drivingschool.entity.User;
import ru.mirea.edu.drivingschool.service.DrivingLessonService;
import ru.mirea.edu.drivingschool.service.LessonService;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final LessonService lessonService;
    private final DrivingLessonService drivingLessonService;

    @GetMapping("/lessons/theory")
    public List<LessonDto> getMyLessons(@AuthenticationPrincipal User student) {
        return lessonService.getStudentLessons(student);
    }

    @GetMapping("/lessons/driving")
    public List<DrivingLessonDto> getMyDrivingLessons(@AuthenticationPrincipal User student) {
        return drivingLessonService.getLessonsForStudent(student);
    }
}
