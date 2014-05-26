package com.my.app.components;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;

public class EditFrame extends JFrame {
	
	private static final long serialVersionUID = 6218065377745508692L;
	private Question question;
	private JEditorPane editorQuestion;
	private JEditorPane editorAnswer;
	private DefaultListModel listModel;
	private SpinnerNumberModel spinnerModel;
	private JButton btnAdd;
	
	public EditFrame(String text) {
		this();
		editorQuestion.setText(text);				
	}

	public EditFrame(Question question) {
		this();
		setQuestion(question);
	}
	
	private EditFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 340);
		setContentPane(createPanel());
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblQuestion.setBounds(10, 11, 64, 14);
		panel.add(lblQuestion);
		
		editorQuestion = new JEditorPane();
		editorQuestion.setFont(new Font("Consolas", Font.PLAIN, 11));
		editorQuestion.setBounds(10, 29, 344, 62);
		panel.add(editorQuestion);
		
		listModel = new DefaultListModel();
		JList list = new JList(listModel);
		list.setFont(new Font("Consolas", Font.PLAIN, 11));
		list.setBounds(10, 163, 344, 126);
		panel.add(list);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblAnswer.setBounds(10, 102, 46, 14);
		panel.add(lblAnswer);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSave.setBounds(364, 29, 76, 62);
		panel.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDelete.setBounds(364, 174, 76, 52);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				setVisible(false);
				dispose();
			}
		});
		btnCancel.setBounds(364, 237, 76, 52);
		panel.add(btnCancel);
		
		editorAnswer = new JEditorPane();
		editorAnswer.setFont(new Font("Consolas", Font.PLAIN, 11));
		editorAnswer.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				onChangeValue();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				onChangeValue();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				onChangeValue();
			}
		}); 
		editorAnswer.setBounds(10, 118, 302, 34);
		panel.add(editorAnswer);
		
		JLabel lblVerity = new JLabel("Verity");
		lblVerity.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblVerity.setBounds(314, 102, 40, 14);
		panel.add(lblVerity);
		
		JSpinner spinner = new JSpinner();
		spinnerModel = new SpinnerNumberModel(50, 0, 100, 10);
		spinner.setModel(spinnerModel);
		spinner.setBounds(314, 118, 40, 34);		
		panel.add(spinner);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Question question = getQuestion();
				question.addAnswer(new Answer(editorAnswer.getText(), (Integer)spinnerModel.getValue()));
			}
		});
		btnAdd.setBounds(364, 118, 76, 41);
		panel.add(btnAdd);
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		return panel;
	}
	
	private void onChangeValue() {
		if (editorAnswer.getText().length() > 0) {
			btnAdd.setEnabled(true);
		} else btnAdd.setEnabled(false);
	}

	public void setQuestion(Question question) {
		this.question = question;
		editorQuestion.setText(question.getQuestion());
		editorAnswer.setText("");
		setAnswers(question.getAnswers());
	}
	
	public Question getQuestion() {
		if (this.question == null) return createNewQuestion(editorQuestion.getText());
		return question;
	}	

	public Question createNewQuestion(String text) {
		this.question = new Question(text);		
		return question;		
	}
	
	public List<Answer> getAnswersFromModel() {
		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < listModel.getSize(); i++) {
			answers.add((Answer) this.listModel.get(i));
		}
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.listModel.clear();
		for (Answer answer : answers) {
			this.listModel.addElement(answer);
		}
	}
}
