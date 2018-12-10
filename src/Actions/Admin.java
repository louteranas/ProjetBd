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

    public ArrayList<String> vainqueursAsc(int idVente) throws SQLException {
        int idProduit = getIdProduit(idVente);
        int stockCourant = getStock(idProduit);
        ArrayList<String> listeVainqueurs= new ArrayList<String>();
        int numVainqueurs = 1;
        int nbEnchere = getNbEnchere(idVente);
        ParamQuery req =new ParamQuery(data, "select * from (select * from (select * from (select email, to_char(date_enchere, 'dd/mm/yyy hh24:mi:ss'), prix_achat , quantite from ENCHERE join affectation_enchere on ENCHERE.ID_ENCHERE = affectation_enchere.ID_ENCHERE where id_vente = ? order by prix_achat desc) where rownum <=1) order by PRIX_ACHAT asc ) where rownum =1", idVente);
        req.getLigneVainqueur(listeVainqueurs);
        stockCourant = stockCourant - getQuantite(idVente, numVainqueurs);
        while ( (stockCourant>0) && (numVainqueurs < nbEnchere) ){
            numVainqueurs += 1;
            //cas ou ce vainqueur a toute la quantite voulu
            if(getQuantite(idVente, numVainqueurs) < stockCourant){
                stockCourant = stockCourant - getQuantite(idVente, numVainqueurs);
                //System.out.println(stockCourant);
                ParamQuery nreq =new ParamQuery(data, "select * from (select * from (select * from (select email, to_char(date_enchere, 'dd/mm/yyy hh24:mi:ss'), prix_achat , quantite from ENCHERE join affectation_enchere on ENCHERE.ID_ENCHERE = affectation_enchere.ID_ENCHERE where id_vente = ? order by prix_achat desc) where rownum <=?) order by PRIX_ACHAT asc ) where rownum =1", idVente, numVainqueurs);
                nreq.getLigneVainqueur(listeVainqueurs) ;
            }//cas ou ce vainqueur recupere que le reste du stock
            else{
                ParamQuery nreq =new ParamQuery(data, "select * from (select * from (select * from (select email, to_char(date_enchere, 'dd/mm/yyy hh24:mi:ss'), prix_achat from ENCHERE join affectation_enchere on ENCHERE.ID_ENCHERE = affectation_enchere.ID_ENCHERE where id_vente = ? order by prix_achat desc)where rownum <=?) order by PRIX_ACHAT asc ) where rownum =1", idVente, numVainqueurs);
                nreq.getLigneSemiVainqueur(stockCourant, listeVainqueurs) ;
                stockCourant = 0;
            }

        }
        return  listeVainqueurs;

    }

    /**
     * Renvoie la quantité à partir de l'idVente et la place de l'enchere (1=la derniere enchere passée)
     */
    public int getQuantite(int idVente, int i) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select quantite from (select * from (select * from (select email, quantite, to_char(date_enchere, 'dd/mm/yyy hh24:mi:ss'), prix_achat from ENCHERE join affectation_enchere on ENCHERE.ID_ENCHERE = affectation_enchere.ID_ENCHERE where id_vente = ? order by prix_achat desc) where rownum <=?) order by PRIX_ACHAT asc ) where rownum =1", idVente, i);
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    public int getNbEnchere(int idVente) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select count(*) from ENCHERE join affectation_enchere on ENCHERE.ID_ENCHERE = affectation_enchere.ID_ENCHERE where id_vente = ? ", idVente);
        return (sreq.getSimpleResult(sreq.getResult()));
    }



}
