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
	 * @throws SQLException 
	 */
	private void initialize(DataBaseAccess data, String email, int idProd, String nom, boolean typeSalle, JFrame parentFrame) {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Admin adm = new Admin(email, data);
		Actions act = new Actions(email, data);
		int idVente = 0;
		try {
			idVente = act.getIdVenteProduit(idProd);
			System.out.print(idVente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("le soucis est la fonction de vente");
		}
		JButton btnNewButton0 = new JButton("retour");
		btnNewButton0.setBounds(10, 12, 100, 25);
		btnNewButton0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parentFrame.setVisible(true);
				
			}
		});
		frame.getContentPane().add(btnNewButton0);
		JLabel lbltitre = new JLabel("Liste des vainqueurs");
		lbltitre.setBounds(200, 12, 400, 15);
		frame.getContentPane().add(lbltitre);
		try {
			ArrayList<String> listeVainqueurs = new ArrayList<>();
			if(typeSalle) {
				listeVainqueurs = adm.vainqueursAsc(idVente);
			}
			else {
					ParamQuery req = adm.vainqueursDesc(idVente);
					req.getLigneVainqueur(listeVainqueurs) ;
			}
			int size = listeVainqueurs.size();
			frame.setBounds(100, 100, 800, 90+50*size);
			for(int j =0; j < size; j++) {
				JLabel lblVainqueurs = new JLabel(listeVainqueurs.get(j));
				lblVainqueurs.setBounds(45, 50+40*j, 700, 15);
				frame.getContentPane().add(lblVainqueurs);
				
			}	
			
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
