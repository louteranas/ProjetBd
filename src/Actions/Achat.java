package Actions;

import connexion.DataBaseAccess;
import requetes.ParamQuery;
import requetes.SimpleQuery;

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
     * Renvoi true si la date est passée ou que le stock est nul
     * @param idVente
     * @return boolean
     * @throws SQLException
     */

    public boolean venteFinie(int idVente) throws SQLException {
        ParamQuery sq = new ParamQuery(data, "select date_fin_pro - sysdate from TYPE_VENTE t1 join vente t2 on t1.ID_TYPE_VENTE= t2.ID_TYPE_VENTE where id_vente = ?", idVente );
        if (!(sq.getSimpleResult(sq.getResult())<0)){
            return false;
        }
        ParamQuery p = new ParamQuery(data, "select stock from produit where idProduit = ?", getIdProduit(idVente));
        if (p.getSimpleResult(p.getResult()) != 0){
            return false;
        }
        return true;
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
     * Décrémente le stock de l'id produit indiqué de la quantité nb
     * @return ParamQuery
     */
    public ParamQuery decrementeStock(int nb, int idProduit) throws SQLException {
        return new ParamQuery(data, "update produit set stock=(select stock from produit where id_produit = ?)-? where id_produit = ?", idProduit, nb, idProduit);
    }

    /**
     * Met à jour la date de fin dans le cas d'un enchère ascendante sans date de fin fixée
     */
    public ParamQuery updateDate(int idVente) throws SQLException {
        int idTypeVente = getIdTypeVente(idVente);
        return new ParamQuery(data, "update type_vente set date_fin_pro=sysdate + 10/24/60 where id_type_vente = ?", idTypeVente);
    }

    /**
     * Récupération de la date de fin
     */
    private String getDateFin(int idVente) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select date_fin from TYPE_VENTE t1 join vente t2 on t1.ID_TYPE_VENTE= t2.ID_TYPE_VENTE where id_vente = ?", idVente);
        return (sreq.getStrResult(sreq.getResult()));
    }

    /**
     * Recherche une enchere faire par l'utilisateur sur une vente
     */
    private boolean dejaEncheri(int idVente) throws SQLException {
        ParamQuery p = new ParamQuery(data, "select count(*) from AFFECTATION_ENCHERE where email = ? and ID_VENTE = ?", utilisateur, idVente);
        p.affichageResultat();
        if (p.getSimpleResult(p.getResult())==0){
            return false;
        }else{
            return true;
        }

    }

    /**
     * Retourne true si l'utilisateur peut enchérir plusieurs fois
     */
    public boolean plusieuresEncheres(int idVente) throws SQLException {
        int idProduit = getIdProduit(idVente);
        ParamQuery p = new ParamQuery(data, "select PLUSIEURS_ENCHERES from (select * from type_enchere join SALLE_VENTE on type_enchere.ID_TYPE_ENCHERE = SALLE_VENTE.ID_TYPE_ENCHERE ) t1 join produit t2 on t1.id_salle_vente = t2.id_salle_vente where id_produit = ?", idProduit);
        if (p.getStrResult(p.getResult())== null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Vérifie que l'utilisateur peut encore enchérir sur cette vente
     */
    private boolean peutEncoreEncherir(int idVente) throws SQLException {
        if (dejaEncheri(idVente)){
            if (!plusieuresEncheres(idVente)){
                return false;
            }
        }
        return true;
    }

    /**
     * Rajoute un champ dans les tables Enchères et Affectation_enchère pour une enchère ascendante
     *
     * @throws SQLException
     */
    public void newEnchereAsc(int idVente, int prixAchat, int quantite) throws Exception {
        if (venteFinie(idVente)){
            throw new Exception("L'enchere est terminée!");
        }
        if (!peutEncoreEncherir(idVente)){
            throw new Exception("Vous avez déjà enchéri sur cette vente! Dommage...");
        }
        int idEnchere = super.getIdEnchere();
        int prixCourant = prixCourant(idVente);

        if (prixAchat < prixCourant){
            throw new IllegalArgumentException("Le prix doit être supérieur au prix courant!");
        }
        if (quantite>getStock(getIdProduit(idVente))){
            throw new IllegalArgumentException("Il n'y a pas assez de stock!");
        }
        if (getDateFin(idVente) == null){
            updateDate(idVente);
        }
        insertIntoEnchere(idEnchere, prixAchat, quantite);
        insertIntoAffectationEnchere(idEnchere, idVente);
    }




    /**
     * Idem pour enchère descendante
     */
    public void newEnchereDesc(int idVente, int quantite) throws Exception {

        int idProduit = getIdProduit(idVente);
        int id_enchere = getIdEnchere();
        int id_typeEnchere = getIdTypeEnchere(getIdSalleVente(idVente));
        int prixAchat = prixCourant(idVente);

        if (!peutEncoreEncherir(idVente)){
            throw new Exception("Vous avez déjà enchéri sur cette vente! Dommage...");
        }

        if (venteFinie(idVente)){
            throw new Exception("L'enchere est terminée!");
        }
        if (quantite>getStock(getIdProduit(idVente))){
            throw new IllegalArgumentException("Il n'y a pas assez de stock!");
        }
        decrementeStock(quantite, idProduit);
        insertIntoEnchere(id_enchere, prixAchat, quantite);
        insertIntoAffectationEnchere(id_enchere, idVente);
    }



}
