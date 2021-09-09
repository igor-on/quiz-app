package com.in.quiz.service;

import com.in.quiz.dto.CategoryQuestionCountInfoDTO;
import com.in.quiz.frontend.Difficulty;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.in.quiz.frontend.Difficulty.*;
import static org.junit.jupiter.api.Assertions.*;

class QuizDataServiceTest {

    @Test
    void calculateEachDifficultyQuestionCount_basicEasy() {
        CategoryQuestionCountInfoDTO categoryQuestionCount = new CategoryQuestionCountInfoDTO(5, 17, 13);
        Map<Difficulty, Integer> result = QuizDataService.calculateEachDifficultyQuestionCount(20, EASY, categoryQuestionCount);

        assertEquals(5, result.get(EASY));
        assertEquals(15, result.get(MEDIUM));
        assertNull(result.get(HARD));
    }

}
