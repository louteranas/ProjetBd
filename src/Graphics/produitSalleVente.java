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

		Vector<String> produits = new Vector<>();
		Vector<Integer> idProduits = new Vector<>();
		act.produitsSalle(produitSalleVente.idSalleVente).getProduitSalle(act, achat, produits, idProduits);
		
		Vector<String> produitsNonFinis = new Vector<>();
		Vector<Integer> idProduitNonFinis = new Vector<>();
		
		Vector<String> produitsFinis = new Vector<>();
		Vector<Integer> idProduitFinis = new Vector<>();
		
		for(int i = 0; i < produits.size(); i++) {
			if(achat.venteFinie(act.getIdVenteProduit(idProduits.elementAt(i)))){
				produitsFinis.add(produits.elementAt(i));
        		idProduitFinis.add(idProduits.elementAt(i));
			}
			else {
				produitsNonFinis.add(produits.elementAt(i));
        		idProduitNonFinis.add(idProduits.elementAt(i));
			}
		}
		
		
		int nbrProduitNonFinis = produitsNonFinis.size();
		int nbrProduitFinis = produitsFinis.size();
		/**
		 * Supprimer les produits finis de "produits" et diminuer nbr_produit
 		 */
		
		frame = new JFrame();
		if(besoin.equals("achat")) {
			frame.setBounds(100, 100, 450, 90*(nbrProduitNonFinis + 1) );
		}
		else if (besoin.equals("historique")) {
			frame.setBounds(100, 100, 450, 90*(nbrProduitFinis + 1) );
		}
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
		
		if(besoin.equals("achat")) {
			for(int j =0; j < nbrProduitNonFinis; j++) {
				String nom = produitsNonFinis.elementAt(j);
				int idProd = idProduitNonFinis.elementAt(j);
				System.out.println(nom + ": "+achat.venteFinie(act.getIdVenteProduit(idProd)));
				JButton btnNewButton1 = new JButton(nom);
				btnNewButton1.setBounds(45, 80+40*j, 350, 25);	
				btnNewButton1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						try {
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
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.getContentPane().add(btnNewButton1);
			}
		}
		else if (besoin.equals("historique")) {

			for(int j =0; j < nbrProduitFinis; j++) {
				String nom = produitsFinis.elementAt(j);
				int idProd = idProduitFinis.elementAt(j);
				JButton btnNewButton1 = new JButton(nom);
				btnNewButton1.setBounds(45, 80+40*j, 350, 25);	
				btnNewButton1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						try {
								historiqueProduit window = new historiqueProduit(data, email, idProd, nom, typeSalle, frame);
								window.frame.setVisible(true);
								frame.setVisible(false);	
							}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.getContentPane().add(btnNewButton1);
			}
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
}
}

	
	

