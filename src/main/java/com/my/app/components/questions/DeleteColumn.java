package com.my.app.components.questions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import com.my.app.domain.Question;
import com.my.app.service.QuestionService;

public class DeleteColumn extends AbstractCellEditor implements
		TableCellEditor, ActionListener, TableCellRenderer {

	private static final long serialVersionUID = 1823620182831897325L;

	protected static final String DELETE = "d";
	private DeleteButton button;
	QuestionTable table;
	int row;
	private Question question = null;

	public class DeleteButton extends JButton {
		private Question question;

		private int row;

		protected static final String DELETE = "d";

		public DeleteButton(String text) {
			this();
			setText(text);
		}

		public DeleteButton() {
			super();
			setText(DELETE);
			setFont(new Font("Consolas", Font.PLAIN, 12));
			setOpaque(true);
			setActionCommand(DELETE);
			// addActionListener(this);
			setBorderPainted(false);
			setBorder(null);
		}

		public Question getQuestion() {
			return question;
		}

		public void setQuestion(Question question) {
			this.question = question;
		}

	}

	public DeleteColumn(QuestionTable table) {
		button = new DeleteButton(DELETE);
		button.addActionListener(table);
		button.addActionListener(this);
		/*
		 * button = new JButton(DELETE); button.setFont(new Font("Consolas",
		 * Font.PLAIN, 12)); button.setOpaque(true);
		 * button.setActionCommand(DELETE); button.addActionListener(this);
		 * button.setBorderPainted(false); button.setBorder(null);
		 */
	}

	@Override
	public Object getCellEditorValue() {
		return question;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (DELETE.equals(e.getActionCommand())) {
			button.setBackground(Color.cyan);
			try {
				// int x = qTable.convertColumnIndexToModel(row);
				// int y = qTable.convertColumnIndexToView(row);
				//table.getModel().removeRow(row);
				rowDelete(row);
			} catch (Exception tableEx) {
			}
			fireEditingStopped();
		}
	}

	private void rowDelete(int row) {
		if (table.getCellEditor() != null)
			((TableCellEditor) table.getCellEditor()).stopCellEditing();
		if ((row >= 0) && (table.getRowCount() > 1)) {
			DefaultTableModel tm = (DefaultTableModel) table.getModel();
			tm.removeRow(row);
			table.repaint();
		}
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		try {
			this.table = (QuestionTable) table;
			this.row = row;
			question = (Question) value;
			button.setQuestion(question);
		} catch (ClassCastException qEx) {
			return null;
		}
		return button;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		try {
			this.table = (QuestionTable) table;
			this.row = row;
			question = (Question) value;
			button.setQuestion(question);
		} catch (ClassCastException qEx) {
			return null;
		}
		return button;
	}

}
