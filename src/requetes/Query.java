package requetes;

import connexion.DataBaseAccess;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

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
	
	public Vector<String> getSallesVente() throws SQLException{
		Vector<String> salles = new Vector<String>();
        ResultSetMetaData rsetmd = this.result.getMetaData();
        while (this.result.next()) {
        	salles.add(this.result.getString(2));
	    }
        return salles;
    }

	public boolean affichageResultatUser(ResultSet resultat) throws SQLException{
        ResultSetMetaData rsetmd = resultat.getMetaData();
        int i = rsetmd.getColumnCount();
        if(resultat.next() == false) {
        	return true;
        }
        else {
        	System.out.println("vous etes inscrit(e), voici vos données:");
        	for (int j = 1; j <= i; j++) {
                System.out.print(resultat.getString(j) + "\t");
        	}
	    	while (resultat.next()) {
	            for (int j = 1; j <= i; j++) {
	                System.out.print(resultat.getString(j) + "\t");
	 	    }
	 	    System.out.println();
	         }	
        }
       
        return false;
    }
	
	public ResultSet getResult() {
		return this.result;
	}


}
