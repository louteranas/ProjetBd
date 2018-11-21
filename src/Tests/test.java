package Tests;

import java.sql.SQLException;

import connexion.Actions;
import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;

public class test {
	public static void main(String[] args){
		try {
		DataBaseAccess data = new DataBaseAccess();	
		//IdentificationUtilisateur user = new IdentificationUtilisateur(data);
		Actions act = new Actions("leila.kany@gmail.com");
		act.enchere(data);
		}
		catch(SQLException e) {
			
		}
	}
}
