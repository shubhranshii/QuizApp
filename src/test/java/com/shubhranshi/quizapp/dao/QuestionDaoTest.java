package com.shubhranshi.quizapp.dao;

import com.shubhranshi.quizapp.QuizappApplication;
import com.shubhranshi.quizapp.model.Question;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class QuestionDaoTest {

    private QuestionDao questionDao;

    @Before
    public void setup() {
        ApplicationContext context = new AnnotationConfigApplicationContext(QuizappApplication.class);
        questionDao = context.getBean(QuestionDao.class);
    }

    @Test
    public void QuestionDao_Add_returnSavedQuestions(){
        //arrange
        Question question= Question.builder()
                .questionTitle("What does JPA stand for?")
                .option1("Java persistence api")
                .option2("jpa")
                .option3("jjj")
                .option4("jkl")
                .rightAnswer("Java persistence api")
                .category("java")
                .difficultylevel("Medium")
                .build();
        //act
        Question savedQuestion= questionDao.save(question);
        //assert
        Assertions.assertThat(savedQuestion).isNotNull();
        Assertions.assertThat(savedQuestion.getId()).isGreaterThan(0);
    }

    @Test
    public void QuestionDao_GetAll_ReturnMoreThanOneQuestion(){
        //arrange

        Question question1= Question.builder()
                .questionTitle("What does JPA stand for?")
                .option1("Java persistence api")
                .option2("jpa")
                .option3("jjj")
                .option4("jkl")
                .rightAnswer("Java persistence api")
                .category("java")
                .difficultylevel("Medium")
                .build();

        Question question2= Question.builder()
                .questionTitle("What does JDBC stand for?")
                .option1("Java persistence api")
                .option2("jdbc")
                .option3("jjj")
                .option4("jkl")
                .rightAnswer("jdbc")
                .category("java")
                .difficultylevel("Medium")
                .build();
        //act
        questionDao.save(question1);
        questionDao.save(question2);

        List<Question> questionList = questionDao.findAll();
        //assert
        Assertions.assertThat(questionList).isNotNull();
        Assertions.assertThat(questionList.size()).isEqualTo(2);

    }

    @Test
    public void QuestionDao_GetByCategory_ReturnsQuestions(){
        //arrange
        Question question= Question.builder()
                .questionTitle("What does JPA stand for?")
                .option1("Java persistence api")
                .option2("jpa")
                .option3("jjj")
                .option4("jkl")
                .rightAnswer("Java persistence api")
                .category("java")
                .difficultylevel("Medium")
                .build();
        //act
        questionDao.save(question);
        List<Question> retrievedQuestions= questionDao.findByCategory("java");
        //assert
        Assertions.assertThat(retrievedQuestions).isNotNull();
        Assertions.assertThat(retrievedQuestions.size()).isGreaterThan(0);
    }

    @Test
    public void QuestionDao_DeleteQuestion_ReturnsNotNull(){
        //arrange
        Question question= Question.builder()
                .questionTitle("What does JPA stand for?")
                .option1("Java persistence api")
                .option2("jpa")
                .option3("jjj")
                .option4("jkl")
                .rightAnswer("Java persistence api")
                .category("java")
                .difficultylevel("Medium")
                .build();
        //act
        questionDao.save(question);
        int id= question.getId();
        questionDao.delete(question);
        Optional<Question> retrievedQuestion= questionDao.findById(id);
        //assert
        Assertions.assertThat(retrievedQuestion).isNotNull();
        Assertions.assertThat(retrievedQuestion).isEmpty();
    }
}
