package com.cova.covaquizapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
@EqualsAndHashCode(callSuper = true)
public class Question extends BaseModel{

    private String title;

    private String optionA;

    private String optionB;

    private String optionC;

    @JsonIgnore
    private int answer;
}
