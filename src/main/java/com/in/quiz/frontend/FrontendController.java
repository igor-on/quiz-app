package com.in.quiz.frontend;

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
    public String postFormSelect(Model model, @ModelAttribute GameOptions gameOptions) {
      log.info("Form submitted with data: " + gameOptions);
      return "index";
    }
}
