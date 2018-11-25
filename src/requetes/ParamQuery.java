package requetes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.DataBaseAccess;

public class ParamQuery extends Query {

	public ParamQuery(DataBaseAccess data, String requete, int nbr) throws SQLException{
		super(data, requete);	
		// on crée la requete 
		PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
		statement.setInt(1, nbr); 
		// on execute la requete
		this.result = statement.executeQuery();
	    
	    // Fermeture 
		//result.close();
		//statement.close();

	}
	
	public ParamQuery(DataBaseAccess data, String requete, String str) throws SQLException{
		super(data, requete);	
		// on crée la requete 
		PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
		statement.setString(1, str); 
		// on execute la requete
		this.result = statement.executeQuery();
	    
	    // Fermeture 
		//result.close();
		//statement.close();

	}
	
	public ParamQuery(DataBaseAccess data, String requete,int nbr, String str) throws SQLException{
		super(data, requete);	
		// on crée la requete 
		PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
		statement.setInt(1, nbr);
		statement.setString(2, str); 
		// on execute la requete
		this.result = statement.executeQuery();
	    
	    // Fermeture 
		//result.close();
		//statement.close();

	}
	
	public ParamQuery(DataBaseAccess data, String requete, String str, int nbr) throws SQLException{
		super(data, requete);	
		// on crée la requete 
		PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
		statement.setString(1, str);
		statement.setInt(2, nbr);
		 
		// on execute la requete
		this.result = statement.executeQuery();
	    
	    // Fermeture 
		//result.close();
		//statement.close();

	}

	/**
	 * pour insert into enchere
	 * @param data
	 * @param requete
	 * @param str
	 * @param nbr
	 * @throws SQLException
	 */
	public ParamQuery(DataBaseAccess data, String requete, String str, int  ) throws SQLException{
		super(data, requete);
		// on crée la requete
		PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
		statement.setString(1, str);
		statement.setInt(2, nbr);

		// on execute la requete
		this.result = statement.executeQuery();

		// Fermeture
		//result.close();
		//statement.close();

	}




}
