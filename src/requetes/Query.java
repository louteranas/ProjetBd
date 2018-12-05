package requetes;

import connexion.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import Actions.Achat;
import Actions.Actions;

public abstract class Query {
	protected DataBaseAccess data;
	protected String requete;
	protected ResultSet result;
	
	public Query(DataBaseAccess data, String requete){
		this.data= data;
		this.requete = requete;		 
	}
	/*
	*Fait la String pour un vainqueur
	*/
	public void getLigneVainqueur(ArrayList<String> listeVainqueurs) throws SQLException{
		ResultSet resultat = this.getResult();
		ResultSetMetaData rsetmd = resultat.getMetaData();
		int i = rsetmd.getColumnCount();
		while (resultat.next()) {
			String output = "";
			output = output + "email: " + resultat.getString(1);
			output = output + "date: " + resultat.getString(2);
			output = output + "prix unitaire: " + resultat.getString(3);
			output = output + "quantité: " + resultat.getString(4);
			//for (int j = 1; j <= i; j++) {
			//	output = output + resultat.getString(j);
			//}
			listeVainqueurs.add(output);
		}
	}
	/*
	*Fait la String pour le dernier vainqueur qui na pas toute la quantite voulu
	*/
	public void getLigneSemiVainqueur(int newQuantite, ArrayList<String> listeVainqueurs) throws SQLException{
		
		ResultSet resultat = this.getResult();
		ResultSetMetaData rsetmd = resultat.getMetaData();
		int i = rsetmd.getColumnCount();
		while (resultat.next()) {
			String output = "";
			output = output + "email: " + resultat.getString(1);
			output = output + "date: " + resultat.getString(2);
			output = output + "prix unitaire: " + resultat.getString(3);
			output = output + "quantité: " + newQuantite;
			listeVainqueurs.add(output);
		}
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
        while (this.result.next()) {
        	salles.add(this.result.getString(2));
	    }
        return salles;
    }
	
	public Vector<Integer> getIdSallesVente() throws SQLException{
		Vector<Integer> salles = new Vector<Integer>();
        while (this.result.next()) {
        	salles.add(Integer.valueOf(this.result.getString(1)));
	    }
        return salles;
    }
	
	public Vector<Integer> getIdEnchereSallesVente() throws SQLException{
		Vector<Integer> salles = new Vector<Integer>();
        while (this.result.next()) {
        	salles.add(Integer.valueOf(this.result.getString(3)));
	    }
        return salles;
    }
	
	public String getTypeEnchere() throws SQLException{
		String type = " (";
		while (this.result.next()) {
        	if(this.result.getString(2) == null) {
        		type = type +  "descendante /";
        	}
        	else {
        		type = type + "montante /";
        	}
        	if(this.result.getString(3) != null) {
        		type = type + "plusieurs enchères /";
        	}
        	else {
        		type = type + "une enchère /";
        	}
        	if(this.result.getString(4) != null) {
        		type = type + "revocable)";
        	}
        	else {
        		type = type + "non revocable)";
        	}
		}
		return type;
		
	}
	
	public String getTypeSalleProduit(int id) throws SQLException{
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
	
	public Vector<String> getProduitSalle(Actions act, Achat achat) throws SQLException{
		Vector<String> salles = new Vector<String>();
        while (this.result.next()) {
        	if (!achat.venteFinie(act.getIdVenteProduit(Integer.valueOf(this.result.getString(1))))) {
        		salles.add(this.result.getString(2) + " (stock = " + this.result.getString(4)+ ")");
        	}
	    }
        return salles;
		
	}

	public Vector<Integer> getIdProduitSalle() throws SQLException{
		Vector<Integer> salles = new Vector<Integer>();
        while (this.result.next()) {
        	salles.add(Integer.valueOf(this.result.getString(1)));
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
		while(resultat.next()) {
			return(resultat.getInt(1));
		}
		return 0;
	}

	public String getStrResult(ResultSet resultat) throws SQLException {
		while(resultat.next()) {
			return(resultat.getString(1));
		}
		return "";
	}


	public Vector<String> affichageCaracteristiquesProduit() throws SQLException {
		Vector<String> caract = new Vector<String>();
		while(this.result.next()) {
			caract.add(this.result.getString(1));
		}
		return caract;
	}


}



