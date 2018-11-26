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
            return(new ParamQuery(data, "select * from produit where id_salle_vente = ?)", id_salle));
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
    public ParamQuery insertIntoAfectationEnchere(String utilisateur, int id_enchere, int id_vente){
        try {
            return(new ParamQuery(data, "insert into AFFECTATION_ENCHERE values(?, ?, ?)", utilisateur, id_enchere, id_vente);
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
     * Rajoute un champ dans les tables Enchèeres et affectation enchère
     * @throws SQLException
     */
    public void newEnchere() throws SQLException {


    }


}
