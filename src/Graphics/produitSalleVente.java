package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Actions.*;
import connexion.DataBaseAccess;
import requetes.*;

public class produitSalleVente {

	public JFrame frame;
	private static String email;
	private static DataBaseAccess data;
	private static int idSalleVente;

	/**
	 * Create the application.
	 * @param besoin 
	 * @param frame2 
	 */

	public produitSalleVente(DataBaseAccess data, String email, int idSalleVente, String nomSalleVente, String besoin, JFrame parentFrame) {
		produitSalleVente.data = data;
		produitSalleVente.email = email;
      	produitSalleVente.idSalleVente = idSalleVente;
		initialize(nomSalleVente, besoin, parentFrame);
	}


	/**
	 * Initialize the contents of the frame.
	 * @param besoin 
	 */
	private void initialize(String nomSalleVente, String besoin, JFrame parentFrame) {
		try {
		Actions act = new Actions(email, data);
		Achat achat = new Achat(email, data);

		System.out.println("on est en salle d'id"+ produitSalleVente.idSalleVente);
		Vector<String> produits = act.produitsSalle(produitSalleVente.idSalleVente).getProduitSalle(act, achat);
		Vector<Integer> idProduit = act.produitsSalle(produitSalleVente.idSalleVente).getIdProduitSalle();
		
		
		int nbr_produit = produits.size();
		
		
		/**
		 * Supprimer les produits finis de "produits" et diminuer nbr_produit
 		 */
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 90*(nbr_produit + 1) );
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		boolean typeSalle = act.enchereMont(act.getIdTypeEnchere(produitSalleVente.idSalleVente));
		

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
			int idProd = idProduit.elementAt(j);
			JButton btnNewButton1 = new JButton(nom);
			btnNewButton1.setBounds(45, 80+40*j, 350, 25);	
			btnNewButton1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(besoin.equals("achat")) {
							if(typeSalle) {
								produitAscendant window = new produitAscendant(data, email, idProd, nom, frame);
								window.frame.setVisible(true);
								frame.setVisible(false);
							}
							else {
								produitDescendant window = new produitDescendant(data, email, idProd, nom, frame);
								window.frame.setVisible(true);
								frame.setVisible(false);
							}	
						}
						else if(besoin.equals("historique")) {
							Achat ach = new Achat(email, data);
							//System.out.println(ach.venteFinie(act.getIdVenteProduit(idProd)));
							//if(ach.venteFinie(act.getIdVenteProduit(idProd))) {
							historiqueProduit window = new historiqueProduit(data, email, idProd, nom, typeSalle, frame);
							window.frame.setVisible(true);
							frame.setVisible(false);
							//}
							//else {
							//	JOptionPane.showMessageDialog(null, "la vente pour ce produit n'est pas finie !");
							//}
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

	
	

