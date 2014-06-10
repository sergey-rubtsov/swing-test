package com.my.app.service;

import com.my.app.domain.Answer;

/**
 * User: Serg
 * Date: 15.03.13
 * Time: 13:38
 */

public interface AnswerService {

    public Answer saveAnswer(Answer answer);

    public void deleteAnswer(Answer answer);
    
    public Answer findAnswer(String id);

}
