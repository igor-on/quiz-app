package com.in.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class QuestionsDTO {

    @JsonProperty("response_code")
    private Integer responseCode;
    private List<QuestionDTO> results;


    @NoArgsConstructor
    @Getter
    @ToString
    public static class QuestionDTO {
        private String category;
        private String type;
        private String difficulty;
        private String question;
        @JsonProperty("correct_answer")
        private String correctAnswer;
        @JsonProperty("incorrect_answers")
        private List<String> incorrectAnswers;
    }
}
