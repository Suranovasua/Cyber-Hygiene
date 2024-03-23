package com.example.cyberhygien.integrationTest;

import com.example.cyberhygien.dto.LessonDTO;
import com.example.cyberhygien.dto.ProgressTrackingDTO;
import com.example.cyberhygien.dto.UserAccountDTO;
import com.example.cyberhygien.repository.LessonRepository;
import com.example.cyberhygien.repository.UserAccountRepository;
import com.example.cyberhygien.service.LessonService;
import com.example.cyberhygien.service.ProgressTrackingService;
import com.example.cyberhygien.service.UserAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LessonService lessonService;

    @MockBean
    private UserAccountService userAccountService;

    @MockBean
    private LessonRepository lessonRepository;

    @MockBean
    private UserAccountRepository userAccountRepository;

    @MockBean
    private ProgressTrackingService progressTrackingService;

    @Test
    public void testUserLearningProcess() throws Exception {
        // Создание пользователя
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUsername("testUser");
        userAccountDTO.setEmail("test@example.com");
        userAccountDTO.setPassword("testPassword");

        // Создание урока
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTitle("Test Lesson");
        lessonDTO.setDescription("Description for test lesson");
        lessonDTO.setTextContent("Text content for test lesson");
        lessonDTO.setImagePath("/images/testlesson.png");

        // Создание процесса обучения пользователя
        LocalDateTime completionDate = LocalDateTime.now();
        ProgressTrackingDTO progressTrackingDTO = new ProgressTrackingDTO();
        progressTrackingDTO.setUserAccountId(1L);
        progressTrackingDTO.setLessonId(1L);
        progressTrackingDTO.setCompletionStatus(true);
        progressTrackingDTO.setCompletionDate(completionDate);

        // Отправляем POST запрос для создания пользователя
        mockMvc.perform(post("/api/user-accounts")
                        .contentType("application/json")
                        .content("{\"username\": \"testUser\", \"email\": \"test@example.com\", \"password\": \"testPassword\"}"))
                .andExpect(status().isCreated());

        // Отправляем POST запрос для создания урока
        mockMvc.perform(post("/api/lessons")
                        .contentType("application/json")
                        .content("{\"title\": \"Test Lesson\", \"description\": \"Description for test lesson\", \"textContent\": \"Text content for test lesson\", \"imagePath\": \"/images/testlesson.png\"}"))
                .andExpect(status().isCreated());

        // Отправляем POST запрос для создания процесса обучения пользователя
        mockMvc.perform(post("/api/progress-trackings")
                        .contentType("application/json")
                        .content("{\"userAccountId\": 1, \"lessonId\": 1, \"completionStatus\": true, \"completionDate\": \"" + completionDate + "\"}"))
                .andExpect(status().isCreated());
    }
}
