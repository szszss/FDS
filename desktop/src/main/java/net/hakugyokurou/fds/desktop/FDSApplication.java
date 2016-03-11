package net.hakugyokurou.fds.desktop;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.parser.MathExpressionParser;
import net.hakugyokurou.fds.util.AnswerHelper;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;

public class FDSApplication {

	private JFrame frmFiniteDigitSummator;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JLabel lblResult;
	private MathExpression currentExpression;
	
	private ActionListener checkResult = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(currentExpression == null)
				return;
			try {
				double answer = AnswerHelper.parseSingleNumber(textField_1.getText());
				boolean correct = AnswerHelper.checkAnswer(answer, currentExpression.eval());
				if(correct)
				{
					int index = table.getSelectedRow();
					table.setValueAt(textField_1.getText(), index, 1);
					if(index < table.getRowCount() - 1)
						table.changeSelection(index + 1, 0, false, false);
					lblResult.setText("Correct");
				}
				else
				{
					lblResult.setText("Wrong");
				}
			} catch (Exception e2) {
				lblResult.setText(e2.getMessage());
			}
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (Exception e) {}
					FDSApplication window = new FDSApplication();
					window.frmFiniteDigitSummator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FDSApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFiniteDigitSummator = new JFrame();
		frmFiniteDigitSummator.setTitle("Finite Digit Summator");
		frmFiniteDigitSummator.setBounds(100, 100, 500, 700);
		frmFiniteDigitSummator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmFiniteDigitSummator.getContentPane().add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Question");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(5, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(5, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Answer");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.addActionListener(checkResult);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Check");
		btnNewButton.addActionListener(checkResult);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(5, 15, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		lblResult = new JLabel("");
		GridBagConstraints gbc_lblResult = new GridBagConstraints();
		gbc_lblResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult.gridx = 1;
		gbc_lblResult.gridy = 2;
		panel.add(lblResult, gbc_lblResult);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewButton, lblNewLabel, textField, lblNewLabel_1, textField_1, lblResult}));
		
		JPanel panel_1 = new JPanel();
		frmFiniteDigitSummator.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(frmFiniteDigitSummator);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = chooser.getSelectedFile();
						if(file.isFile() && file.exists())
						{
							clear();
							fillExpressions(MathExpressionParser.parseFile(file));
						}
					} catch (Exception e2) {
						lblResult.setText(e2.getMessage());
					}
				}
			}
		});
		panel_1.add(btnLoad);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Generator generator = new Generator();
				generator.setModal(true);
				generator.setVisible(true);
				if(generator.ok)
				{
					ArrayList<MathExpression> expressions = generator.expressions;
					clear();
					fillExpressions(expressions);
				}
				generator.dispose();
			}
		});
		panel_1.add(btnGenerate);
		
		JButton btnParse = new JButton("Parse");
		btnParse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Parser parser = new Parser();
				parser.setVisible(true);
				if(parser.ok)
				{
					ArrayList<MathExpression> expressions = parser.expressions;
					clear();
					fillExpressions(expressions);
				}
				parser.dispose();
			}
		});
		panel_1.add(btnParse);
		
		JScrollPane scrollPane = new JScrollPane();
		frmFiniteDigitSummator.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Question", "Answer"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsm.addListSelectionListener(new ListSelectionListener() {		
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = table.getSelectedRow();
				if(index < 0)
					return;
				currentExpression = (MathExpression)table.getValueAt(table.getSelectedRow(), 0);
				textField.setText(currentExpression.toString());
				textField_1.setText("");
				lblResult.setText("");
			}
		});
		scrollPane.setViewportView(table);
	}
	
	@SuppressWarnings("serial")
	private void fillExpressions(ArrayList<MathExpression> expressions) {
		Object[][] objs = new Object[expressions.size()][2];
		for(int i = 0, size = expressions.size(); i < size; i++)
		{
			objs[i] = new Object[] {expressions.get(i), null};
		}
		table.setModel(new DefaultTableModel(
			objs,
			new String[] {
				"Question", "Answer"
			}
		){
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
	}

	private void clear() {
		table.clearSelection();
		currentExpression = null;
		textField.setText("");
		textField_1.setText("");
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Question", "Answer"
			}
		));
	table.getColumnModel().getColumn(0).setPreferredWidth(300);
	}
}
