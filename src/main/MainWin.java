package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import FileParsing.fileListener;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import speech.inputProcessing;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainWin {

	private JFrame frame;
	private JTextField mainTextInput;
	public static List<fileListener> listFileListeners;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin window = new MainWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


	}

	/**
	 * Create the application.
	 */
	public MainWin() {
		initialize();
		setGuiFunctions();
		listFileListeners = new ArrayList<fileListener>();
		System.out.print(mainTextInput.getText());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.EAST);

		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.CENTER);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 10, 0, 0};
		gridBagLayout.rowHeights = new int[]{120, 26, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gridBagLayout);

		mainTextInput = new JTextField();
		GridBagConstraints gbc_mainTextInput = new GridBagConstraints();
		gbc_mainTextInput.insets = new Insets(0, 0, 5, 0);
		gbc_mainTextInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_mainTextInput.gridx = 2;
		gbc_mainTextInput.gridy = 1;
		panel_4.add(mainTextInput, gbc_mainTextInput);
		mainTextInput.setColumns(10);
	}

	/**Sets custom functions of the GUI*/
	public void setGuiFunctions() {
		mainTextInput.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {}
			public void removeUpdate(DocumentEvent e) {
				waitToAct(e);
			}
			public void insertUpdate(DocumentEvent e) {		
				waitToAct(e);
			}

			private void waitToAct(DocumentEvent e) {
				Document doc = (Document)e.getDocument();
				String temp = mainTextInput.getText();
				Timer t1 = new Timer();
				t1.schedule(
						new TimerTask() 
						{
							boolean noMisfire = true;
							public void run() 
							{
								if (temp.equals(mainTextInput.getText()))
								{
									determineKeywords(mainTextInput.getText());
								}
							}}, 2000);
			}
		});
	}

	/**Calls inputProcessins's comprehend method */
	public void determineKeywords(String input)
	{
		System.out.println(input);
		inputProcessing.comprehend(input);

	}
}
