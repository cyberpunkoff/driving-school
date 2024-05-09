package ru.mirea.edu.drivingschool.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLessonRequest {
    private String theme;
    private LocalDateTime time;
}
