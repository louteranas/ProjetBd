package connexion;

import connexion.DataBaseAccess;
import requetes.SimpleQuery;
import requetes.StringRequete;

import java.sql.SQLException;
import java.util.Scanner;

public class Actions {
    
    private String utilisateur;
    private StringRequete strreq;
    private DataBaseAccess data;
    
    public Actions(String mail_utilisateur){
        utilisateur = mail_utilisateur;
        strreq = new StringRequete();
    }
    
    public void enchere() throws SQLException {

    }

    public void affichageSallesDeVente(){
        try {
            SimpleQuery sreq = new SimpleQuery(data, strreq.getSalles());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void produitsSalle(int id_salle){
        try {
            SimpleQuery nr =  new SimpleQuery(data, strreq.getProduitsSalle(id_salle));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    
    
    
}
