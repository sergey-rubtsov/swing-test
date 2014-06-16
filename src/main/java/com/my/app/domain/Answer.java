package com.my.app.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
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
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @NotNull
    @Size(min = 0, max = 8191)
    @Field
    private String answer = "";
    
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public Question getQuestion() {
        return question;
    }    
    
    private int truth;
    
    public Answer() {    	
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestion(Question question) {
        this.question = question;
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
