package com.my.app.components.questions;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class QuestionTable extends JTable {

	private static final long serialVersionUID = 1194556790037959407L;
	private DefaultTableModel model;	
	
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

    	setModel(model);
    	
        TableColumn column0 = getColumnModel().getColumn(0);
        column0.setMinWidth(200);
        column0.setPreferredWidth(200);
        TableColumn column1 = getColumnModel().getColumn(1);
        column1.setMinWidth(24);
        column1.setMaxWidth(24);
        column1.setPreferredWidth(24);
    	QuestionTableEditor editor = new QuestionTableEditor();
        column1.setCellEditor(editor);
        column1.setCellRenderer(editor);
        TableColumn column2 = getColumnModel().getColumn(2);
        column2.setMinWidth(24);
        column2.setMaxWidth(24);
        column2.setPreferredWidth(24);
    }

	public DefaultTableModel getModel() {
		return model;
	}
}
