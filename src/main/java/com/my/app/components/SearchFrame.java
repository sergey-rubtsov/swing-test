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
public class SearchFrame extends JFrame implements TableModelListener {

	private static final long serialVersionUID = 595008560407711863L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnAdd;
	private QuestionTable table;
	
    @Autowired
    QuestionService questionService;
    
    @Autowired
    AnswerService answerService;
	
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
		table = new QuestionTable();
		table.getModel().addTableModelListener(this);
		JScrollPane scroller = new JScrollPane(table);
		scroller.setPreferredSize(new Dimension(400, 180));	
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
	
	private void initDB() {
		Question q1 = new Question("Test1");
		Answer answer = new Answer("YESS", 20);
		q1.addAnswer(answer);
		questionService.saveQuestion(q1);
		answerService.saveAnswer(answer);
		questionService.saveQuestion(new Question("Test2"));
		questionService.saveQuestion(new Question("Test3"));
		questionService.saveQuestion(new Question("Test4"));
		this.table.setQuestions(questionService.findAllQuestions());
	}

	private void onChangeValue() {
		table.search(textField.getText());
		if (table.getModel().getRowCount() == 0) {
			btnAdd.setEnabled(true);
		}
/*		String keywords = textField.getText();
		List<Question> q = questionService.searchQuestion(keywords);
		if (q.size() > 0) {
			this.table.setQuestions(q);
		} else {
			btnAdd.setEnabled(true);
		}*/		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
/*		if (e.getType() == TableModelEvent.DELETE) {
			onChangeValue();
		}*/
	}
}
