package com.my.app.components.answers;

import java.awt.Color;

import javax.swing.JLabel;

import com.my.app.domain.Answer;

public class AnswerLabel extends JLabel {
	
	private static final long serialVersionUID = 376513141405905578L;
	Answer answer;
	
	public AnswerLabel(String answer) {
		super(answer);
	}

	public AnswerLabel(Answer answer) {
		super(answer.getAnswer());
		this.answer = answer;
		setForeground(getColor(answer.getTruth()));
	}
	
	public Color getColor(int truth) {
		truth = truth * 255 / 100;
		if (truth > 255) truth = 255;
		if (truth <= 0) truth = 0;
		Color color = new Color(255 - truth, truth, 0);
		return color;
	}

	public Answer getAnswer() {
		return answer;
	}

}
