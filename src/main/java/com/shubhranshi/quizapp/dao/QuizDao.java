package com.shubhranshi.quizapp.dao;

import com.shubhranshi.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository <Quiz, Integer> {
}
