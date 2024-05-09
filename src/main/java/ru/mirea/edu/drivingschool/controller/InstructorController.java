package ru.mirea.edu.drivingschool.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.edu.drivingschool.dto.request.CreateDrivingLessonRequest;
import ru.mirea.edu.drivingschool.dto.response.DrivingLessonDto;
import ru.mirea.edu.drivingschool.entity.User;
import ru.mirea.edu.drivingschool.service.DrivingLessonService;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final DrivingLessonService drivingLessonService;

    @PostMapping("/lesson") public void createLesson(
        @RequestBody CreateDrivingLessonRequest createDrivingLessonRequest, @AuthenticationPrincipal User instructor
    ) {
        drivingLessonService.createLesson(createDrivingLessonRequest, instructor);
    }

    @DeleteMapping("/lesson/{id}")
    public void deleteLesson(@PathVariable Integer id) {
        drivingLessonService.deleteLesson(id);
    }

    @GetMapping("/lessons")
    public List<DrivingLessonDto> getLessons(@AuthenticationPrincipal User instructor) {
        return drivingLessonService.getLessonsForInstructor(instructor);
    }

    @GetMapping("/lesson/{id}")
    public DrivingLessonDto getLesson(@PathVariable Integer id) {
        return drivingLessonService.getLesson(id);
    }
}
