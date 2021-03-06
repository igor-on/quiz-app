package com.in.quiz.frontend;

import com.in.quiz.service.OnGoingGameService;
import com.in.quiz.service.QuizDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Log
public class FrontendController {

    public final QuizDataService quizDataService;
    public final OnGoingGameService onGoingGameService;

    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/select")
    public String select(Model model) {
        model.addAttribute("gameOptions", new GameOptions());
        model.addAttribute("categories", quizDataService.getQuizCategories());
        return "select";
    }

    @PostMapping("/select")
    public String postSelectForm(@ModelAttribute GameOptions gameOptions) {
        log.info("Form submitted with data: " + gameOptions);
        onGoingGameService.init(gameOptions);
        return "redirect:game";
    }

    @GetMapping("/game")
    public String game(Model model) {
        model.addAttribute("userAnswer", new UserAnswer());
        model.addAttribute("currentQuestionNumber", onGoingGameService.getCurrentQuestionNumber());
        model.addAttribute("totalQuestionNumber", onGoingGameService.getTotalQuestionsNumber());
        model.addAttribute("currentQuestion", onGoingGameService.getCurrentQuestion());
        model.addAttribute("currentQuestionAnswers", onGoingGameService.getCurrentQuestionAnswerInRandomOrder());
        return "game";

    }

    @PostMapping("/game")
    public String postGameForm(@ModelAttribute UserAnswer userAnswer) {
        log.info("User answer submitted with data: " + userAnswer);
        onGoingGameService.checkAnswerForCurrentQuestionAndUpdatePoints(userAnswer.getAnswer());
        boolean hasNextQuestion = onGoingGameService.proceedToNextQuestion();
        if (hasNextQuestion) {
            return "redirect:game";
        } else {
            return "redirect:summary";
        }
    }

    @GetMapping("/summary")
    public String summary(Model model) {
        model.addAttribute("difficulty", onGoingGameService.getDifficulty());
        model.addAttribute("categoryName", onGoingGameService.getCategoryName());
        model.addAttribute("points", onGoingGameService.getPoints());
        model.addAttribute("maxPoints", onGoingGameService.getTotalQuestionsNumber());
        return "summary";
    }
}
