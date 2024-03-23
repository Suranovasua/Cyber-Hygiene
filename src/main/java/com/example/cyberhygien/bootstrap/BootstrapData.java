package com.example.cyberhygien.bootstrap;

import com.example.cyberhygien.dto.LessonDTO;
import com.example.cyberhygien.entity.Lesson;
import com.example.cyberhygien.mapper.LessonMapper;
import com.example.cyberhygien.mapper.UserAccountMapper;
import com.example.cyberhygien.repository.LessonRepository;
import com.example.cyberhygien.repository.UserAccountRepository;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final LessonRepository lessonRepository;



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
    }
}
