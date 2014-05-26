package com.my.app.service;

import com.my.app.domain.Question;

import java.util.List;

/**
 * User: Serg
 * Date: 13.03.13
 * Time: 14:17
 */

public interface QuestionService {

    public List<Question> findQuestionsBySequence(String question, int pageNumber, int countPerPage);
    
    public List<Question> findQuestionsBySequence(String question);

    public List<Question> findAllQuestions();

    public long countQuestions();

    public Question saveQuestion(Question question);

    public void deleteQuestion(Question question);

    public void deleteQuestion(String id);

	public List<Question> searchQuestion(String keywords);	

}
