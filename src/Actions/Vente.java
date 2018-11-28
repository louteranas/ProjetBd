package Actions;

import connexion.DataBaseAccess;
import requetes.ParamQuery;

import java.sql.SQLException;
import java.util.ArrayList;

public class Vente extends Actions {
    public Vente(String mail_utilisateur, DataBaseAccess data) {
        super(mail_utilisateur, data);
    }

    /**
     * Insertion d'une caracteristique produit
     **/
    private ParamQuery insertIntoCaracteristiques(String caracteristique, int id_produit) {
        try {
            return (new ParamQuery(data, "insert into CARACTERISTIQUES values(?, ?)", caracteristique, id_produit));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insertion d'un type de vente (recupere une duree en h)
     **/
    private ParamQuery insertIntoTypeVente(int idTypeVente, int prix_depart, int duree) {
        try {
            return (new ParamQuery(data, "insert into TYPE_VENTE values(?, ?, sysdate + ?/24, sysdate)", idTypeVente, prix_depart, duree));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insertion DANS TABLE vente
     **/
    private ParamQuery insertIntoVente(int id_type_vente, int id_produit) {
        try {
            return (new ParamQuery(data, "insert into VENTE values(id_vente.nextval, ?, ?)",id_type_vente, id_produit));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insertion DANS TABLE CATEGORIE_PRODUIT
     **/
    public ParamQuery insertIntoCategorieProduit(String nom_categorie_produit, String description) {
        try {
            return (new ParamQuery(data, "insert into CATEGORIE_PRODUIT values(?, ?)",nom_categorie_produit, description));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Création d'une nouvelle vente
     */
    public void newVente(ArrayList<String> caracteristiques, int prixDepart, int duree, String nomProduit, int prixRevient, int stock, int idSalleVente) throws SQLException {
        int idProduit = getIdProduit();
        int idTypeVente = getIdTypeVente();
        insertIntoProduit(idProduit, nomProduit, prixRevient, stock, idSalleVente);
        for(String car : caracteristiques){
            insertIntoCaracteristiques(car, idProduit);
        }
        insertIntoTypeVente(idTypeVente, prixDepart, duree);
        insertIntoVente(idTypeVente, idProduit);

    }

}
