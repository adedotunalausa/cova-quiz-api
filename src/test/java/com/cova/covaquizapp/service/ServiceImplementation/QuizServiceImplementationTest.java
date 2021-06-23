package com.cova.covaquizapp.service.ServiceImplementation;

import com.cova.covaquizapp.authpayload.request.SignupRequest;
import com.cova.covaquizapp.dto.AnswerCollectionDTO;
import com.cova.covaquizapp.dto.AnswerDTO;
import com.cova.covaquizapp.exception.ResourceNotFoundException;
import com.cova.covaquizapp.model.Question;
import com.cova.covaquizapp.model.User;
import com.cova.covaquizapp.repository.QuestionRepository;
import com.cova.covaquizapp.repository.ResultRepository;
import com.cova.covaquizapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuizServiceImplementationTest {

    private ModelMapper modelMapper;

    @Mock private UserService userService;

    @Mock private ResultRepository resultRepository;

    @Mock private QuestionRepository questionRepository;

    private QuizServiceImplementation quizServiceImplementationUnderTest;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        quizServiceImplementationUnderTest = new QuizServiceImplementation(modelMapper, userService,
                resultRepository, questionRepository);
    }

    @Test
    void canGetAllQuestions() {
        Question question = new Question();
        question.setTitle("Test title");
        question.setOptionA("Test Option A");
        question.setOptionB("Test Option B");
        question.setOptionC("Test Option C");
        question.setAnswer(2);
        List<Question> allQuestions = new ArrayList<>();
        allQuestions.add(question);
        given(questionRepository.findAll()).willReturn(allQuestions);

        quizServiceImplementationUnderTest.getAllQuestions();

        verify(questionRepository).findAll();
    }

    @Test
    void canGetUserResult() {
        // given
        User user = modelMapper.map(new SignupRequest("demarxes", "Adedotun", "Alausa",
                "Male", "adedotunalausa@gmail.com", "123456"), User.class);
        given(userService.findUserByEmail("adedotunalausa@gmail.com")).willReturn(user);

        Question question = new Question();
        question.setTitle("Test title");
        question.setOptionA("Test Option A");
        question.setOptionB("Test Option B");
        question.setOptionC("Test Option C");
        question.setAnswer(2);
        given(questionRepository.findById(2L)).willReturn(java.util.Optional.of(question));
        given(questionRepository.findById(5L)).willReturn(java.util.Optional.of(question));

        AnswerDTO answer1 = new AnswerDTO(2L, 1);
        AnswerDTO answer2 = new AnswerDTO(5L, 3);
        List<AnswerDTO> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        AnswerCollectionDTO answerCollectionDTO = new AnswerCollectionDTO();
        answerCollectionDTO.setAnswers(answers);

        // when
        quizServiceImplementationUnderTest.getResult(answerCollectionDTO, "adedotunalausa@gmail.com");

        // then
        verify(questionRepository).findById(2L);
    }

    @Test
    void willThrowExceptionIfQuestionNotFound() {
        // given
        Question question = new Question();
        question.setTitle("Test title");
        question.setOptionA("Test Option A");
        question.setOptionB("Test Option B");
        question.setOptionC("Test Option C");
        question.setAnswer(2);

        // when
        // then
        assertThatThrownBy(() -> quizServiceImplementationUnderTest
                .getQuestionById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Question not found!");
    }
}