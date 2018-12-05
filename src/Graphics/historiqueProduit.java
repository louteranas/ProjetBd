package Graphics;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Actions.Actions;
import Actions.Admin;
import connexion.DataBaseAccess;
import requetes.ParamQuery;

public class historiqueProduit {

	public JFrame frame;

	/**
	 * Create the application.
	 * @param parentFrame 
	 * @param nom 
	 * @param idProd 
	 * @param email 
	 * @param data 
	 * @param typeSalle 
	 */
	public historiqueProduit(DataBaseAccess data, String email, int idProd, String nom, boolean typeSalle, JFrame parentFrame) {
		initialize(data , email, idProd, nom, typeSalle, parentFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param parentFrame 
	 * @param nom 
	 * @param idProd 
	 * @param email 
	 * @param data 
	 * @param typeSalle 
	 */
	private void initialize(DataBaseAccess data, String email, int idProd, String nom, boolean typeSalle, JFrame parentFrame) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Admin adm = new Admin(email, data);
		Actions act = new Actions(email, data);
		System.out.println(idProd);
		ParamQuery queryVente = act.ventesProduit(idProd);
		int idVente;
		JLabel lbltitre = new JLabel("Liste des vainqueurs");
		lbltitre.setBounds(45, 30, 400, 15);
		frame.getContentPane().add(lbltitre);
		try {
			idVente = queryVente.getSimpleResult(queryVente.getResult());
			System.out.println(idVente);
			ArrayList<String> listeVainqueurs = new ArrayList<>();
			if(typeSalle) {
				listeVainqueurs = adm.vainquersAsc(idVente);
			}
			else {
					ParamQuery req = adm.vainqueursDesc(idVente);
					req.getLigneVainqueur(listeVainqueurs) ;
			}
			int size = listeVainqueurs.size();
			for(int j =0; j < size; j++) {
				JLabel lblVainqueurs = new JLabel(listeVainqueurs.get(j));
				lblVainqueurs.setBounds(45, 30+40*j, 400, 15);
				frame.getContentPane().add(lblVainqueurs);
				
			}
			
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
