package Tests;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import connexion.Actions;
import connexion.DataBaseAccess;
import requetes.SimpleQuery;

public class produitSalleVente {

	private JFrame frame;
	private static String email;
	private static DataBaseAccess data;
	private static int idSalleVente;
	
	/**
	 * Create the application.
	 */
	
	public produitSalleVente(DataBaseAccess data, String email, int idSalleVente, String nomSalleVente) {
		this.data = data;
		this.email = email;
		this.idSalleVente = idSalleVente;
		initialize(nomSalleVente);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nomSalleVente) {
		try {
		Actions act = new Actions(email, data);
		
		
		Vector<String> produits = act.affichageSallesDeVente().getSallesVente();
		int nbr_ligne = produits.size()%2;
		int nbr_produit = produits.size();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 90*(nbr_ligne +1) );
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProduitSalleVente = new JLabel(nomSalleVente);
		lblProduitSalleVente.setBounds(165, 12, 218, 15);
		frame.getContentPane().add(lblProduitSalleVente);
		
		
		for(int j =0; j <= nbr_produit; j=j+2) {
			String nom = produits.elementAt(j);
			JButton btnNewButton1 = new JButton(nom);
			btnNewButton1.setBounds(45, 40+25*j, 117, 25);
			frame.getContentPane().add(btnNewButton1);
			if(j+1<=nbr_produit-1) {
				nom = produits.elementAt(j+1);
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

	
	

