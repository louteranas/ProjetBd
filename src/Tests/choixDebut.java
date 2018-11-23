package Tests;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;
import requetes.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;


public class choixDebut {

	public JFrame frame;
	private DataBaseAccess data;
	private String email;


	/**
	 * Create the application.
	 */
	public choixDebut(DataBaseAccess data, String email) {
		this.data = data;
		this.email = email;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblQueVoulezVous = new JLabel("Que voulez vous faire ?");
		lblQueVoulezVous.setBounds(134, 24, 254, 23);
		frame.getContentPane().add(lblQueVoulezVous);
		
		JButton btnVendre = new JButton("Vendre");
		btnVendre.setBounds(34, 89, 117, 25);
		btnVendre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().add(btnVendre);
		
		
		JButton btnAcheter = new JButton("Acheter");
		btnAcheter.setBounds(282, 89, 117, 25);
		btnAcheter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					sallesVente window = new sallesVente(data, email);
					window.frame.setVisible(true);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnAcheter);
	}

}
