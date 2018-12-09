package Graphics;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import connexion.DataBaseAccess;

public class choixAdmin {

	public JFrame frame;
	private DataBaseAccess data;
	private String email;


	/**
	 * Create the application.
	 */
	public choixAdmin(DataBaseAccess data, String email) {
		this.data = data;
		this.email = email;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 230);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblQueVoulezVous = new JLabel("Que voulez vous faire ?");
		lblQueVoulezVous.setBounds(230, 24, 254, 23);
		frame.getContentPane().add(lblQueVoulezVous);
		
		JButton btnAjouter = new JButton("Ajouter salle de vente");
		btnAjouter.setBounds(34, 89, 190, 25);
		btnAjouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					ajoutSalle window = new ajoutSalle(data, email, frame);
					window.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnAjouter);
		
		JButton btnDeco = new JButton("Deconnexion");
		btnDeco.setBounds(235, 160, 125, 25);
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
		
		
		JButton btnHistorique = new JButton("historique de vente");
		btnHistorique.setBounds(392, 89, 190, 25);
		btnHistorique.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					sallesVente window = new sallesVente(data, email, frame, "historique");
					window.frame.setVisible(true);
					frame.setVisible(false);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnHistorique);
	}

}
