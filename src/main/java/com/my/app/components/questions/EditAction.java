package com.my.app.components.questions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.my.app.components.EditFrame;
import com.my.app.domain.Question;

public class EditAction extends AbstractAction {
	
	private Question q;
	private QuestionTable table;

	public EditAction(Question q, QuestionTable table) {
		super();
		this.q = q;
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		EditFrame edit = new EditFrame(q, table);
		edit.setVisible(true);
	}

}
