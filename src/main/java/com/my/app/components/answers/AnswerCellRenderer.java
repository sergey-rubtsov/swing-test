package com.my.app.components.answers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.my.app.components.EditFrame;
import com.my.app.domain.Answer;

public class AnswerCellRenderer implements ListCellRenderer {
	
	private EditFrame frame;
	
	public AnswerCellRenderer(EditFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		AnswerLabel label = (AnswerLabel) value;
		if (isSelected) {
			label.setBackground(Color.RED);
			label.setForeground(Color.BLACK);
			frame.setFocusedAnswer(label);
		}
		if (cellHasFocus) {
			label.setBackground(Color.RED);
			label.setForeground(Color.BLACK);
			frame.setFocusedAnswer(label);
		}
		if (label.getAnswer().getAnswer().length() > 150) {
			label.setToolTipText(label.getAnswer().getAnswer());
		}		
		return label;
	}
}
