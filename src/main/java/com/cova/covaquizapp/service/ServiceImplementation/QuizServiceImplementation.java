package com.cova.covaquizapp.service.ServiceImplementation;

import com.cova.covaquizapp.dto.AnswerCollectionDTO;
import com.cova.covaquizapp.dto.AnswerDTO;
import com.cova.covaquizapp.dto.QuestionCollectionDTO;
import com.cova.covaquizapp.dto.ResultDTO;
import com.cova.covaquizapp.exception.ResourceNotFoundException;
import com.cova.covaquizapp.model.Question;
import com.cova.covaquizapp.model.Result;
import com.cova.covaquizapp.model.User;
import com.cova.covaquizapp.repository.QuestionRepository;
import com.cova.covaquizapp.repository.ResultRepository;
import com.cova.covaquizapp.service.QuizService;
import com.cova.covaquizapp.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class QuizServiceImplementation implements QuizService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ResultRepository resultRepository;
    private final QuestionRepository questionRepository;

    @Override
    public QuestionCollectionDTO getAllQuestions() {
        List<Question> allQuestions = questionRepository.findAll();
        List<Question> questionList = new ArrayList<>();

        Random random = new Random();

        for(int i = 0; i < 10; i++) {
            int rand = random.nextInt(allQuestions.size());
            questionList.add(allQuestions.get(rand));
            allQuestions.remove(rand);
        }

        return new QuestionCollectionDTO(questionList);
    }

    @Override
    public ResultDTO getResult(AnswerCollectionDTO answers, String email) {

        User currentUser = userService.findUserByEmail(email);

        int correctAnswers = getCorrectAnswers(answers.getAnswers());

        ResultDTO resultDTO = new ResultDTO(currentUser.getUsername(), currentUser.getEmail(),
                answers.getAnswers().size(), correctAnswers);

        Result result = modelMapper.map(resultDTO, Result.class);
        resultRepository.save(result);

        return resultDTO;
    }

    private int getCorrectAnswers(List<AnswerDTO> answers) {
        AtomicInteger correctAnswers = new AtomicInteger();

        answers.forEach(answerDTO -> {
            Question question = getQuestionById(answerDTO.getQuestionId());

            if(question.getAnswer() == answerDTO.getAnswer()) {
                correctAnswers.getAndIncrement();
            }
        });

        return correctAnswers.get();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Question not found!");
                }
        );
    }
}
