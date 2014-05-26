package com.my.app.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.my.app.domain.Question;

public class QuestionCellRenderer extends JPanel implements ListCellRenderer {
	
	private static final long serialVersionUID = -4985877683580416092L;

	private JLabel questionElement;
	private JButton questionEdit;
	private Question question;
	
	public Question getQuestion() {
		return question;
	}
	
	public void editFrameShow() {
		try {
			EditFrame edit = new EditFrame(getQuestion());
			edit.setVisible(true);
		} catch (NullPointerException e) {
			new NullPointerException("Question is null");
		} catch (Exception e) {
		}
	}

	public QuestionCellRenderer() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		questionEdit = new JButton("Edit");
		questionEdit.setFont(new Font("Consolas", Font.PLAIN, 12));
		questionEdit.setBorder(null);
		questionEdit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				editFrameShow();
			}
		});
		questionEdit.setMnemonic(KeyEvent.VK_D);
		add(questionEdit);
		
		questionElement = new JLabel();
		questionElement.setFont(new Font("Consolas", Font.PLAIN, 12));
		add(questionElement);
		questionElement.setOpaque(true);
		editButtonShow(false);
	}	
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		question = (Question) value;
		questionElement.setText(question.getQuestion());
		if (isSelected) {
			editButtonShow(true);
			questionElement.setBackground(Color.red);
			questionElement.setForeground(Color.white);
		} else {
			editButtonShow(false);
			questionElement.setBackground(new Color(0, 0, 0, 0));
			questionElement.setForeground(Color.black);
		}
		return this;
	}
	
	public void editButtonShow(boolean show) {
		questionEdit.setEnabled(show);
		questionEdit.setVisible(show);
	}

}
