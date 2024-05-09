package ru.mirea.edu.drivingschool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    private Integer id;
    private String theme;
    private TeacherDto teacher;
    private LocalDateTime date;
}
