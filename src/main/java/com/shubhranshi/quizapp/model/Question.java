package com.shubhranshi.quizapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data //lombok data
@Entity //we want this table to get mapped with the class
public class Question {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;

    @Column(name = "difficulty_level")  // <-- Map JSON field to entity column
    @JsonProperty("difficultyLevel")
    public String difficultylevel;
    private String category;
}
