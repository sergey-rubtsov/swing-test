package com.my.app.service;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;
import com.my.app.repository.AnswerRepository;
import com.my.app.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Serg
 * Date: 15.03.13
 * Time: 13:40
 */

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;
    
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Answer answer) {
    	Question q = answer.getQuestion();
    	if (q != null) {
        	q.getAnswers().remove(answer);
        	questionRepository.save(q);    		
    	}
        answerRepository.delete(answer);
    }

	@Override
	public Answer findAnswer(String id) {
		return answerRepository.findOne(id);
	}
}
