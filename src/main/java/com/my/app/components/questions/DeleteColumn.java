package com.my.app.components.questions;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class DeleteColumn extends AbstractCellEditor implements
		TableCellEditor, TableCellRenderer {

	private static final long serialVersionUID = 1823620182831897325L;

	QuestionTable table;

	@Override
	public Object getCellEditorValue() {
		return false;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		DeleteButton button = null;
		try {
			button = (DeleteButton) value;
		} catch (ClassCastException e) {
			return null;
		}
		return button;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		DeleteButton button = null;
		try {
			button = (DeleteButton) value;
		} catch (ClassCastException e) {
			return null;
		}
		return button;
	}

}
