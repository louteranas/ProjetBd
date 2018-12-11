package connexion;

import java.sql.SQLException;

import requetes.SimpleQuery;

public class AjoutUtilisateur {
	DataBaseAccess data;
	String email;
	String nom;
	String prenom;
	String addresse;
	
	public AjoutUtilisateur(DataBaseAccess data, String email, String nom, String prenom, String addresse) throws SQLException {
		this.data = data;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.addresse = addresse;
		String addUser = "insert into utilisateur values('"+email+"','"+nom+"','"+prenom+"','"+addresse+"')";
		SimpleQuery add = new SimpleQuery(this.data, addUser);
		new SimpleQuery(this.data, "commit" );
	}
}
