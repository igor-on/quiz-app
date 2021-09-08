package com.in.quiz.service;

import com.in.quiz.dto.CategoriesDTO;
import com.in.quiz.dto.QuestionsDTO;
import com.in.quiz.frontend.GameOptions;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@Service
@Log
public class QuizDataService {

    RestTemplate restTemplate = new RestTemplate();

    public List<CategoriesDTO.CategoryDTO> getQuizCategories() {
        CategoriesDTO result = restTemplate.getForObject("https://opentdb.com/api_category.php", CategoriesDTO.class);
        log.info("Quiz categories: " + result.getCategories());
        return result.getCategories();
    }

    public List<QuestionsDTO.QuestionDTO> getQuizQuestions(GameOptions gameOptions) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://opentdb.com/api.php")
                .queryParam("amount", gameOptions.getNumberOfQuestions())
                .queryParam("category", gameOptions.getCategoryId())
                .queryParam("difficulty", gameOptions.getDifficulty().name().toLowerCase(Locale.ROOT))
                .build().toUri();
        log.info("Quiz question retrieve URL: " + uri);

        QuestionsDTO result = restTemplate.getForObject(uri, QuestionsDTO.class);
        log.info("Quiz questions: " + result.getResults());

        return result.getResults();
    }
}
