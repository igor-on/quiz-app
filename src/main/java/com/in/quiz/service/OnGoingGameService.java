package com.in.quiz.service;

import com.in.quiz.dto.QuestionsDTO;
import com.in.quiz.frontend.GameOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class OnGoingGameService {

    private GameOptions gameOptions;
    private int currentQuestionIndex;
    private int points;
    private List<QuestionsDTO.QuestionDTO> questions;
    private final QuizDataService quizDataService;

    public void init(GameOptions gameOptions) {
            this.gameOptions = gameOptions;
            this.currentQuestionIndex = 0;
            this.points = 0;

           this.questions = quizDataService.getQuizQuestions(this.gameOptions);
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionIndex + 1;
    }

    public int getTotalQuestionsNumber() {
        return questions.size();
    }

    public String getCurrentQuestion() {
        return questions.get(currentQuestionIndex).getQuestion();
    }

    public List<String> getCurrentQuestionAnswerInRandomOrder() {
        QuestionsDTO.QuestionDTO dto = questions.get(currentQuestionIndex);

        List<String> answers = new ArrayList<>();
        answers.add(dto.getCorrectAnswer());
        answers.addAll(dto.getIncorrectAnswers());

        Collections.shuffle(answers);

        return answers;
    }

    public boolean checkAnswerForCurrentQuestionAndUpdatePoints(String userAnswer) {
        QuestionsDTO.QuestionDTO dto = questions.get(currentQuestionIndex);

        boolean correct = dto.getCorrectAnswer().equals(userAnswer);
        if(correct) {
            points++;
        }
        return correct;
    }

    public boolean proceedToNextQuestion() {
        currentQuestionIndex++;
        return currentQuestionIndex < questions.size();
    }
}
