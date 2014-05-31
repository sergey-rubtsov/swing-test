package com.my.app.components.questions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import com.my.app.components.questions.DeleteColumn.DeleteButton;
import com.my.app.domain.Answer;
import com.my.app.domain.Question;
import com.my.app.service.AnswerService;
import com.my.app.service.QuestionService;

@Configurable(preConstruction = true)
@Transactional
public class QuestionTable extends JTable implements ActionListener {

	@Autowired
	QuestionService questionService;

	@Autowired
	AnswerService answerService;

	private static final long serialVersionUID = 1194556790037959407L;
	DefaultTableModel model;
	private List<Question> questions;
	protected static final String DELETE = "d";

	public QuestionTable() {
		super();
		createTable();
	}

	public void createTable() {
		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 0)
					return false;
				return true;
			}
		};

		model.addColumn("Question");
		model.addColumn("Edit");
		model.addColumn("Delete");

		setTableHeader(null);
		setBorder(new javax.swing.border.EmptyBorder(10, 10, 10, 10));
		setModel(model);

		TableColumn column0 = getColumnModel().getColumn(0);
		QuestionColumn listColumn = new QuestionColumn();
		column0.setCellRenderer(listColumn);
		column0.setMinWidth(200);
		column0.setPreferredWidth(200);
		TableColumn column1 = getColumnModel().getColumn(1);
		column1.setMinWidth(24);
		column1.setMaxWidth(24);
		column1.setPreferredWidth(24);
		EditColumn editor = new EditColumn();
		column1.setCellEditor(editor);
		column1.setCellRenderer(editor);
		TableColumn column2 = getColumnModel().getColumn(2);
		DeleteColumn deleter = new DeleteColumn(this);
		column2.setCellEditor(deleter);
		column2.setCellRenderer(deleter);
		column2.setMinWidth(24);
		column2.setMaxWidth(24);
		column2.setPreferredWidth(24);
	}

	@Override
	public DefaultTableModel getModel() {
		return model;
	}

	public void updateRowHeights() {
		try {
			for (int row = 0; row < getRowCount(); row++) {
				int rowHeight = getRowHeight();
				for (int column = 0; column < getColumnCount(); column++) {
					Component comp = prepareRenderer(
							getCellRenderer(row, column), row, column);
					rowHeight = Math.max(rowHeight,
							comp.getPreferredSize().height);
				}
				setRowHeight(row, rowHeight);
			}
		} catch (ClassCastException e) {
		}
	}

	public void setQuestions(List<Question> questions) {
		for (final Question question : questions) {
			Object[] row = { question, question, question };
			this.getModel().addRow(row);
		}
		this.updateRowHeights();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (DELETE.equals(e.getActionCommand())) {
			DeleteButton button = (DeleteColumn.DeleteButton) e.getSource();
			questionService.deleteQuestion(button.getQuestion());
		}
	}

	private void initDB() {
		Question q1 = new Question("Test1");
		Answer answer = new Answer("YESS", 20);
		q1.addAnswer(answer);
		questionService.saveQuestion(q1);
		answerService.saveAnswer(answer);
		questionService.saveQuestion(new Question("Test2"));
		questionService.saveQuestion(new Question("Test3"));
		questionService.saveQuestion(new Question("Test4"));
		search();
	}

	public void search() {
		questions = questionService.findAllQuestions();
		if (questions.size() > 0) {
			setQuestions(questions);
		}
	}

	public void search(String keyword) {
		questions = questionService.searchQuestion(keyword);
		if (questions.size() > 0) {
			setQuestions(questions);
		}
	}
	
	@Deprecated
	private void refreshTable() {
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(0);
		}
		search();
	}
}
