package ru.mirea.edu.drivingschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.mirea.edu.drivingschool.dto.request.CreateLessonRequest;
import ru.mirea.edu.drivingschool.dto.response.LessonDto;
import ru.mirea.edu.drivingschool.dto.response.LessonWithStudentsDto;
import ru.mirea.edu.drivingschool.dto.response.StudentDto;
import ru.mirea.edu.drivingschool.dto.response.TeacherDto;
import ru.mirea.edu.drivingschool.entity.Lesson;
import ru.mirea.edu.drivingschool.entity.User;
import ru.mirea.edu.drivingschool.repository.LessonRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public List<LessonDto> getLessons() {
        return lessonRepository.findAll().stream()
            .map(lesson -> LessonDto.builder()
                .id(lesson.getId())
                .date(lesson.getTime())
                .theme(lesson.getTheme())
                .teacher(mapUserToTeacher(lesson.getTeacher()))
                .build())
            .toList();
    }

    public static TeacherDto mapUserToTeacher(User user) {
        return TeacherDto.builder()
            .name(user.getFirstName())
            .surname(user.getLastName())
            .patronymic(user.getPatronymic())
            .build();
    }

    public static StudentDto mapUserToStudent(User user) {
        return StudentDto.builder()
            .name(user.getFirstName())
            .surname(user.getLastName())
            .patronymic(user.getPatronymic())
            .build();
    }

    public LessonWithStudentsDto getLesson(Integer id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        return LessonWithStudentsDto.builder()
            .id(lesson.getId())
            .date(lesson.getTime())
            .theme(lesson.getTheme())
            .teacher(mapUserToTeacher(lesson.getTeacher()))
            .students(lesson.getStudents().stream()
                .map(LessonService::mapUserToStudent)
                .toList())
            .build();
    }

    public void createLesson(CreateLessonRequest createLessonRequest, User teacher) {
        Lesson lesson = Lesson.builder()
            .teacher(teacher)
            .theme(createLessonRequest.getTheme())
            .time(createLessonRequest.getTime())
            .build();

        lessonRepository.save(lesson);
    }
}
