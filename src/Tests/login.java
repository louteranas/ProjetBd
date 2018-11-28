package Tests;

import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;
import requetes.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JSeparator;

public class login {

	public JFrame frame;
	private JTextField email;

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 266);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(177, 12, 134, 46);
		frame.getContentPane().add(panel);

		JLabel lblLeCoincoin = new JLabel("Le coincoin");
		panel.add(lblLeCoincoin);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 107, 96, 25);
		frame.getContentPane().add(panel_1);

		JLabel lblEmail = new JLabel("Email");
		panel_1.add(lblEmail);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(177, 105, 134, 27);
		frame.getContentPane().add(panel_2);

		email = new JTextField();
		panel_2.add(email);
		email.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(177, 184, 139, 25);
		frame.getContentPane().add(panel_3);

		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(email.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas inserer votre email !");
				}
				else {
					DataBaseAccess data = new DataBaseAccess();
					try {
						IdentificationUtilisateur user = new IdentificationUtilisateur(data, email.getText());
						//System.out.println("user.VerificationUser() = "+user.VerificationUser());
						if(!user.VerificationUser()) {
							choixDebut windowDebut = new choixDebut(data,email.getText());
							windowDebut.frame.setVisible(true);
							frame.dispose();
						}
						else {
							signup window = new signup(data);
							window.frame.setVisible(true);
							//frame.setVisible(false);
							frame.dispose();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "sql error");
						e.printStackTrace();
					}
				}
			}
		});
		panel_3.add(btnLogin);

	}
}
