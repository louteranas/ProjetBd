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

	public ParamQuery(DataBaseAccess data, String requete, String s, String t) throws SQLException {
	    super(data, requete);
        PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
        statement.setString(1, s);
        statement.setString(2, t);
        // on execute la requete
        this.result = statement.executeQuery();
    }

    public ParamQuery(DataBaseAccess data, String requete, int i, int j, int k) throws SQLException {
        super(data, requete);
        PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
        statement.setInt(1, i);
        statement.setInt(2, j);
        statement.setInt(3, k);
        // on execute la requete
        this.result = statement.executeQuery();
    }

    public ParamQuery(DataBaseAccess data, String requete, String st, int j, int k) throws SQLException {
        super(data, requete);
        PreparedStatement statement = this.data.getConn().prepareStatement(this.requete);
        statement.setString(1, st);
        statement.setInt(2, j);
        statement.setInt(3, k);
        // on execute la requete
        this.result = statement.executeQuery();
    }

}
