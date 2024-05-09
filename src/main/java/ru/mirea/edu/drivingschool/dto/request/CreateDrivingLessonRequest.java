package ru.mirea.edu.drivingschool.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDrivingLessonRequest {
    private LocalDateTime time;
    private String studentEmail;
}
