package com.my.app.components.questions;

import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;

public class QuestionColumn implements TableCellRenderer {

	public QuestionColumn() {
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Question question = (Question) value;
		DefaultListModel listModel = new DefaultListModel();
		JList list = new JList(listModel);
		list.setFont(new Font("Consolas", Font.PLAIN, 12));
		list.setCellRenderer(new QuestionsRenderer());
		listModel.addElement(question);
		for (Answer answer : question.getAnswers()) {
			listModel.addElement(answer);
		}
		if (question.getQuestion().length() > 80) {
			list.setToolTipText(question.getQuestion());
		}	
		return list;
	}
}
