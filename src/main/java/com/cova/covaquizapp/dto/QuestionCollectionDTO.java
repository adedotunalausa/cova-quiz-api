package com.cova.covaquizapp.dto;

import com.cova.covaquizapp.model.Question;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class QuestionCollectionDTO {
    private List<Question> questions;
}
