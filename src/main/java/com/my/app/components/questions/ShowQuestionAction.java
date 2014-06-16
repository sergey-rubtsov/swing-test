package com.my.app.components.questions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.my.app.components.EditFrame;
import com.my.app.domain.Question;

public class ShowQuestionAction extends AbstractAction {
	
	private Question q;

	public ShowQuestionAction(Question q) {
		super();
		this.q = q;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		QuestionInfoFrame panel = new QuestionInfoFrame();
		panel.setQuestion(q);
		JOptionPane.showMessageDialog(null, panel);
	}

}
