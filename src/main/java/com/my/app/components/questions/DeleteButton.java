package com.my.app.components.questions;

import java.awt.Font;

import javax.swing.JButton;

public class DeleteButton extends JButton {

	private static final long serialVersionUID = -7455470258663698891L;
	protected static final String DELETE = "d";

	public DeleteButton(DeleteAction deleteAction) {
		super(deleteAction);
		setText(DELETE);
		setFont(new Font("Consolas", Font.PLAIN, 12));
		setOpaque(true);
		setActionCommand(DELETE);
		setBorderPainted(false);
		setBorder(null);
	}
}
