package Graphics;

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
		frame.setBounds(100, 100, 560, 230);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblQueVoulezVous = new JLabel("Que voulez vous faire ?");
		lblQueVoulezVous.setBounds(200, 24, 254, 23);
		frame.getContentPane().add(lblQueVoulezVous);
		
		JButton btnVendre = new JButton("Vendre");
		btnVendre.setBounds(34, 89, 117, 25);
		btnVendre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					sallesVente window = new sallesVente(data, email, frame, "vente");
					window.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnVendre);
		
		JButton btnDeco = new JButton("Deconnexion");
		btnDeco.setBounds(200, 140, 125, 25);
		btnDeco.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					data.getConn().close();
					login window = new login();
					window.frame.setVisible(true);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnDeco);
		
		JButton btnAcheter = new JButton("Acheter");
		btnAcheter.setBounds(392, 89, 117, 25);
		btnAcheter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					sallesVente window = new sallesVente(data, email, frame, "achat");
					window.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnAcheter);
	}

}
