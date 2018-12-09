package Actions;

import connexion.DataBaseAccess;
import requetes.ParamQuery;
import requetes.SimpleQuery;

import java.sql.SQLException;
import java.util.ArrayList;

public class Vente extends Actions {
    public Vente(String mail_utilisateur, DataBaseAccess data) {
        super(mail_utilisateur, data);
    }

    /**
     * Insertion d'une caracteristique produit
     **/
    private void insertIntoCaracteristiques(String caracteristique, int id_produit) throws SQLException {
           new ParamQuery(data, "insert into CARACTERISTIQUES values(?, ?)", caracteristique, id_produit);
    }

    /**
     * Insertion d'un type de vente (recupere une duree en h)
     **/
    public void insertIntoTypeVente(int idVente, int idTypeVente, int prix_depart, int duree) throws SQLException {
        int idTypeEnchere = getIdTypeEnchere(getIdSalleVente(idVente));
        int idProduit = getIdProduit(idVente);
        boolean asc = enchereMont(idTypeEnchere);
        if(!asc) {
            if (getRevocable(idTypeEnchere)) {
                duree = (prix_depart - getPrixRevient(idProduit)) / 60;
            } else {
                duree = prix_depart / 60;
            }
        }

        if (duree == 0) {
            new ParamQuery(data, "insert into TYPE_VENTE values(?, ?, null, sysdate, sysdate + 10/(24*60))", idTypeVente, prix_depart);

        }else{
            new ParamQuery(data, "insert into TYPE_VENTE values(?, ?, sysdate + ?/24, sysdate, sysdate + ?/24)", idTypeVente, prix_depart, duree, duree);
        }
    }

    /**
     * Insertion DANS TABLE vente
     **/
    private void insertIntoVente(int id_vente, int id_type_vente, int id_produit) throws SQLException {
        new ParamQuery(data, "insert into VENTE values(?, ?, ?)",id_vente, id_type_vente, id_produit);
    }

    /**
     * Insertion DANS TABLE CATEGORIE_PRODUIT
     **/
    public void insertIntoCategorieProduit(String nom_categorie_produit, String description) throws SQLException {
            new ParamQuery(data, "insert into CATEGORIE_PRODUIT values(?, ?)",nom_categorie_produit, description);
    }

    /**
     * Retourn l'idVente à utiliser
     * @return int
     * @throws SQLException
     */
    protected int getIdVente() throws SQLException {
        SimpleQuery sreq;
        sreq = new SimpleQuery(data, "select id_vente.nextval from dual");
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * Création d'une nouvelle vente
     */
    public void newVente(ArrayList<String> caracteristiques, int prixDepart, int duree, String nomProduit, int prixRevient, int stock, int idSalleVente) throws SQLException {
        int idProduit = getIdProduit();
        int idTypeVente = getIdTypeVente();
        int idVente = getIdVente();
        insertIntoProduit(idProduit, nomProduit, prixRevient, stock, idSalleVente);
        for(String car : caracteristiques){
            insertIntoCaracteristiques(car, idProduit);
        }
        insertIntoTypeVente(idVente, idTypeVente, prixDepart, duree);
        insertIntoVente(idVente, idTypeVente, idProduit);
        this.commit();

    }

}
