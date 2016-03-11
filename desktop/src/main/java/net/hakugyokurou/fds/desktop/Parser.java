package net.hakugyokurou.fds.desktop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.parser.MathExpressionParser;

import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dialog.ModalityType;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
class Parser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	ArrayList<MathExpression> expressions = new ArrayList<MathExpression>();
	boolean ok = false;
	private JTextArea textArea;
	private JLabel lblResut;
	private JButton okButton;
	
	private ActionListener parseAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				MathExpression mathExpression = MathExpressionParser.parseLine(new StringReader(textField.getText()));
				String newLine = textArea.getText().equals("") ? "" : "\n";
				textArea.setText(textArea.getText() + newLine + mathExpression);
				expressions.add(mathExpression);
				textField.setText("");
				lblResut.setText("");
				okButton.setEnabled(true);
			} catch (Exception e2) {
				lblResut.setText(e2.getMessage());
			}
		}
	};

	/**
	 * Create the dialog.
	 */
	public Parser() {
		setResizable(false);
		setTitle("Parser");
		setModalityType(ModalityType.TOOLKIT_MODAL);
		setModal(true);
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JButton btnParse = new JButton("Parse");
				btnParse.addActionListener(parseAction);
				GridBagConstraints gbc_btnParse = new GridBagConstraints();				
				gbc_btnParse.gridx = 1;
				gbc_btnParse.gridy = 0;
				panel.add(btnParse, gbc_btnParse);
			}
			{
				textField = new JTextField();
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.insets = new Insets(0, 0, 0, 5);
				gbc_textField.gridx = 0;
				gbc_textField.gridy = 0;
				textField.addActionListener(parseAction);
				panel.add(textField, gbc_textField);
				textField.setColumns(10);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				textArea = new JTextArea();
				textArea.setEditable(false);
				scrollPane.setViewportView(textArea);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				lblResut = new JLabel("");
				buttonPane.add(lblResut);
			}
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ok = true;
						setVisible(false);
					}
				});
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
