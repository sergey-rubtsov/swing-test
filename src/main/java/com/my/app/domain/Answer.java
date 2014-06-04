package com.my.app.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * User: Serg
 * Date: 13.03.13
 * Time: 11:30
 */

@Entity
public class Answer implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @NotNull
    @Size(min = 1, max = 10000)
    private String answer = "";

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    private Question question;
    
    private int truth;
    
    public Answer() {
    	
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
        if (!question.getAnswers().contains(this)) {
            question.getAnswers().add(this);
        }
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

	public int getTruth() {
		return truth;
	}

	public void setTruth(int truth) {
		this.truth = truth;
	}    
}
