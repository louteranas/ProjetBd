package requetes;

import java.sql.SQLException;

public class StringRequete {

    public String getCategories () {
        return ("SELECT * from CATEGORIE_PRODUIT");
    }

    public String getProduitsCat(String categorie){
        return ("SELECT * from PRODUIT where nom_categorie_produit = '" + categorie + "'");
    }

    public String getSalles(){
        return("SELECT * from SALLE_VENTE");
    }

    public String getProduitsSalle(int id_salle){
        return("SELECT * from PRODUIT where id_salle_vente = '" + id_salle + "'");
    }

    public String nvEnchere(int prixAchat, int quantite, int idTypeEnchere){
        String date = getDate();
        return ("INSERT INTO ENCHERE values(id_enchere.nextval, " + prixAchat +", "+getDate()+", "+ quantite+", " + idTypeEnchere + ")");
    }

    public String getDate(){
        return("select to_char(sysdate, 'dd/mm/yyyy; HH:MI:SS' from dual");
    }

    public String getTypeEnchere(int salle){
        return ("select id_type_enchere from salle_vente where id_salle_vente = " + salle );
    }

}
