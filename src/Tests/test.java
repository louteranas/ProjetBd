package Tests;

import java.awt.EventQueue;
import java.sql.SQLException;

import connexion.Actions;
import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;

public class test {


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