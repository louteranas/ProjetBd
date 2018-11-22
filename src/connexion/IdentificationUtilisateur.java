package connexion;

import java.sql.*;
import java.util.Scanner;

import requetes.*;

public class IdentificationUtilisateur {
	DataBaseAccess data;
	String email;
	
	public IdentificationUtilisateur(DataBaseAccess data, String email) throws SQLException {
		this.data = data;
		this.email = email;
		
	}

	public Boolean VerificationUser() {
		String rechercheUtilisateur = "select * from utilisateur where email ='" + email+"'";
		try{
			SimpleQuery requeteRecherche = new SimpleQuery(this.data, rechercheUtilisateur);		
			return requeteRecherche.affichageResultatUser(requeteRecherche.getResult());		
		}
		catch(SQLException excep) {
			System.err.println("failed");
			excep.printStackTrace(System.err);
			
		}
		return null;
	}
	public void addUtilisateur(String email, String nom, String prenom, String addresse) throws SQLException {
		
		String addUser = "insert into utilisateur values('"+email+"','"+nom+"','"+prenom+"','"+addresse+"')";
		SimpleQuery add = new SimpleQuery(this.data, addUser);
	}

}
