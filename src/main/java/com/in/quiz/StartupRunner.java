package com.in.quiz;

import com.in.quiz.service.QuizDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    public final QuizDataService quizDataService;

    @Override
    public void run(String... args) throws Exception {
    log.info("Exceuting startup actions...");

    quizDataService.getQuizCategories();
    quizDataService.getQuizQuestions();
    }
}
