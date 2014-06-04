package com.my.app.components.answers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.my.app.components.EditFrame;
import com.my.app.domain.Answer;

public class AnswerCellRenderer extends JLabel implements ListCellRenderer {
	
	private static final long serialVersionUID = 3901844984589773667L;
	
	private Answer a;
	private EditFrame frame;
	
	public AnswerCellRenderer(EditFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		a = (Answer) value;		
		setText(a.getAnswer());		
		setForeground(getColor(a.getTruth()));
		if (isSelected) {
			setBackground(Color.RED);
			setForeground(Color.BLACK);
			frame.setFocusedAnswer(a);
		}
		if (cellHasFocus) {
			setBackground(Color.RED);
			setForeground(Color.BLACK);
			frame.setFocusedAnswer(a);
		}
		if (a.getAnswer().length() > 150) {
			setToolTipText(a.getAnswer());
		}		
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
