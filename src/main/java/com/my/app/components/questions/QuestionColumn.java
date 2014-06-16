package com.my.app.components.questions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;

public class QuestionColumn implements TableCellRenderer {
	
	private QuestionInfoFrame info;

	public QuestionColumn() {
		info = new QuestionInfoFrame();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Question question = (Question) value;
		DefaultListModel listModel = new DefaultListModel();
		JList list = new JList(listModel);
		
		MouseAdapter click = new MouseAdapter() {
            public void mousePressed(MouseEvent arg0) {
                System.out.println("hello");
                arg0.consume();
           }
           
           public void mouseClicked(MouseEvent arg0) {
        	   System.out.println("hello"); 
        	   arg0.consume();
           }
           
           public void mouseReleased(MouseEvent arg0) {
        	   System.out.println("hello"); 
        	   arg0.consume();
           }
		};
		
		list.addMouseListener(click);
		list.setFont(new Font("Consolas", Font.PLAIN, 12));
		list.setCellRenderer(new QuestionsRenderer());
		listModel.addElement(question);
		for (Answer answer : question.getAnswers()) {
			listModel.addElement(answer);
		}
		if (question.getQuestion().length() > 80) {
			list.setToolTipText(question.getQuestion());
		}
		if (hasFocus) {
			showQuestionInfo(question);
/*			JLabel qInfo = new JLabel();
			qInfo.setFont(new Font("Consolas", Font.PLAIN, 14));
			qInfo.setForeground(Color.BLACK);
			qInfo.setText(question.getQuestion());
			JOptionPane.showMessageDialog(null, qInfo);*/
		}
		return list;
	}

	private void showQuestionInfo(Question question) {
		if (info.getQuestion() != null) {
			if (info.getQuestion() != question) {
				info.setQuestion(question);
				info.setVisible(true);
			}
		} else {
			info.setQuestion(question);
			info.setVisible(true);
		}		
	}
}
