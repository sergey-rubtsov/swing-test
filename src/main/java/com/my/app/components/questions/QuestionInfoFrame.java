package com.my.app.components.questions;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import java.awt.Component;

public class QuestionInfoFrame extends JFrame {

	private JTextPane textPane;
	private Question question;
	
	public QuestionInfoFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Consolas", Font.PLAIN, 12));
		getContentPane().add(textPane);
	}
	
	public void setQuestion(Question q) {
		this.question = q;
		String text = "";
		text = text + q.getQuestion() + "\n\n";		
		for (Answer a : q.getAnswers()) {
			text = text + " [" + a.getTruth() + "] " + a.getAnswer() + "\n"; 
		}
		textPane.setText(text);
	}
	
	public Question getQuestion() {
		return this.question;
	}

}
