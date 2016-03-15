package net.hakugyokurou.fds.desktop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.generator.MathExpressionGenerator;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
class Generator extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ArrayList<MathExpression> expressions = new ArrayList<MathExpression>();
	boolean ok = false;
	private JRadioButton rdbtnEasy;
	private JRadioButton rdbtnNormal;
	private JRadioButton rdbtnHard;
	private JRadioButton rdbtnLunatic;
	private JSpinner spinner;
	private JTextArea textArea;
	private JButton okButton;

	public Generator() {
		setResizable(false);
		setModal(true);
		setModalityType(ModalityType.TOOLKIT_MODAL);
		setTitle("Expression Generator");
		setBounds(100, 100, 600, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			ButtonGroup group = new ButtonGroup();
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				rdbtnEasy = new JRadioButton("Easy");
				group.add(rdbtnEasy);
				panel.add(rdbtnEasy);
			}
			{
				rdbtnNormal = new JRadioButton("Normal");
				rdbtnNormal.setSelected(true);
				group.add(rdbtnNormal);
				panel.add(rdbtnNormal);
			}
			{
				rdbtnHard = new JRadioButton("Hard");
				group.add(rdbtnHard);
				panel.add(rdbtnHard);
			}
			{
				rdbtnLunatic = new JRadioButton("Lunatic");
				group.add(rdbtnLunatic);
				panel.add(rdbtnLunatic);
			}
			{
				JLabel lblCount = new JLabel("Count:");
				panel.add(lblCount);
			}
			{
				spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(20, 1, 100, 1));
				panel.add(spinner);
			}
			{
				JButton btnGege = new JButton("Generate");
				btnGege.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(rdbtnEasy.isSelected()) {
							expressions = MathExpressionGenerator.generateEasy(((Number)spinner.getValue()).intValue());
						} else if(rdbtnNormal.isSelected()) {
							expressions = MathExpressionGenerator.generateNormal(((Number)spinner.getValue()).intValue());
						} else if(rdbtnHard.isSelected()) {
							expressions = MathExpressionGenerator.generateHard(((Number)spinner.getValue()).intValue());
						} else if(rdbtnLunatic.isSelected()) {
							expressions = MathExpressionGenerator.generateLunatic(((Number)spinner.getValue()).intValue());
						}
						StringBuilder sb = new StringBuilder();
						for(MathExpression expr : expressions) 
						{
							sb.append(expr).append("\n");
						}
						textArea.setText(sb.toString());
						okButton.setEnabled(true);
					}
				});
				panel.add(btnGege);
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
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ok = true;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
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
