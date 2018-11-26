package connexion;

import requetes.ParamQuery;
import requetes.SimpleQuery;

import java.sql.SQLException;


public class Actions {

    private String utilisateur;
    private DataBaseAccess data;

    public Actions(String mail_utilisateur, DataBaseAccess data) {
        utilisateur = mail_utilisateur;
        this.data = data;
    }

    private String getDate() {
        return ("select to_char(sysdate, 'dd/mm/yyyy; HH:MI:SS' from dual");
    }


    /**
     * Exécute la commande pour avoir accès à toutes les salles de vente
     */

    public SimpleQuery affichageSallesDeVente() {

        try {
            return new SimpleQuery(data, "SELECT * from SALLE_VENTE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Affiche tous les produits en vente dans la salle de l'id indiqué
     */
    public ParamQuery produitsSalle(int id_salle) {
        try {
            return (new ParamQuery(data, "select * from produit where id_salle_vente = ?", id_salle));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Affiche tous les produits d'une catégorie donnée
     *
     * @return PramQuery
     */
    public ParamQuery produitsCat(String categorie) throws SQLException {
        try {
            return (new ParamQuery(data, "SELECT * from PRODUIT where nom_categorie_produit = ?", categorie));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Renvoie l'id type enchère selon la salle
     *
     * @return int
     */
    public int getIdTypeEnchere(int id_salle) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select id_type_enchere from salle_vente where id_salle_vente = ?", id_salle);
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * renvoie l'id de la salle dans laquelle se déroule une vente
     *
     * @return int
     */
    public int getIdSalleVente(int id_vente) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select id_salle_vente from produit p, vente v where v.id_vente = ? and v.id_produit = p.id_produit", id_vente);
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * Insertion dans la table Enchere
     */
    public ParamQuery insertIntoEnchere(int id_enchere, int prixAchat, int quantite, int id_type_enchere) {
        try {
            return (new ParamQuery(data, "insert into ENCHERE values(?, ?,sysdate, ?, ?)", id_enchere, prixAchat, quantite, id_type_enchere));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insertion dans la table affectation enchere
     */
    public ParamQuery insertIntoAffectationEnchere(int id_enchere, int id_vente) {
        try {
            return (new ParamQuery(data, "insert into AFFECTATION_ENCHERE values(?, ?, ?)", utilisateur, id_enchere, id_vente));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Compte le nombre de salles de vente
     */
    public SimpleQuery getNbSallesVentes() throws SQLException {
        return (new SimpleQuery(data, "select count(id_salle_vente) from salle_vente"));
    }

    /**
     * Renvoie les ventes concernant un produit
     */
    public ParamQuery ventesProduit(int id_produit) {
        try {
            return (new ParamQuery(data, "select id_vente from produit where id_produit = ?", id_produit));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Retourne l'idEnchere à utiliser
     */
    private int getIdEnchere() throws SQLException {
        SimpleQuery sreq;
        sreq = new SimpleQuery(data, "select id_enchere.nextval from dual");
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * Retourne true si l'enchere est descendante, false si montante
     */
    private boolean enchereDesc(int id_enchere) throws SQLException {
        ParamQuery sq = new ParamQuery(data, "select montante_descendante from type_enchere t, enchere e where id_enchere = ? and e.id_type_enchere = t.id_type_enchere", id_enchere);
        if (sq.getStrResult(sq.getResult()) == null){
            return false;
        }
        return true;
    }

    /**
     * Rajoute un champ dans les tables Enchèeres et affectation enchère pour une enchère ascendante
     *
     * @throws SQLException
     */
    public void newEnchereAcs(int id_vente, int prixAchat, int quantite) throws SQLException {
        int id_enchere = getIdEnchere();
        int id_typeEnchere = getIdTypeEnchere(getIdSalleVente(id_vente));
        insertIntoEnchere(id_enchere, prixAchat, quantite, id_typeEnchere);
        insertIntoAffectationEnchere(id_enchere, id_vente);
    }

    /**
     * Idem pour enchère descendante
     */
    public void newEnchereDesc(int id_vente, int prixAchat, int quantite) throws SQLException {
        int id_enchere = getIdEnchere();
        int id_typeEnchere = getIdTypeEnchere(getIdSalleVente(id_vente));

        insertIntoEnchere(id_enchere, prixAchat, quantite, id_typeEnchere);
        insertIntoAffectationEnchere(id_enchere, id_vente);


    }



    /**
     * Mise en place d'une nouvelle salle de vente (admin)
     */
    public ParamQuery newSalle(String categorie_produit, int typeEnchere) throws SQLException {
        try {
            return (new ParamQuery(data, "insert into SALLE_VENTE values(id_salle_vente.nextval, ?, ?", categorie_produit, typeEnchere));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Insertion d'un nouveau produit
     **/
    public ParamQuery insertIntoProduit(String nom, int prix, int stock, String categorie, int id_salle) {
        try {
            return (new ParamQuery(data, "insert into PRODUIT values(idi_produit.nextval, ?, ?, ?, ?, ?)", nom, prix, stock, categorie, id_salle));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Affichage du type d'enchere à partir d'un id
     */
    public ParamQuery typeEnchere(int id) throws SQLException {
        return (new ParamQuery(data, "select * from TYPE_ENCHERE where id_type_enchere = ?", id));
    }

    /**
     * Renvoie le prix courant pour une enchère donnée (max)
     */
    public int prixCourant(int idVente) throws SQLException {
        int idTypeEnchere = getIdTypeEnchere(getIdSalleVente(idVente));
        ParamQuery sreq;
        if(!enchereDesc(idTypeEnchere)){
            sreq = new ParamQuery(data, "select max(prix_achat) from ENCHERE where id_enchere in (SELECT id_enchere FROM AFFECTATION_ENCHERE where id_vente = ?)", idVente);
        }else{
            sreq = new ParamQuery(data, "select prix_courant from (select prix_depart-(sysdate-DATE_DEBUT)*24*60 as prix_courant, id_vente from TYPE_VENTE t1, VENTE t2 WHERE t1.id_type_vente = t2.id_type_vente) where id_vente = ?", idVente);
        }
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * Renvoie les caractéristiques d'un produit
     */
    public ParamQuery getCaracteristiques(int idProduit) throws SQLException {
        return (new ParamQuery(data, "select caracteristiques from CARACTERISTIQUES where id_produit = ?", idProduit));

    }

}