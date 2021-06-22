package com.cova.covaquizapp.model;

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
@Table(name = "results")
@EqualsAndHashCode(callSuper = true)
public class Result extends BaseModel{

    private String username;
    private int totalCorrect = 0;
}
