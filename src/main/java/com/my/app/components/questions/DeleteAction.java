package com.my.app.components.questions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.my.app.domain.Question;

public class DeleteAction extends AbstractAction {
	
	private Question q;
	private QuestionTable table;

	public DeleteAction(Question q, QuestionTable table) {
		super();
		this.table = table;
		this.q = q;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		table.deleteQuestion(q);
	}

}
