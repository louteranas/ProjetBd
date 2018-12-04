
package Graphics;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Actions.*;
import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;
import requetes.SimpleQuery;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class produitAscendant {

	public JFrame frame;
	private JTextField prix;
	private JTextField quantite;
 	private static String email;
	private static DataBaseAccess data;
	private static int idProduit;


	/**
	 * Create the application.
	 * @param parentFrame 
	 */
	public produitAscendant(DataBaseAccess data, String email, int idProduit, String nomProduit, JFrame parentFrame) {
		produitAscendant.data = data;
		produitAscendant.email = email;
		produitAscendant.idProduit = idProduit;
		initialize(nomProduit, parentFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nomProduit, JFrame parentFrame) {
		try {
		Actions act = new Actions(email, data);

		Vector<String> caracteristiques = act.getCaracteristiques(produitAscendant.idProduit).affichageCaracteristiquesProduit();
		int nbr_caracteristique = caracteristiques.size();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 220 + 80*nbr_caracteristique);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNomproduit = new JLabel(nomProduit);
		lblNomproduit.setBounds(150, 12, 300, 22);
		frame.getContentPane().add(lblNomproduit);

		JLabel lblPrix = new JLabel("Prix ?");
		lblPrix.setBounds(200, 48, 100, 22);
		frame.getContentPane().add(lblPrix);
		
		prix = new JTextField();
		prix.setBounds(310, 48, 80, 19);
		frame.getContentPane().add(prix);
		prix.setColumns(10);
		
		JLabel lblQuantite = new JLabel("Quantité ?");
		lblQuantite.setBounds(200, 70, 100, 22);
		frame.getContentPane().add(lblQuantite);
		
		quantite = new JTextField();
		quantite.setBounds(310, 70, 80, 19);
		frame.getContentPane().add(quantite);
		quantite.setColumns(10);
		

		JButton btnNewButton0 = new JButton("retour");
		btnNewButton0.setBounds(10, 12, 100, 25);
		btnNewButton0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parentFrame.setVisible(true);
				
			}
		});
		frame.getContentPane().add(btnNewButton0);
		
		
		int currentPrice = act.prixCourant(act.getIdVenteProduit(produitAscendant.idProduit));

		JLabel lblPrixActuel = new JLabel("Prix actuel :  " + currentPrice);
		lblPrixActuel.setBounds(25, 46, 184, 22);
		frame.getContentPane().add(lblPrixActuel);
		
		JLabel lblUnitaire = new JLabel("(unitaire)");
		lblUnitaire.setBounds(25, 60, 100, 22);
		frame.getContentPane().add(lblUnitaire);
		
		JLabel lblCaracteristiques = new JLabel("Caractéristiques:");
		lblCaracteristiques.setBounds(25, 140, 200, 22);
		frame.getContentPane().add(lblCaracteristiques);

		for(int j =0; j < nbr_caracteristique; j++) {
			String texte = caracteristiques.elementAt(j);
			JLabel lblAjouterCaracteristiques = new JLabel(texte);
			lblAjouterCaracteristiques.setBounds(35, 140+80*j, 400, 80);
			frame.getContentPane().add(lblAjouterCaracteristiques);
		}
		
		
		JButton btnEnchere = new JButton("Enchérir");
		btnEnchere.setBounds(310, 100, 100, 15);
		frame.getContentPane().add(btnEnchere);
		btnEnchere.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(prix.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas inseré de prix !");
				}
				else if (quantite.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas inseré de quantité !");
				}
				else {
					String textePrix = prix.getText();
					int prixInt = Integer.parseInt(textePrix);
						
					String texteQuantite = quantite.getText();
					int quantiteInt = Integer.parseInt(texteQuantite);
						
						
					try {
					int idVente = act.getIdVenteProduit(produitAscendant.idProduit);
						try {
							Achat achat = new Achat(email, data);
							achat.newEnchereAsc(idVente, prixInt, quantiteInt);
							JOptionPane.showMessageDialog(null, "Enchère bien enregistrée");
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Il y a eu une erreur, contactez un administrateur !");
						e.printStackTrace();
					}
				}
			}
		});
		
		} catch (SQLException e) {
			 // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

