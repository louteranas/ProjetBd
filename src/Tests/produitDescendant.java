
package Tests;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Actions.Actions;
import connexion.DataBaseAccess;

import javax.swing.JTextField;

public class produitDescendant {

	public JFrame frame;
	private JTextField textField;
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
		frame.setBounds(100, 100, 450, 80 + 80*(nbr_caracteristique+1));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNomproduit = new JLabel(nomProduit);
		lblNomproduit.setBounds(200, 12, 300, 12);
		frame.getContentPane().add(lblNomproduit);
		
		
		JButton btnEnchere = new JButton("Enchérir");
		btnEnchere.setBounds(237, 48, 100, 15);
		frame.getContentPane().add(btnEnchere);

		JButton btnNewButton0 = new JButton("retour");
		btnNewButton0.setBounds(10, 12, 100, 25);
		btnNewButton0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parentFrame.setVisible(true);
				
			}
		});
		frame.getContentPane().add(btnNewButton0);
			
		
		JLabel lblPrixActuel = new JLabel("Prix actuel :");
		lblPrixActuel.setBounds(25, 46, 184, 22);
		frame.getContentPane().add(lblPrixActuel);

		for(int j =0; j < nbr_caracteristique; j++) {
			String texte = caracteristiques.elementAt(j);
			JLabel lblAjouterCaracteristiques = new JLabel(texte);
			lblAjouterCaracteristiques.setBounds(25, 80+80*j, 400, 80);
			frame.getContentPane().add(lblAjouterCaracteristiques);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
