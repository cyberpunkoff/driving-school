package ru.mirea.edu.drivingschool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class DrivingLessonDto {
    private StudentDto student;
    private TeacherDto instructor;
    private LocalDateTime time;
}
