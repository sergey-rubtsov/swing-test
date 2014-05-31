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
import com.my.app.domain.Question;

public class EditColumn extends AbstractCellEditor implements TableCellEditor, ActionListener, TableCellRenderer {

	private static final long serialVersionUID = -6744877611120460258L;
	protected static final String EDIT = "e";
	private JButton button;
	private EditFrame edit;
	private Question question;

	public EditColumn() {
        button = new JButton(EDIT);
        button.setFont(new Font("Consolas", Font.PLAIN, 12));
        button.setOpaque(true);
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        button.setBorder(null);
    }

	@Override
	public Object getCellEditorValue() {
		return question;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            button.setBackground(Color.cyan);
        	edit = new EditFrame(this.question);
    		edit.setVisible(true);
            fireEditingStopped();
        }		
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		try {
			question = (Question) value;
		} catch (ClassCastException e) {
			return null;
		}
		return button;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		try {
			question = (Question) value;
		} catch (ClassCastException e) {
			return null;
		}
		return button;
	}
}
