package Tests;

import connexion.DataBaseAccess;
import requetes.*;

public class test {
	public static void main(String[] args) {
		DataBaseAccess data = new DataBaseAccess();	
		String requete = "select * from emp";
		SimpleQuery req = new SimpleQuery(data, requete);
	}
}
