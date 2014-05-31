package com.my.app.components.questions;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import com.my.app.domain.Question;

public class QuestionsTableModel extends DefaultTableModel {
	
	private static final long serialVersionUID = -6739326234267544303L;
	HashMap<String, Value> map = new HashMap<String, Value>();
	
	private class Value {
		Object[] row;
		int rowNumber;
		public Value(Object[] row, int rowNumber) {
			super();
			this.row = row;
			this.rowNumber = rowNumber;
		}
		public Object[] getRow() {
			return row;
		}
		public void setRow(Object[] row) {
			this.row = row;
		}
		public int getRowNumber() {
			return rowNumber;
		}
		public void setRowNumber(int rowNumber) {
			this.rowNumber = rowNumber;
		}
	} 

	public void addRow(String id, Object[] row) {
		Value value = new Value(row, -1);
		map.put(id, value);
		addRow(row);
	}
	
	public void removeRow(String questionId) {
		index();
		Value value = map.get(questionId);
		removeRow(value.getRowNumber());
		map.remove(questionId);
		index();
	}
	
	private void index() {
		for (int i = 0; i < getRowCount(); i++) {
			Question q = (Question) getValueAt(i, 0);
			String key = q.getId();
			Value value = map.get(key);
			value.setRowNumber(i);
		}
	}

}
