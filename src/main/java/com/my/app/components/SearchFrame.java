package com.my.app.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.my.app.components.questions.QuestionTable;
import com.my.app.domain.Answer;
import com.my.app.domain.Question;
import com.my.app.service.AnswerService;
import com.my.app.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable(preConstruction=true)
@Transactional
public class SearchFrame extends JFrame {

	private static final long serialVersionUID = 595008560407711863L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnAdd;
	private JButton btnShow;
	private JButton btnClear;
	private QuestionTable table;
	
	public SearchFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 420);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmImport = new JMenuItem("Import db");
		mntmImport.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				importDB();
			}
		});
		mnFile.add(mntmImport);
		
		JMenuItem mntmExport = new JMenuItem("Export db");
		mntmExport.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				exportDB();
			}
		});
		mnFile.add(mntmExport);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setFont(new Font("Consolas", Font.PLAIN, 14));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panelTop.add(textField);
		textField.setColumns(40);
		textField.setEditable(true);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditFrame edit = new EditFrame(textField.getText());
				clearSearchField();
				edit.setVisible(true);
			}
		});
		panelTop.add(btnAdd);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearSearchField();			
			}
		});
		panelTop.add(btnClear);		
		
		btnShow = new JButton("All");
		btnShow.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				table.showAll();				
			}
		});
		panelTop.add(btnShow);		
		
		JPanel panelBottom = new JPanel();

		contentPane.add(panelBottom, BorderLayout.SOUTH);
		table = new QuestionTable();
		JScrollPane scroller = new JScrollPane(table);
		scroller.setPreferredSize(new Dimension(636, 300));	
		panelBottom.add(scroller);
		
		textField.getDocument().addDocumentListener(new DocumentListener() {			
			@Override
			public void removeUpdate(DocumentEvent e) {
				onChangeValue();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				onChangeValue();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				onChangeValue();
			}
		});
	}
	
	public void clearSearchField() {
		textField.setText("");
	}
	
	private void exportDB() {
		
	}
	
	private void importDB() {
		
	}
	
	private void onChangeValue() {
		table.search(textField.getText());	
	}
}
