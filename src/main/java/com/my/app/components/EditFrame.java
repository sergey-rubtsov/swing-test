package com.my.app.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import com.my.app.components.answers.AnswerCellRenderer;
import com.my.app.components.answers.AnswerLabel;
import com.my.app.components.questions.QuestionTable;
import com.my.app.domain.Answer;
import com.my.app.domain.Question;
import com.my.app.service.AnswerService;
import com.my.app.service.QuestionService;

@Configurable(preConstruction = true)
@Transactional
public class EditFrame extends JFrame {
	
	@Autowired
	QuestionService questionService;

	@Autowired
	AnswerService answerService;
	
	private static final long serialVersionUID = 6218065377745508692L;
	private Question question;
	private AnswerLabel focusedAnswer;
	private JEditorPane editorQuestion;
	private JEditorPane editorAnswer;
	private DefaultListModel listModel;
	private SpinnerNumberModel spinnerModel;
	private JButton btnAdd;
	private JButton btnDelete;
	private EditorState editorState;
	QuestionTable table;
	
	public enum EditorState {
		CREATE, EDIT
	}
	
	public EditFrame(String text) {
		this();
		editorState = EditorState.CREATE;
		editorQuestion.setText(text);				
	}

	public EditFrame(Question question, QuestionTable table) {
		this();
		this.table = table;
		editorState = EditorState.EDIT;
		setQuestion(question);
	}
	
	private EditFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
		JScrollPane qScroller = new JScrollPane(editorQuestion);
		qScroller.setBounds(10, 29, 344, 62);
		panel.add(qScroller);
		
		listModel = new DefaultListModel();		
		JList list = new JList(listModel);
		list.setCellRenderer(new AnswerCellRenderer(this));
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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO: answers edition
				if (editorState == EditorState.EDIT) {					
					Question q = getQuestion();
					q.setQuestion(editorQuestion.getText());
					List<Answer> newAnswers = getAnswersFromModel();
					for (Answer a : newAnswers) {
						a.setQuestion(q);
						//if (!q.getAnswers().contains(a)) q.addAnswer(a);
					}
					HashSet<Answer> set = new HashSet<Answer>(newAnswers);
					q.setAnswers(set);
					questionService.saveQuestion(q);					
				}
				if (editorState == EditorState.CREATE) {
					Question q = getQuestion();
					for (Answer a : getAnswersFromModel()) {
						q.addAnswer(a);
					}
					questionService.saveQuestion(q);
					//for (Answer answer : getQuestion().getAnswers()) answerService.saveAnswer(answer);
				}				
				setVisible(false);
				dispose();
			}
		});
		btnSave.setBounds(364, 29, 76, 62);
		panel.add(btnSave);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JLabel warning = new JLabel();
				warning.setFont(new Font("Consolas", Font.PLAIN, 14));
				warning.setForeground(Color.RED);
				if (getFocusedAnswer() == null) {					
					if (editorState == EditorState.EDIT) {
						warning.setText("Do you want delete this QUESTION?");
						int dialogResult = JOptionPane.showConfirmDialog (null, warning, "Question deleting.", JOptionPane.YES_NO_OPTION);
						if (dialogResult == JOptionPane.YES_OPTION){							
							if (question != null) questionService.deleteQuestion(getQuestion());
							table.deleteQuestion(getQuestion());
							setVisible(false);
							dispose();
						}
					}
					if (editorState == EditorState.CREATE) {
						//TODO: check
						warning.setText("You have to select an existing object.");
						JOptionPane.showMessageDialog(null, warning);
					}
				} else {
					warning.setText("Do you want delete this ANSWER?");
					int dialogResult = JOptionPane.showConfirmDialog (null, warning, "Answer deleting.", JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION){
						//TODO: check
						if (editorState == EditorState.EDIT) {
							Answer a = answerService.findAnswer(getFocusedAnswer().getAnswer().getId());
							if (a != null) answerService.deleteAnswer(getFocusedAnswer().getAnswer());
						}
						deleteFocusedAnswer();
						setFocusedAnswer(null);
					}					
				}
			}
		});
		btnDelete.setBounds(364, 174, 76, 52);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnCancel.addActionListener(new ActionListener() {
			@Override
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
	
		JScrollPane aScroller = new JScrollPane(editorAnswer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		aScroller.setBounds(10, 118, 302, 34);
		panel.add(aScroller);
		
		JLabel lblVerity = new JLabel("Verity");
		lblVerity.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblVerity.setBounds(314, 102, 40, 14);
		panel.add(lblVerity);
		
		JSpinner spinner = new JSpinner();
		spinnerModel = new SpinnerNumberModel(100, 0, 100, 25);
		spinner.setModel(spinnerModel);
		spinner.setBounds(314, 118, 40, 34);		
		panel.add(spinner);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Question question = getQuestion();
				Answer a = new Answer();
				a.setAnswer(editorAnswer.getText());
				a.setTruth((Integer)spinnerModel.getValue());
				question.addAnswer(a);				
				setAnswers(question.getAnswers());
				editorAnswer.setText("");
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
		if (question == null) { 
			return createNewQuestion(editorQuestion.getText()); 
		}
		return question;
	}	

	public Question createNewQuestion(String text) {
		this.question = new Question();
		question.setQuestion(text);
		return question;		
	}
	
	public List<Answer> getAnswersFromModel() {
		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < listModel.getSize(); i++) {
			AnswerLabel label = (AnswerLabel) this.listModel.get(i);
			answers.add(label.getAnswer());
		}
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {		
		this.listModel.clear();
		for (Answer answer : answers) {
			AnswerLabel answerLabel = new AnswerLabel(answer);
			this.listModel.addElement(answerLabel);
		}
	}
	
	public void setAnswers(Set<Answer> answers) {		
		this.listModel.clear();
		for (Answer answer : answers) {
			AnswerLabel answerLabel = new AnswerLabel(answer);
			this.listModel.addElement(answerLabel);
		}
	}

	public void setFocusedAnswer(AnswerLabel a) {
		focusedAnswer = a;		
	}
	
	public void deleteFocusedAnswer() {
		this.listModel.removeElement(getFocusedAnswer());
	}
	
	public AnswerLabel getFocusedAnswer() {
		return focusedAnswer;
	}

	public EditorState getEditorState() {
		return editorState;
	}

	public void setEditorState(EditorState editorState) {
		this.editorState = editorState;
	}
	
}
