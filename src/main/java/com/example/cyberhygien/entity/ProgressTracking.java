package com.example.cyberhygien.entity;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProgressTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @NotNull(message = "Completion status must not be null")
    private Boolean completionStatus;

    @NotNull
    @PastOrPresent
    private LocalDateTime completionDate;
}
