package connexion;

import requetes.ParamQuery;
import requetes.SimpleQuery;

import java.sql.SQLException;


public class Actions {
    
    private String utilisateur;
    private DataBaseAccess data;
    
    public Actions(String mail_utilisateur, DataBaseAccess data){
        utilisateur = mail_utilisateur;
        this.data = data;
    }

    private String getDate(){
        return("select to_char(sysdate, 'dd/mm/yyyy; HH:MI:SS' from dual");
    }


    /**
     * Exécute la commande pour avoir accès à toutes les salles de vente
     */

    public SimpleQuery affichageSallesDeVente(){

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
    public ParamQuery produitsSalle(int id_salle){
        try {
            return(new ParamQuery(data, "select * from produit where id_salle_vente = ?", id_salle));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Affiche tous les produits d'une catégorie donnée
     * @return PramQuery
     */
    public ParamQuery produitsCat(String categorie) throws SQLException{
        try {
            return(new ParamQuery(data, "SELECT * from PRODUIT where nom_categorie_produit = ?", categorie));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Renvoie l'id type enchère selon la salle
     * @return int
     */
    public int getIdTypeEnchere(int id_salle) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data,"select id_type_enchere from salle_vente where id_salle_vente = ?", id_salle );
        return(sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * renvoie l'id de la salle dans laquelle se déroule une vente
     * @return int
     */
    public int getIdSalleVente(int id_vente) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data,"select id_salle_vente from produit p, vente v where v.id_vente = ? and v.id_produit = p.id_produit", id_vente );
        return(sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * Insertion dans la table Enchere
     */
    public ParamQuery insertIntoEnchere(int prixAchat, int quantite, int id_type_enchere){
        try {
            return(new ParamQuery(data, "insert into ENCHERE values(id_enchere.nextval, ?," + getDate()+"?, ?, ?", prixAchat, quantite, id_type_enchere));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insertion dans la table affectation enchere
     */
    public ParamQuery insertIntoAffectationEnchere(int id_vente){
        try {
            return(new ParamQuery(data, "insert into AFFECTATION_ENCHERE values(id_enchere.nextval, ?, ?)", utilisateur, id_vente));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Compte le nombre de salles de vente
     */
    public SimpleQuery getNbSallesVentes() throws SQLException {
        return(new SimpleQuery(data, "select count(id_salle_vente) from salle_vente"));
    }

    /**
     * Renvoie les ventes concernant un produit
     */
    public ParamQuery ventesProduit(int id_produit){
        try {
            return(new ParamQuery(data, "select id_vente from produit where id_produit = ?", id_produit));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Rajoute un champ dans les tables Enchèeres et affectation enchère
     * @throws SQLException
     */
    public void newEnchere(int id_vente, int prixAchat, int quantite) throws SQLException {
        insertIntoAffectationEnchere(id_vente);
        int id_typeEnchere = getIdTypeEnchere(getIdSalleVente(id_vente));
        insertIntoEnchere(prixAchat, quantite, id_typeEnchere);

    }

    /**
     * Mise en place d'une nouvelle salle de vente (admin)
     */
    public ParamQuery newSalle(String categorie_produit, int typeEnchere) throws SQLException {
        try {
            return(new ParamQuery(data, "insert into SALLE_VENTE values(id_salle_vente.nextval, ?, ?", categorie_produit, typeEnchere));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Insertion d'un nouveau produit
     **/
    public ParamQuery insertIntoProduit(String nom, int prix, int stock, String categorie, int id_salle){
        try {
            return(new ParamQuery(data, "insert into PRODUIT values(idi_produit.nextval, ?, ?, ?, ?, ?)", nom, prix, stock, categorie, id_salle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
