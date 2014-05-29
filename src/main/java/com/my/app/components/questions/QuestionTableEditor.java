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

public class QuestionTableEditor extends AbstractCellEditor implements TableCellEditor, ActionListener, TableCellRenderer {

	private static final long serialVersionUID = -6744877611120460258L;
	protected static final String EDIT = "e";
	private JButton button;
	private EditFrame edit;
	private Question question;

	public QuestionTableEditor() {
        button = new JButton(EDIT);
        button.setFont(new Font("Consolas", Font.PLAIN, 6));
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
            //The user has clicked the cell, so
            //bring up the dialog.
            button.setBackground(Color.cyan);
        	edit = new EditFrame(this.question);
    		edit.setVisible(true);

            fireEditingStopped(); //Make the renderer reappear.

        } else { //User pressed dialog's "OK" button.
            //currentColor = colorChooser.getColor();
        }
		
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		question = (Question) value;
		return button;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		question = (Question) value;
		return button;
	}	
	
	/*	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		switch (column) {
		case 0:
			add(new JLabel(value.toString()));
			return this;
		case 1:
			JButton edit = new JButton("E");
			edit.setFont(new Font("Consolas", Font.PLAIN, 12));
			edit.setBackground(new Color(0, 0, 0, 0));
			edit.setForeground(Color.green);
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					EditFrame edit = new EditFrame("");
					edit.setVisible(true);
				}
			});
			add(edit);
			return this;
		case 2:
			JButton delete = new JButton("D");
			delete.setFont(new Font("Consolas", Font.PLAIN, 12));
			delete.setForeground(Color.red);
			delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});
			add(delete);
			return this;
		default:
			return this;
		}
	}*/
}
