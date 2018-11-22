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
//			System.out.println(requeteRecherche.getResult().next());
			
			//return requeteRecherche.affichageResultatUser(requeteRecherche.getResult());
			return requeteRecherche.affichageResultatUser(requeteRecherche.getResult());
//			if(requeteRecherche.affichageResultatUser(requeteRecherche.getResult())) {
//				System.out.println("Vous n'etes pas inscrit askip! ");
//				System.out.println("Veuillez entrer votre nom");
//				@SuppressWarnings("resource")
//				Scanner scan = new Scanner(System.in);
//				String nom = scan.nextLine();
//				System.out.println("Veuillez entrer votre prenom");
//				String prenom = scan.nextLine();
//				System.out.println("Veuillez entrer votre adresse");
//				String addresse = scan.nextLine();
//				System.out.println("Super !");
//				this.addUtilisateur(email, nom, prenom, addresse);
//				System.out.println("inscription réussite, voici vos donnée");
//				requeteRecherche = new SimpleQuery(this.data, rechercheUtilisateur);
//				requeteRecherche.affichageResultat(requeteRecherche.getResult());
//			}
		
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
