package com.cova.covaquizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerDTO {

    private Long questionId;
    private int answer;

}
