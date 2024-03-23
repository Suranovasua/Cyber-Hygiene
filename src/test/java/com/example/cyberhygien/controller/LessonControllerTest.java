package com.example.cyberhygien.controller;

import com.example.cyberhygien.dto.LessonDTO;
import com.example.cyberhygien.service.LessonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LessonControllerTest {

    @Mock
    private LessonService lessonService;

    @InjectMocks
    private LessonController lessonController;

    @Test
    void getAllLessons() throws Exception {
        // Mocking service response
        when(lessonService.getAllLessons()).thenReturn(Collections.singletonList(new LessonDTO()));

        // Setting up MockMVC
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lessonController).build();

        // Performing GET request and verifying the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lessons"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    void getLessonById() throws Exception {
        // Mocking service response
        when(lessonService.getLessonById(1L)).thenReturn(new LessonDTO());

        // Setting up MockMVC
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lessonController).build();

        // Performing GET request and verifying the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lessons/{lessonId}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void createLesson() throws Exception {
        // Mocking service response
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTitle("Test Title");
        when(lessonService.createLesson(lessonDTO)).thenReturn(lessonDTO);

        // Setting up MockMVC
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lessonController).build();

        // Performing POST request and verifying the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Title\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Title"));
    }

    @Test
    void updateLesson() throws Exception {
        // Mocking service response
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTitle("Updated Title");
        when(lessonService.updateLesson(1L, lessonDTO)).thenReturn(lessonDTO);

        // Setting up MockMVC
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lessonController).build();

        // Performing PUT request and verifying the response
        mockMvc.perform(MockMvcRequestBuilders.put("/api/lessons/{lessonId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Title\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void deleteLesson() throws Exception {
        // Setting up MockMVC
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lessonController).build();

        // Performing DELETE request and verifying the response
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/lessons/{lessonId}", 1L))
                .andExpect(status().isNoContent());
    }
}
