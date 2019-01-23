package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MailHelperGUI extends MailHelper{

	private JFrame frame;
	private JTextField mailAdresseGUI;
	private JTextField mailBetreffGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailHelperGUI window = new MailHelperGUI();
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
	public MailHelperGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBounds(0, 0, 582, 358);
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(null);
		
		mailAdresseGUI = new JTextField();
		mailAdresseGUI.setBounds(12, 13, 250, 40);
		panelMain.add(mailAdresseGUI);
		mailAdresseGUI.setColumns(10);
		
		mailBetreffGUI = new JTextField();
		mailBetreffGUI.setColumns(10);
		mailBetreffGUI.setBounds(12, 66, 250, 40);
		panelMain.add(mailBetreffGUI);
		
		final JTextArea mailTextGUI = new JTextArea();
		mailTextGUI.setBounds(12, 119, 400, 225);
		panelMain.add(mailTextGUI);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					composeEmail(mailAdresseGUI.getText(), mailBetreffGUI.getText(), mailTextGUI.getText(), null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Done!");
			}
		});
		btnSubmit.setBounds(473, 13, 97, 25);
		panelMain.add(btnSubmit);
	}
}
