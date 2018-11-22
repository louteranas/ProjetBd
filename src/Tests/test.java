package Tests;

import java.awt.EventQueue;
import java.sql.SQLException;

import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;
import requetes.*;

public class test {
//	public static void main(String[] args){
//		try {
//		DataBaseAccess data = new DataBaseAccess();	
//		IdentificationUtilisateur user = new IdentificationUtilisateur(data);
//		//String requete = "select nom from utilisateur where email = 'truc@gmail.com'";
//		//SimpleQuery req = new SimpleQuery(data, requete);
//		}
//		catch(SQLException e) {
//			
//		}
//	}
//}


/**
 * Launch the application.
 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}