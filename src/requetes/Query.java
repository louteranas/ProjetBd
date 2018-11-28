package requetes;

import connexion.*;
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
	
	public void affichageResultat() throws SQLException{
		ResultSet resultat = this.getResult();
        ResultSetMetaData rsetmd = resultat.getMetaData();
        int i = rsetmd.getColumnCount();
        while (resultat.next()) {
            for (int j = 1; j <= i; j++) {
                System.out.print(resultat.getString(j) + "\t");
	    }
	    System.out.println();
        }
    }
	
	public Vector<String> getNomSallesVente() throws SQLException{
		Vector<String> salles = new Vector<String>();
        ResultSetMetaData rsetmd = this.result.getMetaData();
        while (this.result.next()) {
        	salles.add(this.result.getString(2));
	    }
        return salles;
    }
	
	public Vector<Integer> getIdSallesVente() throws SQLException{
		Vector<Integer> salles = new Vector<Integer>();
        ResultSetMetaData rsetmd = this.result.getMetaData();
        while (this.result.next()) {
        	salles.add(Integer.valueOf(this.result.getString(1)));
	    }
        return salles;
    }
	
	public Vector<Integer> getIdEnchereSallesVente() throws SQLException{
		Vector<Integer> salles = new Vector<Integer>();
        ResultSetMetaData rsetmd = this.result.getMetaData();
        while (this.result.next()) {
        	salles.add(Integer.valueOf(this.result.getString(3)));
	    }
        return salles;
    }
	
	public String getTypeEnchere() throws SQLException{
        ResultSetMetaData rsetmd = result.getMetaData();
		String type = " (";
		while (this.result.next()) {
        	if(this.result.getString(2) == "montante") {
        		type = type + "montante /";
        	}
        	else {
        		type = type + "descendante /";
        	}
        	if(this.result.getString(3) == "oui") {
        		type = type + "plusieurs enchères /";
        	}
        	else {
        		type = type + "une enchère /";
        	}
        	if(this.result.getString(4) == "revocable") {
        		type = type + "revocable)";
        	}
        	else {
        		type = type + "non revocable)";
        	}
		}
		return type;
		
	}
	
	public String getTypeSalleProduit(int id) throws SQLException{
		ResultSetMetaData rsetmd = result.getMetaData();
		while (this.result.next()) {
			if(Integer.valueOf(this.result.getString(1)) == id) {
        	if(this.result.getString(2) == "montante") {
        		return "montante";
        	}
        	else {
        		return "descendante";
        	}
			}
		}
		return null;
	}
	
	public Vector<String> getPoduitSalle() throws SQLException{
		Vector<String> salles = new Vector<String>();
        ResultSetMetaData rsetmd = this.result.getMetaData();
        while (this.result.next()) {
        	salles.add(this.result.getString(2) + " (stock = " + this.result.getString(4)+ ")");
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


	/**
	 * Renvoie le résultat sous forme d'un entier
	 * (lorsque le résultat attendu est un id par exemple)
	 */
	public int getSimpleResult(ResultSet resultat) throws SQLException {
		resultat.next();
		return(resultat.getInt(1));
	}

	public String getStrResult(ResultSet resultat) throws SQLException {
		resultat.next();
		return(resultat.getString(1));
	}




}
