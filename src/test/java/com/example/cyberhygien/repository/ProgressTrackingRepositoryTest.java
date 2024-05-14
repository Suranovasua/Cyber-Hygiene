//package com.example.cyberhygien.repository;
//
//import com.example.cyberhygien.entity.ProgressTracking;
//import com.example.cyberhygien.entity.Lesson;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//public class ProgressTrackingRepositoryTest {
//
//    @Autowired
//    private ProgressTrackingRepository progressTrackingRepository;
//
//    @Test
//    public void testSaveProgressTracking() {
//        UserAccount userAccount = new UserAccount();
//        userAccount.setUsername("testUser");
//        userAccount.setEmail("test@example.com");
//        userAccount.setPassword("password");
//        userAccount.setRole("ROLE_USER");
//
//        Lesson lesson = new Lesson();
//        lesson.setTitle("Test Lesson");
//        lesson.setDescription("Description of Test Lesson");
//        lesson.setTextContent("Text content of Test Lesson");
//        lesson.setImagePath("lesson.jpg");
//
//
//        ProgressTracking progressTracking = new ProgressTracking();
//        progressTracking.setUserAccount(userAccount);
//        progressTracking.setLesson(lesson);
//        progressTracking.setCompletionStatus(true);
//        progressTracking.setCompletionDate(LocalDateTime.now());
//
//
//        ProgressTracking savedProgressTracking = progressTrackingRepository.save(progressTracking);
//
//
//        assertThat(savedProgressTracking.getId()).isNotNull();
//        assertThat(savedProgressTracking.getUserAccount()).isEqualTo(userAccount);
//        assertThat(savedProgressTracking.getLesson()).isEqualTo(lesson);
//        assertThat(savedProgressTracking.getCompletionStatus()).isTrue();
//        assertThat(savedProgressTracking.getCompletionDate()).isNotNull();
//    }
//}
