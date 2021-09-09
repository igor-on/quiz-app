package com.in.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.in.quiz.frontend.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class CategoryQuestionCountInfoDTO {

    @JsonProperty("category_id")
    private int categoryId;
    @JsonProperty("category_question_count")
    private CategoryQuestionCountInfo categoryQuestionCountInfo;

    public CategoryQuestionCountInfoDTO(int easy, int medium, int hard) {
     categoryQuestionCountInfo = new CategoryQuestionCountInfo(easy+medium+hard, easy, medium, hard);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @ToString
    public static class CategoryQuestionCountInfo {

        @JsonProperty("total_question_count")
        private int totalQuestionCount;
        @JsonProperty("total_easy_question_count")
        private int totalEasyQuestionCount;
        @JsonProperty("total_medium_question_count")
        private int totalMediumQuestionCount;
        @JsonProperty("total_hard_question_count")
        private int totalHardQuestionCount;
    }

    public int getTotalQuestionsCount() {
        return categoryQuestionCountInfo.totalQuestionCount;
    }

    public int getQuestionCountForDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return categoryQuestionCountInfo.totalEasyQuestionCount;
            case MEDIUM:
                return categoryQuestionCountInfo.totalMediumQuestionCount;
            case HARD:
                return categoryQuestionCountInfo.totalHardQuestionCount;
        }
        return 0;
    }
}
