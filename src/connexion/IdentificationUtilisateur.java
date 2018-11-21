package connexion;

import java.sql.*;
import java.util.Scanner;

import requetes.*;

public class IdentificationUtilisateur {
	DataBaseAccess data;
	String email;
	
	public IdentificationUtilisateur(DataBaseAccess data) throws SQLException {
		this.data = data;
		this.email = this.getEmail();
		String rechercheUtilisateur = "select nom from utilisateur where email ='" + email+"'";
		try{
			SimpleQuery requeteRecherche = new SimpleQuery(this.data, rechercheUtilisateur);
			//System.out.println(requeteRecherche.getResult().next());
			if(requeteRecherche.getResult().next() == false) {
				System.out.println("Vous n'etes pas inscrit askip! ");
				System.out.println("Veuillez entrer votre nom");
				@SuppressWarnings("resource")
				Scanner scan = new Scanner(System.in);
				String nom = scan.nextLine();
				System.out.println("Veuillez entrer votre prenom");
				String prenom = scan.nextLine();
				System.out.println("Veuillez entrer votre adresse");
				String addresse = scan.nextLine();
				System.out.println("Super !");
				this.addUtilisateur(email, nom, prenom, addresse);
				
			}
			
		}
		catch(SQLException excep) {
			System.err.println("failed");
			excep.printStackTrace(System.err);
			
		}
	}
	
	public String getEmail() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("veuillez entrez votre Email");
		return scan.nextLine();
		
	}
	
	public void addUtilisateur(String email, String nom, String prenom, String addresse) throws SQLException {
		
		String addUser = "insert into utilisateur values('"+email+"','"+nom+"','"+prenom+"','"+addresse+"')";
		SimpleQuery add = new SimpleQuery(this.data, addUser);
	}

}
