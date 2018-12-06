package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import connexion.DataBaseAccess;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Actions.Actions;
import Actions.Vente;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class vendreProduit {

	public JFrame frame;
	private JTextField nomProduit;
	private JTextField stock;
	private JTextField prixDepart;
	private JTextField prixRevient;
	private JTextField duree;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @param frame2 
	 * @param salle 
	 * @param id 
	 * @param email 
	 * @param data 
	 * @param act 
	 * @param parentFrame 
	 */
	public vendreProduit(DataBaseAccess data, String email, int idSalle, String salle, Actions act, JFrame parentFrame) {

		initialize(data, email, idSalle, act,  parentFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param parentFrame 
	 * @param idSalle 
	 * @param email 
	 * @param data 
	 * @param act 
	 */
	private void initialize(DataBaseAccess data, String email, int idSalle, Actions act, JFrame parentFrame) {
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 460);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblVendreUnProduit = new JLabel("Vendre un produit");
		lblVendreUnProduit.setBounds(171, 12, 143, 15);
		frame.getContentPane().add(lblVendreUnProduit);
		
		JLabel lblNomDuProduit = new JLabel("Nom du produit :");
		lblNomDuProduit.setBounds(22, 53, 127, 15);
		frame.getContentPane().add(lblNomDuProduit);
		
		nomProduit = new JTextField();
		nomProduit.setBounds(206, 51, 216, 19);
		frame.getContentPane().add(nomProduit);
		nomProduit.setColumns(10);
		
		JLabel lblStock = new JLabel("Stock : ");
		lblStock.setBounds(22, 84, 70, 15);
		frame.getContentPane().add(lblStock);
		
		stock = new JTextField();
		stock.setBounds(206, 82, 216, 19);
		frame.getContentPane().add(stock);
		stock.setColumns(10);
		
		JLabel lblPrixRevient = new JLabel("Prix de revient :");
		lblPrixRevient.setBounds(22, 148, 127, 15);
		frame.getContentPane().add(lblPrixRevient);
		
		prixDepart = new JTextField();
		prixDepart.setBounds(206, 113, 216, 19);
		frame.getContentPane().add(prixDepart);
		prixDepart.setColumns(10);
		
		JLabel label = new JLabel("Prix de départ :");
		label.setBounds(22, 111, 127, 15);
		frame.getContentPane().add(label);
		
		prixRevient = new JTextField();
		prixRevient.setBounds(206, 146, 216, 19);
		frame.getContentPane().add(prixRevient);
		prixRevient.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("durée :");
		lblNewLabel.setBounds(22, 181, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		duree = new JTextField();
		duree.setBounds(206, 177, 216, 19);
		frame.getContentPane().add(duree);
		duree.setColumns(10);
		duree.setText("0");
		
		JLabel lblCaractristiques = new JLabel("caractéristiques :");
		lblCaractristiques.setBounds(22, 215, 127, 15);
		frame.getContentPane().add(lblCaractristiques);
		
		JTextArea caract = new JTextArea();
		caract.setBounds(206, 215, 216, 132);
		frame.getContentPane().add(caract);
		
		JButton btnVendre = new JButton("Vendre");
		btnVendre.setBounds(153, 380, 117, 25);
		btnVendre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					Vente sell = new Vente(email, data);
					ArrayList<String> caratProduit = new ArrayList<>();
					caratProduit.add(caract.getText());
					sell.newVente(caratProduit, Integer.valueOf(prixDepart.getText()), Integer.valueOf(duree.getText()), nomProduit.getText(), Integer.valueOf(prixRevient.getText()), Integer.valueOf(stock.getText()), idSalle);
					JOptionPane.showMessageDialog(null, "Vente bien crée !");
					frame.setVisible(false);
					parentFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnVendre);
	}
}
