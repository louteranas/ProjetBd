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
     * Exécute la commande pour avoir accès à toutes les salles de vente
     */

    public SimpleQuery affichageSallesDeVente(){

        try {
            SimpleQuery sreq = new SimpleQuery(data, strreq.getSalles());
            return sreq;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Affiche tous les produits en vente dans la salle de l'id indiqué
     * @param id_salle
     */
    public SimpleQuery produitsSalle(int id_salle){
        try {
            SimpleQuery nr =  new SimpleQuery(data, strreq.getProduitsSalle(id_salle));
            return nr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getIdTypeEnchere(int id_salle) throws SQLException {
        SimpleQuery sreq = null;
        sreq = new SimpleQuery(data, strreq.getTypeEnchere(1));
        return(sreq.getSimpleResult(sreq.getResult()));
    }




    
    
    
}
