package com.my.app.components.questions;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;

public class QuestionsRenderer extends JLabel implements ListCellRenderer {

	private static final long serialVersionUID = -6911305349605952030L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String text = "";
		setForeground(new Color(0, 0, 0));
		if (value.getClass() == Question.class) {
			Question q = (Question) value;
			text = q.getQuestion();
		}
		if (value.getClass() == Answer.class) {
			Answer a = (Answer) value;
			text = a.getAnswer();			
			setForeground(getColor(a.getTruth()));
		}
		setText(text);	
		return this;
	}
	
	private Color getColor(int truth) {
		truth = truth * 255 / 100;
		if (truth > 255) truth = 255;
		if (truth <= 0) truth = 0;
		Color color = new Color(255 - truth, truth, 0);
		return color;
	}

}
