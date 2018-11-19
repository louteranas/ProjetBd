package requetes;

import connexion.DataBaseAccess;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class Query {
	protected DataBaseAccess data;
	protected String requete;
	protected ResultSet result;
	
	public Query(DataBaseAccess data, String requete){
		this.data= data;
		this.requete = requete;		 
	}
	
	public void affichageResultat(ResultSet resultat) throws SQLException{
        ResultSetMetaData rsetmd = resultat.getMetaData();
        int i = rsetmd.getColumnCount();
        while (resultat.next()) {
            for (int j = 1; j <= i; j++) {
                System.out.print(resultat.getString(j) + "\t");
	    }
	    System.out.println();
        }
    }
	
	public ResultSet getResult() {
		return this.result;
	}
}
