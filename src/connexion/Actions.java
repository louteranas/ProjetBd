package connexion;

import connexion.DataBaseAccess;
import requetes.SimpleQuery;
import requetes.StringRequete;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Actions {
    
    private String utilisateur;
    private StringRequete strreq;
    private DataBaseAccess data;
    
    public Actions(String mail_utilisateur, DataBaseAccess data){
        utilisateur = mail_utilisateur;
        strreq = new StringRequete();
        this.data = data;
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

    public void getIdTypeEnchere(int id_salle){
        SimpleQuery sreq = null;
        try {
            sreq = new SimpleQuery(data, strreq.getTypeEnchere(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet result = sreq.getResult();
        try {
            sreq.affichageResultat(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
}
