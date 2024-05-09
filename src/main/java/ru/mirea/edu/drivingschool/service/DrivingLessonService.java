package ru.mirea.edu.drivingschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.edu.drivingschool.dto.request.CreateDrivingLessonRequest;
import ru.mirea.edu.drivingschool.dto.response.DrivingLessonDto;
import ru.mirea.edu.drivingschool.dto.response.LessonDto;
import ru.mirea.edu.drivingschool.entity.DrivingLesson;
import ru.mirea.edu.drivingschool.entity.User;
import ru.mirea.edu.drivingschool.repository.DrivingLessonRepository;
import ru.mirea.edu.drivingschool.repository.UserRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrivingLessonService {
    private final UserRepository userRepository;
    private final DrivingLessonRepository drivingLessonRepository;

    public void createLesson(CreateDrivingLessonRequest createDrivingLessonRequest, User instructor) {
        User student = userRepository.findByEmail(createDrivingLessonRequest.getStudentEmail()).orElseThrow();
        DrivingLesson drivingLesson = DrivingLesson.builder()
            .teacher(instructor)
            .student(student)
            .build();
        student.getDrivingLessons().add(drivingLesson);
        instructor.getDrivingLessonsTeacher().add(drivingLesson);

        userRepository.save(student);
        userRepository.save(instructor);
        drivingLessonRepository.save(drivingLesson);
    }

    public void deleteLesson(Integer id) {
        drivingLessonRepository.deleteById(id);
    }

    public List<DrivingLessonDto> getLessons() {
        return drivingLessonRepository.findAll().stream()
            .map(DrivingLessonService::mapDrivingLessonDto)
            .toList();
    }

    public static DrivingLessonDto mapDrivingLessonDto(DrivingLesson drivingLesson) {
        return DrivingLessonDto.builder()
            .time(drivingLesson.getDateTime())
            .instructor(LessonService.mapUserToTeacher(drivingLesson.getTeacher()))
            .student(LessonService.mapUserToStudent(drivingLesson.getTeacher()))
            .build();
    }

    public DrivingLessonDto getLesson(Integer id) {
        return mapDrivingLessonDto(drivingLessonRepository.findById(id).orElseThrow());
    }

    public List<DrivingLessonDto> getLessonsForInstructor(User instructor) {
        return drivingLessonRepository.findByTeacher(instructor).stream()
            .map(DrivingLessonService::mapDrivingLessonDto)
            .toList();
    }

    public List<DrivingLessonDto> getLessonsForStudent(User student) {
        return drivingLessonRepository.findByStudent(student).stream()
            .map(DrivingLessonService::mapDrivingLessonDto)
            .toList();
    }
}
