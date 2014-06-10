package com.my.app.components.questions;

import javax.swing.JButton;

import com.my.app.domain.Question;


public class TableButton extends JButton {
	
	private Question question;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}	

}
