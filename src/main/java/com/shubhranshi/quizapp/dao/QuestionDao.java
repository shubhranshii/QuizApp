package com.shubhranshi.quizapp.dao;

import com.shubhranshi.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository <Question, Integer> {
    //jpa requires class/table name and primary key type
    List<Question> findByCategory(String category);

}
