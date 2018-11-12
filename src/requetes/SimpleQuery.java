package requetes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.DataBaseAccess;

public class SimpleQuery extends Query {

	public SimpleQuery(DataBaseAccess data, String requete){
		super(data, requete);	
		try {
			// on crée la requete 
			PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
			
			// on execute la requete
			ResultSet result = statement.executeQuery();
			
			 // Affichage du resultat
            System.out.println("Resultats:");
            this.affichageResultat(result);
            System.out.println("");
            
            // Fermeture 
    		result.close();
    		statement.close();
		}
		catch (SQLException except) {
            System.err.println("failed");
            except.printStackTrace(System.err);
        }
		
		 
	}
}
