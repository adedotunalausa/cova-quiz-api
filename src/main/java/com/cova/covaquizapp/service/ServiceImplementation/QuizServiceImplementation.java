package com.cova.covaquizapp.service.ServiceImplementation;

import com.cova.covaquizapp.model.Question;
import com.cova.covaquizapp.repository.QuestionRepository;
import com.cova.covaquizapp.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizServiceImplementation implements QuizService {

    private final QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
