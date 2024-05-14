package com.example.cyberhygien.bootstrap;

import com.example.cyberhygien.entity.Lesson;
import com.example.cyberhygien.entity.ProgressTracking;
import com.example.cyberhygien.repository.LessonRepository;
import com.example.cyberhygien.repository.ProgressTrackingRepository;
import com.example.cyberhygien.security.entities.User;
import com.example.cyberhygien.security.entities.UserRole;
import com.example.cyberhygien.security.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final LessonRepository lessonRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;




    private final ProgressTrackingRepository progressTrackingRepository;

     public static List<Lesson> generateSampleLessons(int numberOfLessons) {
        List<Lesson> lessons = new ArrayList<>();


        for (int i = 1; i <= numberOfLessons; i++) {
            Lesson lesson = Lesson.builder()
                    .title("Lesson " + i)
                    .description("Description for Lesson " + i)
                    .textContent("Text content for Lesson " + i)
                    .imagePath("/images/lesson" + i + ".png")
                    .build();

            lessons.add(lesson);
        }
        return lessons;
    }


    @Override
    public void run(String... args) throws Exception {
        List<Lesson> sampleLessons = generateSampleLessons(10);
        lessonRepository.saveAll(sampleLessons);

        List<User> sampleUserAccounts = generateSampleUserAccounts(10);
        userRepository.saveAll(sampleUserAccounts);

        generateProgressTrackingData(sampleUserAccounts, sampleLessons);
    }

    private void generateProgressTrackingData(List<User> userAccounts, List<Lesson> lessons) {
        for (User userAccount : userAccounts) {
            for (Lesson lesson : lessons) {
                ProgressTracking progressTracking = ProgressTracking.builder()
                        .userAccount(userAccount)
                        .lesson(lesson)
                        .completionStatus(false) // Assuming default completion status
                        .completionDate(LocalDateTime.now()) // Assuming completion date is current timestamp
                        .build();
                progressTrackingRepository.save(progressTracking);
            }
        }
    }

    public List<User> generateSampleUserAccounts(int numberOfAccounts) {
        List<User> userAccounts = new ArrayList<>();

        for (int i = 1; i <= numberOfAccounts; i++) {
            User userAccount = User.builder()
                    .username("user" + i)
                    .email("user" + i + "@example.com")
                    .password(passwordEncoder.encode("password"))
                    .roles(Set.of(UserRole.user))
                    .build();

            userAccounts.add(userAccount);
        }
        return userAccounts;
    }
}
