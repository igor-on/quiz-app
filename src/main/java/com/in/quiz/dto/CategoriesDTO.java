package com.in.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class CategoriesDTO {

    @JsonProperty("trivia_categories")
    private List<CategoryDTO> categories;

    @NoArgsConstructor
    @Getter
    @ToString
    public static class CategoryDTO {
        private Integer id;
        private String name;
    }
}
