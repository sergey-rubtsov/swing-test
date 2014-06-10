package com.my.app.components.questions;

import java.awt.Font;

import javax.swing.JButton;

public class EditButton extends JButton {

	private static final long serialVersionUID = -6744877611120460258L;
	protected static final String EDIT = "e";

	public EditButton(EditAction editAction) {
		super(editAction);
		setText("e");
		setFont(new Font("Consolas", Font.PLAIN, 12));
		setOpaque(true);
		setBorderPainted(false);
		setBorder(null);
	}
}
