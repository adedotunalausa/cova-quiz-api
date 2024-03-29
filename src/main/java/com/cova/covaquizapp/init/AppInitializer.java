package com.cova.covaquizapp.init;

import com.cova.covaquizapp.dto.RoleDTO;
import com.cova.covaquizapp.enums.ERole;
import com.cova.covaquizapp.model.Question;
import com.cova.covaquizapp.model.Role;
import com.cova.covaquizapp.repository.QuestionRepository;
import com.cova.covaquizapp.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;

    private final QuestionRepository questionRepository;

    private final ModelMapper modelMapper;

    @Override
    public void run(ApplicationArguments args) {
        Role role;
        Question question;

        if(roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
            role = modelMapper.map(new RoleDTO(ERole.ROLE_USER), Role.class);
            roleRepository.save(role);
        }

        if(questionRepository.findAll().isEmpty()) {
            question = new Question();
            question.setTitle("What is a correct syntax to output \"Hello World\" in Java?");
            question.setOptionA("echo \"Hello World\"");
            question.setOptionB("printf(\"Hello World\")");
            question.setOptionC("System.out.println(\"Hello World\")");
            question.setAnswer(3);
            questionRepository.save(question);

            question = new Question();
            question.setTitle("Java is short for \"JavaScript.\"");
            question.setOptionA("True");
            question.setOptionB("False");
            question.setOptionC("Partially true");
            question.setAnswer(2);
            questionRepository.save(question);

            question = new Question();
            question.setTitle("How do you insert COMMENTS in Java code?");
            question.setOptionA("# This is a comment");
            question.setOptionB("// This is a comment");
            question.setOptionC("/* This is a comment");
            question.setAnswer(2);
            questionRepository.save(question);

            question = new Question();
            question.setTitle("Which data type is used to create a variable that should store text?");
            question.setOptionA("String");
            question.setOptionB("Char");
            question.setOptionC("Both");
            question.setAnswer(1);
            questionRepository.save(question);

            question = new Question();
            question.setTitle("How do you create a variable with the numeric value 5");
            question.setOptionA("num x = 5");
            question.setOptionB("float x = 5");
            question.setOptionC("int x = 5");
            question.setAnswer(3);
            questionRepository.save(question);

            question = new Question();
            question.setTitle("How do you create a variable with the floating number 2.8?");
            question.setOptionA("num x = 2.8");
            question.setOptionB("float x = 2.8");
            question.setOptionC("int x = 2.8");
            question.setAnswer(2);
            questionRepository.save(question);

            question = new Question();
            question.setTitle("Which method can be used to find the length of a string?");
            question.setOptionA("length()");
            question.setOptionB("getSize()");
            question.setOptionC("size()");
            question.setAnswer(1);
            questionRepository.save(question);

            question = new Question();
            question.setTitle("Which operator is used to add together two values?");
            question.setOptionA("&&");
            question.setOptionB(".add()");
            question.setOptionC("+");
            question.setAnswer(3);
            questionRepository.save(question);

            question = new Question();
            question.setTitle("The value of a string variable can be surrounded by single quotes.");
            question.setOptionA("True");
            question.setOptionB("False");
            question.setOptionC("Maybe");
            question.setAnswer(2);
            questionRepository.save(question);

            question = new Question();
            question.setTitle("Which operator can be used to compare two values?");
            question.setOptionA("><");
            question.setOptionB("&|");
            question.setOptionC("==");
            question.setAnswer(3);
            questionRepository.save(question);
        }
    }
}
