package Tests;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;

import connexion.Actions;
import connexion.DataBaseAccess;
import requetes.SimpleQuery;

import javax.swing.JButton;

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
		int nbr_ligne = salles.size()%2;
		int nbr_salle = salles.size();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 90*(nbr_ligne +1) );
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSallesDeVente = new JLabel("Salles de vente");
		lblSallesDeVente.setBounds(165, 12, 218, 15);
		frame.getContentPane().add(lblSallesDeVente);
		
		
		for(int j =0; j <= nbr_salle; j=j+2) {
			String nom = idSalles.elementAt(j) + salles.elementAt(j) + idEnchereSalles.elementAt(j);
			JButton btnNewButton1 = new JButton(nom);
			btnNewButton1.setBounds(45, 40+25*j, 117, 25);
			frame.getContentPane().add(btnNewButton1);
			if(j+1<=nbr_salle-1) {
				nom = salles.elementAt(j+1);
				JButton btnNewButton2 = new JButton(nom);
				btnNewButton2.setBounds(285, 40+ 25*j, 117, 25);
				frame.getContentPane().add(btnNewButton2);
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
