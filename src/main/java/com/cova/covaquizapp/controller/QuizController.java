package com.cova.covaquizapp.controller;

import com.cova.covaquizapp.dto.AnswerCollectionDTO;
import com.cova.covaquizapp.dto.QuestionCollectionDTO;
import com.cova.covaquizapp.dto.ResultDTO;
import com.cova.covaquizapp.security.jwt.JwtUtils;
import com.cova.covaquizapp.service.QuizService;
import com.cova.covaquizapp.util.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuizController {

    private final JwtUtils jwtUtils;
    private final QuizService quizService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<QuestionCollectionDTO> takeQuiz() {
        return new ResponseEntity<>(quizService.getAllQuestions(), HttpStatus.OK);
    }

    @PostMapping("/submit")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResultDTO> submitAndGetResult(HttpServletRequest request,
                                                        @RequestBody AnswerCollectionDTO answers) {
        String jwt = MethodUtils.parseJwt(request);
        String email = jwtUtils.getUserNameFromJwtToken(jwt);
        return new ResponseEntity<>(quizService.getResult(answers, email), HttpStatus.OK);
    }

}
