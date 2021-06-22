package com.cova.covaquizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultDTO {
    private String username;
    private String email;
    private int totalQuestionsAnswered;
    private int totalCorrectAnswers;
}
