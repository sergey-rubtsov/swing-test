package com.my.app.components.questions;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;

public class QuestionColumn implements
TableCellRenderer {

	private static final long serialVersionUID = -14429936538178095L;
	
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
