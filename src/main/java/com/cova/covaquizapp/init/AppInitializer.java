package com.cova.covaquizapp.init;

import com.cova.covaquizapp.enums.ERole;
import com.cova.covaquizapp.model.Question;
import com.cova.covaquizapp.model.Role;
import com.cova.covaquizapp.repository.QuestionRepository;
import com.cova.covaquizapp.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;

    private final QuestionRepository questionRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role role;
        Question question;

        if(roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
            role = new Role();
            role.setName(ERole.ROLE_USER);
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
        }
    }
}
