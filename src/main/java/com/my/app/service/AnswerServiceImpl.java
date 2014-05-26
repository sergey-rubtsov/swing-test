package com.my.app.service;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;
import com.my.app.repository.AnswerRepository;

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

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Answer answer) {
        answerRepository.delete(answer);
    }
}
