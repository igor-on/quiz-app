package com.in.quiz.service;

import com.in.quiz.dto.CategoriesDTO;
import com.in.quiz.dto.CategoryQuestionCountInfoDTO;
import com.in.quiz.dto.QuestionsDTO;
import com.in.quiz.frontend.Difficulty;
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

    private CategoryQuestionCountInfoDTO getCategoryQuestionCount(int categoryId) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://opentdb.com/api_count.php")
                .queryParam("category", categoryId)
                .build().toUri();
        log.info("Quiz category question count retrieve URL: " + uri);
        CategoryQuestionCountInfoDTO result = restTemplate.getForObject(uri, CategoryQuestionCountInfoDTO.class);
        log.info("Quiz category question count content: " + result);
        return result;
    }

    public List<QuestionsDTO.QuestionDTO> getQuizQuestions(GameOptions gameOptions) {
        return getQuizQuestions(gameOptions.getNumberOfQuestions(), gameOptions.getCategoryId(), gameOptions.getDifficulty());
    }

    private List<QuestionsDTO.QuestionDTO> getQuizQuestions(int numberOfQuestions, int categoryId, Difficulty difficulty) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://opentdb.com/api.php")
                .queryParam("amount", numberOfQuestions)
                .queryParam("category", categoryId)
                .queryParam("difficulty", difficulty.name().toLowerCase())
                .build().toUri();
        log.info("Quiz question retrieve URL: " + uri);
        QuestionsDTO result = restTemplate.getForObject(uri, QuestionsDTO.class);
        log.info("Quiz questions: Open Trivia DB response code = " + result.getResponseCode() + ". Content: " + result.getResults());
        return result.getResults();
    }
}
