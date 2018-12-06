
package Graphics;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Actions.Achat;
import Actions.Actions;
import connexion.DataBaseAccess;

import javax.swing.JTextField;

public class produitDescendant {

	public JFrame frame;
	private JTextField quantite;
	private static String email;
	private static DataBaseAccess data;
	private static int idProduit;

	/**
	 * Create the application.
	 * @param parentFrame 
	 */
	public produitDescendant(DataBaseAccess data, String email, int idProduit, String nomProduit, JFrame parentFrame) {
		produitDescendant.data = data;
		produitDescendant.email = email;
		produitDescendant.idProduit = idProduit;
		initialize(nomProduit, parentFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nomProduit, JFrame parentFrame) {
		try {
		Actions act = new Actions(email, data);
		Vector<String> caracteristiques = act.getCaracteristiques(produitDescendant.idProduit).affichageCaracteristiquesProduit();
		int nbr_caracteristique = caracteristiques.size();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 200 + 80*(nbr_caracteristique+1));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNomproduit = new JLabel(nomProduit);
		lblNomproduit.setBounds(150, 12, 300, 22);
		frame.getContentPane().add(lblNomproduit);
		
		JLabel lblUnitaire = new JLabel("(unitaire)");
		lblUnitaire.setBounds(25, 60, 100, 22);
		frame.getContentPane().add(lblUnitaire);
		
		JLabel lblQuantite = new JLabel("Quantité ?");
		lblQuantite.setBounds(215, 48, 100, 22);
		frame.getContentPane().add(lblQuantite);
		
		quantite = new JTextField();
		quantite.setBounds(310, 48, 80, 19);
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
			
		
		int currentPrice = act.prixCourant(act.getIdVenteProduit(produitDescendant.idProduit));

		JLabel lblPrixActuel = new JLabel("Prix actuel :  " + currentPrice);
		lblPrixActuel.setBounds(25, 46, 184, 22);
		frame.getContentPane().add(lblPrixActuel);

		JLabel lblCaracteristiques = new JLabel("Caractéristiques:");
		lblCaracteristiques.setBounds(25, 120, 200, 22);
		frame.getContentPane().add(lblCaracteristiques);

		for(int j =0; j < nbr_caracteristique; j++) {
			String texte = caracteristiques.elementAt(j);
			JLabel lblAjouterCaracteristiques = new JLabel(texte);
			lblAjouterCaracteristiques.setBounds(35, 120+80*j, 400, 80);
			frame.getContentPane().add(lblAjouterCaracteristiques);
		}
		
		JButton btnEnchere = new JButton("Enchérir");
		btnEnchere.setBounds(310, 80, 100, 15);
		frame.getContentPane().add(btnEnchere);
		btnEnchere.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (quantite.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas inseré de quantité !");
				}
				else {
					String texteQuantite = quantite.getText();
					int quantiteInt = Integer.parseInt(texteQuantite);
						
						
					try {
					int idVente = act.getIdVenteProduit(produitDescendant.idProduit);
						try {
							Achat achat = new Achat(email, data);
							achat.newEnchereDesc(idVente, quantiteInt);
							JOptionPane.showMessageDialog(null, "Enchère bien enregistrée");
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
						catch (Exception e) {
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
