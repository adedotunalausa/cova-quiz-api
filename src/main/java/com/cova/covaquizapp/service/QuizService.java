package com.cova.covaquizapp.service;

import com.cova.covaquizapp.dto.AnswerDTO;
import com.cova.covaquizapp.dto.QuestionCollectionDTO;
import com.cova.covaquizapp.dto.ResultDTO;
import com.cova.covaquizapp.model.Question;

import java.util.List;

public interface QuizService {
    QuestionCollectionDTO getAllQuestions();
    ResultDTO getResult(List<AnswerDTO> answers, String email);
    Question getQuestionById(Long id);
}
