package com.example.cyberhygien.dto;

import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgressTrackingDTO {

    private Long id;

    @NotNull
    private Long userAccountId;

    @NotNull
    private Long lessonId;

    @NotNull(message = "Completion status must not be null")
    private Boolean completionStatus;

    @NotNull
    @PastOrPresent
    private LocalDateTime completionDate;
}
