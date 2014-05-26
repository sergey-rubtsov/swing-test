package com.my.app.components;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.my.app.domain.Question;
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
	private DefaultListModel listModel;	
	
    @Autowired
    QuestionService questionService;	
	
	public SearchFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmImport = new JMenuItem("Import");
		mnFile.add(mntmImport);
		
		JMenuItem mntmExport = new JMenuItem("Export");
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
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditFrame edit = new EditFrame(textField.getText());
				edit.setVisible(true);
			}
		});
		panelTop.add(btnAdd);
		
		JPanel panelBottom = new JPanel();

		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		listModel = new DefaultListModel();
		JList list = new JList(listModel);
		list.setLayoutOrientation(JList.VERTICAL);
		
		list.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(350, 150));
		
		list.setCellRenderer(new QuestionCellRenderer());

		panelBottom.add(listScroller);
		
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
		initDB();
	}
	
	private void initDB() {
		questionService.saveQuestion(new Question("Test1"));
		questionService.saveQuestion(new Question("Test2"));
		questionService.saveQuestion(new Question("Test3"));
		questionService.saveQuestion(new Question("Test4"));
		setQuestions(questionService.findAllQuestions());
	}

	public void setQuestions(List<Question> questions) {
		this.listModel.clear();
		for (Question question : questions) {
			this.listModel.addElement(question);
		}
	}

	private void onChangeValue() {
		String keywords = textField.getText();
		List<Question> q = questionService.searchQuestion(keywords);
		if (q.size() > 0) {
			setQuestions(q);
		} else {
			btnAdd.setEnabled(true);
		}		
	}
}
