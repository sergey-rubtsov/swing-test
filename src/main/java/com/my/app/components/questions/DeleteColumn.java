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

import com.my.app.domain.Question;

public class DeleteColumn extends AbstractCellEditor implements
		TableCellEditor, ActionListener, TableCellRenderer {

	private static final long serialVersionUID = 1823620182831897325L;

	protected static final String DELETE = "d";
	private DeleteButton button;
	QuestionTable table;
	int row;
	private Question question = null;

	public class DeleteButton extends TableButton {

		private static final long serialVersionUID = -7455470258663698891L;

		public DeleteButton() {
			super();
			setText(DeleteColumn.DELETE);
			setFont(new Font("Consolas", Font.PLAIN, 12));
			setOpaque(true);
			setActionCommand(DeleteColumn.DELETE);
			setBorderPainted(false);
			setBorder(null);
		}
	}

	public DeleteColumn(QuestionTable table) {
		button = new DeleteButton();
		button.addActionListener(table);
		button.addActionListener(this);
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
				rowDelete(row);
			} catch (Exception tableEx) {
			}
			fireEditingStopped();
		}
	}

	private void rowDelete(int row) {
		if (table.getCellEditor() != null)
			table.getCellEditor().stopCellEditing();
		if ((row >= 0) && (table.getRowCount() > 1)) {
			DefaultTableModel tm = table.getModel();
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
