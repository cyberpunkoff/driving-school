package ru.mirea.edu.drivingschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.edu.drivingschool.dto.request.CreateLessonRequest;
import ru.mirea.edu.drivingschool.dto.response.LessonDto;
import ru.mirea.edu.drivingschool.dto.response.LessonWithStudentsDto;
import ru.mirea.edu.drivingschool.entity.Lesson;
import ru.mirea.edu.drivingschool.entity.User;
import ru.mirea.edu.drivingschool.service.LessonService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final LessonService lessonService;

    @GetMapping("/lessons")
    public List<LessonDto> getLessons() {
        return lessonService.getLessons();
    }

    @GetMapping("/lesson/{id}")
    public LessonWithStudentsDto getLessons(@PathVariable Integer id) {
        return lessonService.getLesson(id);
    }

    @PostMapping("/lesson")
    public void createLesson(
        @RequestBody CreateLessonRequest createLessonRequest,
        @AuthenticationPrincipal User teacher
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        lessonService.createLesson(createLessonRequest, teacher);
    }
}
