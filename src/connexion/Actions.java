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

    /**
     * Affiche toutes les salles de vente
     */
    public void affichageSallesDeVente(){
        try {
            SimpleQuery sreq = new SimpleQuery(data, strreq.getSalles());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche tous les produits en vente dans la salle de l'id indiqué
     * @param id_salle
     */
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
        String a = sreq.getSimpleResult(result);
        System.out.println("a" +a);
        try {
            sreq.affichageResultat(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
}
