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
	
	public int nbreSalleVente(ResultSet resultat) throws SQLException{
			
	        ResultSetMetaData rsetmd = resultat.getMetaData();
	        int i = rsetmd.getColumnCount();
	        int nbreSalleVente = 0;
	        while (resultat.next()) {
	        	nbreSalleVente = nbreSalleVente + 1;
		    }
	        return nbreSalleVente;
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


	/**
	 * Renvoie le résultat sous forme d'un entier
	 * (lorsque le résultat attendu est un id par exemple)
	 */
	public int getSimpleResult(ResultSet resultat) throws SQLException {
		resultat.next();
		return(resultat.getInt(1));
	}




}
