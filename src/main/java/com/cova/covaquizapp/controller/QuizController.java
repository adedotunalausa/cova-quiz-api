package com.cova.covaquizapp.controller;

import com.cova.covaquizapp.model.Question;
import com.cova.covaquizapp.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuizController {
    private final QuizService quizService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Question>> takeQuiz() {
        return new ResponseEntity<>(quizService.getAllQuestions(), HttpStatus.OK);
    }

}
