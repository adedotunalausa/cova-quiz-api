package com.cova.covaquizapp.service;

import com.cova.covaquizapp.dto.AnswerCollectionDTO;
import com.cova.covaquizapp.dto.QuestionCollectionDTO;
import com.cova.covaquizapp.dto.ResultDTO;
import com.cova.covaquizapp.model.Question;

public interface QuizService {
    QuestionCollectionDTO getAllQuestions();
    ResultDTO getResult(AnswerCollectionDTO answers, String email);
    Question getQuestionById(Long id);
}
