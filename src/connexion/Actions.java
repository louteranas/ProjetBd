package connexion;

import connexion.DataBaseAccess;
import requetes.SimpleQuery;
import requetes.StringRequete;

import java.sql.SQLException;
import java.util.Scanner;

public class Actions {
    
    private String utilisateur;
    private StringRequete strreq;
    
    public Actions(String mail_utilisateur){
        utilisateur = mail_utilisateur;
        strreq = new StringRequete();
    }
    
    public void enchere(DataBaseAccess data) throws SQLException {
        SimpleQuery sreq = new SimpleQuery(data, strreq.getSalles());
        System.out.println("Sélectionnez votre salle de vente");
        Scanner scan = new Scanner(System.in);
        String salle = scan.nextLine();
        SimpleQuery nr =  new SimpleQuery(data, strreq.getProduitsSalle(salle));
    }




    
    
    
}
