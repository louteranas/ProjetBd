package Tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import connexion.*;

public class sallesVente {

	public JFrame frame;
	private static String email;
	private static DataBaseAccess data;

	
	/**
	 * Create the application.
	 */
	public sallesVente(DataBaseAccess data, String email) {
		this.data = data;
		this.email = email;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		Actions act = new Actions(email, data);
		
		
		Vector<String> salles = act.affichageSallesDeVente().getNomSallesVente();
		Vector<Integer> idSalles = act.affichageSallesDeVente().getIdSallesVente();
		Vector<Integer> idEnchereSalles = act.affichageSallesDeVente().getIdEnchereSallesVente();
		int nbr_salle = salles.size();
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 90*(nbr_salle-1) );
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSallesDeVente = new JLabel("Salles de vente");
		lblSallesDeVente.setBounds(250, 12, 400, 15);
		frame.getContentPane().add(lblSallesDeVente);
		
		JButton btnNewButton0 = new JButton("retour");
		btnNewButton0.setBounds(10, 12, 100, 25);
		btnNewButton0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		frame.getContentPane().add(btnNewButton0);
		
		
		for(int j =0; j < nbr_salle; j++) {
			String nom = salles.elementAt(j) + act.typeEnchere(idEnchereSalles.elementAt(j)).getTypeEnchere();
			int id = idEnchereSalles.elementAt(j);
			String salle = salles.elementAt(j);
			JButton btnNewButton1 = new JButton(nom);
			btnNewButton1.setBounds(45, 80+40*j, 560, 25);
			btnNewButton1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						produitSalleVente window = new produitSalleVente(data, email, id, salle);
						window.frame.setVisible(true);
						frame.dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			frame.getContentPane().add(btnNewButton1);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
