package Actions;

import connexion.DataBaseAccess;
import requetes.ParamQuery;

import java.sql.SQLException;

public class Achat extends Actions {


    public Achat(String mail_utilisateur, DataBaseAccess data) {
        super(mail_utilisateur, data);
    }

    /**
     * Insertion dans la table Enchere
     */
    private ParamQuery insertIntoEnchere(int id_enchere, int prixAchat, int quantite) {
        try {
            return (new ParamQuery(super.data, "insert into ENCHERE values(?, ?,sysdate, ?)", id_enchere, prixAchat, quantite));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Insertion dans la table affectation enchere
     */
    private ParamQuery insertIntoAffectationEnchere(int id_enchere, int id_vente) {
        try {
            return (new ParamQuery(data, "insert into AFFECTATION_ENCHERE values(?, ?, ?)", utilisateur, id_enchere, id_vente));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Rajoute un champ dans les tables Enchères et affectation enchère pour une enchère ascendante
     *
     * @throws SQLException
     */
    public void newEnchereAsc(int idVente, int prixAchat, int quantite) throws SQLException {
        int idEnchere = super.getIdEnchere();
        int prixCourant = prixCourant(idVente);
        if (prixAchat < prixCourant){
            throw new IllegalArgumentException("Le prix doit être supérieur au prix courant!");
        }
        if (quantite>getStock(getIdProduit(idVente))){
            throw new IllegalArgumentException("Il n'y a pas assez de stock!");
        }
        insertIntoEnchere(idEnchere, prixAchat, quantite);
        insertIntoAffectationEnchere(idEnchere, idVente);
    }


    /**
     * Idem pour enchère descendante
     */
    public void newEnchereDesc(int id_vente, int quantite) throws SQLException {
        int id_enchere = getIdEnchere();
        int id_typeEnchere = getIdTypeEnchere(getIdSalleVente(id_vente));
        int prixAchat = prixCourant(id_vente);
        insertIntoEnchere(id_enchere, prixAchat, quantite);
        insertIntoAffectationEnchere(id_enchere, id_vente);
    }



}
