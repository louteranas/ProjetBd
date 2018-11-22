package requetes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.DataBaseAccess;

public class SimpleQuery extends Query {

	public SimpleQuery(DataBaseAccess data, String requete) throws SQLException{
		super(data, requete);	
		// on crée la requete 
		PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
		
		// on execute la requete
		this.result = statement.executeQuery();
	    
	    // Fermeture 
		//result.close();
		//statement.close();

	}

}
