package Tests;

import java.sql.SQLException;

import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;
import requetes.*;

public class test {
	public static void main(String[] args){
		try {
		DataBaseAccess data = new DataBaseAccess();	
		IdentificationUtilisateur user = new IdentificationUtilisateur(data);
		//String requete = "select nom from utilisateur where email = 'truc@gmail.com'";
		//SimpleQuery req = new SimpleQuery(data, requete);
		}
		catch(SQLException e) {
			
		}
	}
}
