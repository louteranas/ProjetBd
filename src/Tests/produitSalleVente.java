package Tests;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import connexion.Actions;
import connexion.DataBaseAccess;
import requetes.SimpleQuery;

public class produitSalleVente {

	public JFrame frame;
	private static String email;
	private static DataBaseAccess data;
	private static int idSalleVente;

	/**
	 * Create the application.
	 * @param frame2 
	 */

	public produitSalleVente(DataBaseAccess data, String email, int idSalleVente, String nomSalleVente, JFrame parentFrame) {
		produitSalleVente.data = data;
		produitSalleVente.email = email;
      	produitSalleVente.idSalleVente = idSalleVente;
		initialize(nomSalleVente, parentFrame);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nomSalleVente, JFrame parentFrame) {
		try {
		Actions act = new Actions(email, data);


		Vector<String> produits = act.produitsSalle(produitSalleVente.idSalleVente).getProduitSalle();
		Vector<Integer> idProduit = act.produitsSalle(produitSalleVente.idSalleVente).getIdProduitSalle();
		int nbr_produit = produits.size();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 90*(nbr_produit + 1) );
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String typeSalle = act.affichageSallesDeVente().getTypeSalleProduit(produitSalleVente.idSalleVente);
		

		JLabel lblProduitSalleVente = new JLabel(nomSalleVente);
		lblProduitSalleVente.setBounds(195, 12, 218, 15);
		frame.getContentPane().add(lblProduitSalleVente);
		
		JButton btnNewButton0 = new JButton("retour");
		btnNewButton0.setBounds(10, 12, 100, 25);
		btnNewButton0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parentFrame.setVisible(true);
				
			}
		});
		frame.getContentPane().add(btnNewButton0);

		for(int j =0; j < nbr_produit; j++) {
			String nom = produits.elementAt(j);
			int id = idProduit.elementAt(j);
			
			JButton btnNewButton1 = new JButton(nom);
			btnNewButton1.setBounds(45, 80+40*j, 350, 25);	
			btnNewButton1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(typeSalle == "montante") {
							produitDescendant window = new produitDescendant(data, email, id, nom);
							window.frame.setVisible(true);
							frame.dispose();
						}
						else {
							produitDescendant window = new produitDescendant(data, email, id, nom);
							window.frame.setVisible(true);
							frame.dispose();
						}
						
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

	
	

