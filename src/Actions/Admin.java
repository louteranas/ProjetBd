package Actions;

import connexion.DataBaseAccess;
import requetes.ParamQuery;

import java.sql.SQLException;
import java.util.ArrayList;

public class Admin extends Actions {

    public Admin(String mail_utilisateur, DataBaseAccess data) {
        super(mail_utilisateur, data);
    }

    public ParamQuery vainqueursDesc(int idVente) throws SQLException {
        return new ParamQuery(data, "select email, quantite, enchere.PRIX_ACHAT, enchere.DATE_ENCHERE from enchere join AFFECTATION_ENCHERE on enchere.ID_ENCHERE = AFFECTATION_ENCHERE.ID_ENCHERE where id_vente = ?", idVente);
    }

//    public ArrayList<String> vainquersAsc(int idVente) throws SQLException {
//        int idProduit = getIdProduit(idVente);
//        int stock = getStock(idProduit);
//        while (stock>0){
//        }
//
//
//    }



}
