package com.my.app.domain;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;
import com.my.app.repository.QuestionRepository;
import com.my.app.service.QuestionService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Serg
 * Date: 13.03.13
 * Time: 15:27
 */

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext.xml")
@Transactional
public class QuestionTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionService questionService;

    @Test
    public void testNotNullService() throws Exception {
        Assert.assertNotNull(questionService);
    }

    @Test
    public void testNotNullRepo() throws Exception {
        Assert.assertNotNull(questionRepository);
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertTrue(questionRepository.count() == 0);
        questionService.saveQuestion(getFirstQuestion());
        questionService.saveQuestion(getSecondQuestion());
        Assert.assertTrue(questionRepository.count() == 2);
    }

    @Test
    public void testDelete() throws Exception {
        Assert.assertTrue(questionRepository.count() == 0);
        Question question0 = questionService.saveQuestion(getFirstQuestion());
        Question question1 = questionService.saveQuestion(getSecondQuestion());
        Assert.assertTrue(questionRepository.count() == 2);
        questionService.deleteQuestion(question0);
        Assert.assertTrue(questionRepository.count() == 1);
        questionService.deleteQuestion(question1);
        Assert.assertTrue(questionRepository.count() == 0);
    }

    @Test
    public void testCount() throws Exception {
        Assert.assertTrue(questionService.countQuestions() == 0);
        questionService.saveQuestion(getFirstQuestion());
        questionService.saveQuestion(getSecondQuestion());
        Assert.assertTrue(questionService.countQuestions() == 2);
    }

    @Test
    public void testFindAll() {
        Question question0 = questionService.saveQuestion(getFirstQuestion());
        Question question1 = questionService.saveQuestion(getSecondQuestion());
        Question question2 = questionService.saveQuestion(getThirdQuestion());
        Question question3 = questionService.saveQuestion(getFourthQuestion());
        long count = questionService.countQuestions();
        List<Question> list = questionService.findAllQuestions();
        Assert.assertEquals(list.size(), count);
        Assert.assertTrue(list.contains(question0));
        Assert.assertTrue(list.contains(question1));
        Assert.assertTrue(list.contains(question2));
        Assert.assertTrue(list.contains(question3));
    }

    @Test
    public void testFindQuestionsPages() {
        questionService.saveQuestion(getFirstQuestion());
        questionService.saveQuestion(getSecondQuestion());
        questionService.saveQuestion(getThirdQuestion());
        questionService.saveQuestion(getFourthQuestion());
        List<Question> list = questionService.findQuestionsBySequence("To be", 0, 4);
        Assert.assertEquals(list.size(), 2);
        list = questionService.findQuestionsBySequence("To", 0, 4);
        Assert.assertEquals(list.size(), 3);
    }
    
    @Test
    public void testFindQuestions() {
        questionService.saveQuestion(getFirstQuestion());
        questionService.saveQuestion(getSecondQuestion());
        questionService.saveQuestion(getThirdQuestion());
        questionService.saveQuestion(getFourthQuestion());
        List<Question> list = questionService.findQuestionsBySequence("To be");
        Assert.assertEquals(list.size(), 2);
        list = questionService.findQuestionsBySequence("To");
        Assert.assertEquals(list.size(), 3);
    }

    @Test
    public void testOneToMany() {
        Question question = getFirstQuestion();
        question.addAnswer(getFirstAnswer());
        question.addAnswer(getSecondAnswer());
        question.addAnswer(getThirdAnswer());
        question = questionService.saveQuestion(question);
        Assert.assertNotNull(question.getAnswers());
        Assert.assertEquals(question.getAnswers().size(), 3);
    }

    @Test
    public void testSearchQuestionService() {
        Question question0 = getFirstQuestion();
        question0.addAnswer(getFirstAnswer());
        Question question1 = getSecondQuestion();
        question1.addAnswer(getFirstAnswer());
        question1.addAnswer(getSecondAnswer());
        question1.addAnswer(getThirdAnswer());
        questionService.saveQuestion(question0);
        questionService.saveQuestion(question1);
        
        List<Question> list = questionService.searchQuestionByAnswer("No");
        Assert.assertEquals(list.size(), 1);
        Question testQ = list.get(0);
        Assert.assertEquals(testQ.getId(), question1.getId());
        Assert.assertEquals(testQ.getQuestion(), question1.getQuestion());
    }
    
    @Test
    public void testSearchQuestionByAnswer() {
    	
    }
    
    @Test
    public void testFullTextSearch() {
        Question question0 = getFirstQuestion();
        question0.addAnswer(getFirstAnswer());
        Question question1 = getSecondQuestion();
        question1.addAnswer(getFirstAnswer());
        question1.addAnswer(getSecondAnswer());
        question1.addAnswer(getThirdAnswer());
        
        questionService.saveQuestion(question0);
        questionService.saveQuestion(question1);        
       
        List<Question> list = questionService.fullTextSearch("o");
        Assert.assertEquals(list.size(), 1);
        Question testQ = list.get(0);
        Assert.assertEquals(testQ.getId(), question1.getId());
        Assert.assertEquals(testQ.getQuestion(), question1.getQuestion());
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

    private Question getThirdQuestion() {
        Question question = new Question();
        question.setQuestion("Tomorrow or yesterday?");
        return question;
    }

    private Question getFourthQuestion() {
        Question question = new Question();
        question.setQuestion("Yes or no?");
        return question;
    }

    private Answer getFirstAnswer() {
        Answer answer = new Answer();
        answer.setAnswer("Yes.");
        return answer;
    }

    private Answer getSecondAnswer() {
        Answer answer = new Answer();
        answer.setAnswer("No.");
        return answer;
    }

    private Answer getThirdAnswer() {
        Answer answer = new Answer();
        answer.setAnswer("Tomorrow.");
        return answer;
    }

    private Answer getFourthAnswer() {
        Answer answer = new Answer();
        answer.setAnswer("To be.");
        return answer;
    }

}
