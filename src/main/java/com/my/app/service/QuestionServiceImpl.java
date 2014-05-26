package com.my.app.service;

import com.my.app.domain.Question;
import com.my.app.repository.QuestionRepository;
import com.my.app.utils.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Serg
 * Date: 13.03.13
 * Time: 14:33
 */

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<Question> findQuestionsBySequence(String question, int pageNumber, int countPerPage) {
        Sort sort = new Sort(Sort.Direction.ASC, "question");
        return questionRepository.findByQuestionLike('%' + question + '%', new Paging(pageNumber, countPerPage, 0, sort));
    }
    
    @Override
    public List<Question> findQuestionsBySequence(String question) {
    	return questionRepository.findByQuestionLike('%' + question + '%');
    }

    @Override
    public List<Question> findAllQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    public long countQuestions() {
        return questionRepository.count();
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }

    @Override
    public void deleteQuestion(String id) {
        questionRepository.delete(id);
    }

	@Override
	public List<Question> searchQuestion(String keywords) {
		return findQuestionsBySequence(keywords);
	}

}
