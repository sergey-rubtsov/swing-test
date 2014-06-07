package com.my.app.components.questions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.my.app.components.EditFrame;
import com.my.app.components.questions.DeleteColumn.DeleteButton;
import com.my.app.domain.Question;

public class EditColumn extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

	private static final long serialVersionUID = -5677136980819247871L;
	
	public EditColumn() {
    }	

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		EditButton button = null;
		try {
			button = (EditButton) value;
		} catch (ClassCastException e) {
			return null;
		}		
		return button;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		EditButton button = null;
		try {
			button = (EditButton) value;
		} catch (ClassCastException e) {
			return null;
		}		
		return button;
	}

	@Override
	public Object getCellEditorValue() {
		return false;
	}
}
