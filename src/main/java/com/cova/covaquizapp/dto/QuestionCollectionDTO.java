package com.cova.covaquizapp.dto;

import com.cova.covaquizapp.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionCollectionDTO {
    private List<Question> questions;
}
