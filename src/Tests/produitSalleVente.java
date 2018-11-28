package Tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Actions.Actions;
import connexion.DataBaseAccess;

public class produitSalleVente {

	public JFrame frame;
	private static String email;
	private static DataBaseAccess data;
	private static int idSalleVente;

	/**
	 * Create the application.
	 */

	public produitSalleVente(DataBaseAccess data, String email, int idSalleVente, String nomSalleVente) {
		produitSalleVente.data = data;
		produitSalleVente.email = email;
      	produitSalleVente.idSalleVente = idSalleVente;
		initialize(nomSalleVente);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nomSalleVente) {
		try {
		Actions act = new Actions(email, data);


		Vector<String> produits = act.produitsSalle(produitSalleVente.idSalleVente).getPoduitSalle();
		int nbr_produit = produits.size();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 90*(nbr_produit + 1) );
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		

		JLabel lblProduitSalleVente = new JLabel(nomSalleVente);
		lblProduitSalleVente.setBounds(195, 12, 218, 15);
		frame.getContentPane().add(lblProduitSalleVente);
		
		JButton btnNewButton0 = new JButton("retour");
		btnNewButton0.setBounds(10, 12, 100, 25);
		frame.getContentPane().add(btnNewButton0);

		for(int j =0; j < nbr_produit; j++) {
			String nom = produits.elementAt(j);
			JButton btnNewButton1 = new JButton(nom);
			btnNewButton1.setBounds(45, 80+40*j, 350, 25);	
			btnNewButton1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						//produitSalleVente window = new produitSalleVente(data, email, id, salle);
						//window.frame.setVisible(true);
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

	
	

