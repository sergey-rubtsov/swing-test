package com.my.app.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.my.app.domain.Answer; 
import com.my.app.repository.AnswerRepository;
import com.my.app.repository.QuestionRepository;
import com.my.app.service.AnswerService;
import com.my.app.service.QuestionService;

/**
 * User: Serg
 * Date: 13.03.13
 * Time: 15:27
 */

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext.xml")
@Transactional
public class AnswerTest {
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	AnswerRepository answerRepository;
	
    @Autowired
    QuestionRepository questionRepository;
	
    @Autowired
    QuestionService questionService;

	@Test
	public void testSave() {        
        Question q = questionService.saveQuestion(getFirstQuestion());        
        
        Answer a = getFirstAnswer();
        answerService.saveAnswer(a);
        Assert.assertTrue(answerRepository.count() == 1);
        answerService.deleteAnswer(a);
        Assert.assertTrue(answerRepository.count() == 0);
        
        q.addAnswer(getFirstAnswer());
        q.addAnswer(getSecondAnswer());        
        q = questionService.saveQuestion(q);
        Assert.assertNotNull(q.getAnswers());
        Assert.assertEquals(q.getAnswers().size(), 2);        
	}
	
	@Test
	public void testDelete() {
        Question q = questionService.saveQuestion(getFirstQuestion());
        Answer a = getFirstAnswer();
        Answer a1 = getSecondAnswer();
        q.addAnswer(a);
        q.addAnswer(a1); 
        a.setQuestion(q);
        a1.setQuestion(q);
        
        q = questionService.saveQuestion(q);
        
        //List<Answer> answers = q.getAnswers();
        Set<Answer> answers = q.getAnswers();
        Assert.assertEquals(answers.size(), 2);
        ArrayList<Answer> answ = new ArrayList<Answer>(answers);
        
        answerService.deleteAnswer(answ.get(0));
        q = questionService.findQuestion(q.getId());
        answers = q.getAnswers();
        
        Assert.assertEquals(answers.size(), 1);
        //List<Question> lq = questionService.findAllQuestions();
	}
	
    @Test
    public void testNotNullServicesAndRepos() throws Exception {
    	Assert.assertNotNull(answerRepository);
    	Assert.assertNotNull(answerService);
    	Assert.assertNotNull(questionRepository);
    	Assert.assertNotNull(questionService);
    }
	
    private Question getFirstQuestion() {
        Question question = new Question();
        question.setQuestion("To be or not to be?");
        return question;
    }

    private Question getSecondQuestion() {
        Question question = new Question();
        question.setQuestion("To be?");
        return question;
    }

    private Answer getFirstAnswer() {
        Answer answer = new Answer();
        answer.setAnswer("Yes.");
        answer.setTruth(20);
        return answer;
    }

    private Answer getSecondAnswer() {
        Answer answer = new Answer();
        answer.setAnswer("No.");
        answer.setTruth(80);
        return answer;
    }
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

}
