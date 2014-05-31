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

	private Question question;
	private JList list;
	private DefaultListModel listModel;
	
	public QuestionColumn() {
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		try {
			question = (Question) value;
		} catch (ClassCastException e) {
			return null;
		}
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setFont(new Font("Consolas", Font.PLAIN, 12));
		listModel.addElement(question.getQuestion());
		for (Answer answer : question.getAnswers()) {
			listModel.addElement(answer.getAnswer());
		}
		return list;
	}
}
