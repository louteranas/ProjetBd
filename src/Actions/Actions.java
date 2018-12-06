package Actions;

import connexion.DataBaseAccess;
import requetes.ParamQuery;
import requetes.SimpleQuery;
import java.sql.SQLException;
import java.util.Vector;


public class Actions {

    protected String utilisateur;
    protected DataBaseAccess data;

    public Actions(String mail_utilisateur, DataBaseAccess data) {
        utilisateur = mail_utilisateur;
        this.data = data;
    }

    public String getDate() {
        return ("select to_char(sysdate, 'dd/mm/yyyy; HH24:MI:SS' from dual");
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
    protected int getIdEnchere() throws SQLException {
        SimpleQuery sreq;
        sreq = new SimpleQuery(data, "select id_enchere.nextval from dual");
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * Retourne l'idProduit à utiliser
     */
    protected int getIdProduit() throws SQLException {
        SimpleQuery sreq;
        sreq = new SimpleQuery(data, "select id_produit.nextval from dual");
        return (sreq.getSimpleResult(sreq.getResult()));
    }


    /**
     * Retourne l'idTypeVente à utiliser
     */
    protected int getIdTypeVente() throws SQLException {
        SimpleQuery sreq;
        sreq = new SimpleQuery(data, "select id_type_vente.nextval from dual");
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * Retourne l'id_vente à partir de l'id_enchere
     */
    protected int getIdVente(int idEnchere) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select id_vente from AFFECTATION_ENCHERE where id_enchere = ?", idEnchere);
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * Retourne true si l'enchere est montante, false si descendante
     */
    public boolean enchereMont(int idTypeEnchere) throws SQLException {
        ParamQuery sq = new ParamQuery(data, "select montante_descendante from type_enchere  where id_type_enchere = ?", idTypeEnchere);
        if (sq.getStrResult(sq.getResult()) == null){
            return false;
        }
        return true;
    }


    /**
     * Mise en place d'une nouvelle salle de vente (admin)
     */
    public ParamQuery newSalle(String categorie_produit, int typeEnchere) throws SQLException {
        try {
            return (new ParamQuery(data, "insert into SALLE_VENTE values(id_salle_vente.nextval, ?, ?)", categorie_produit, typeEnchere));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    
    /**
     * Mise en place d'une nouvelle categorie de produit (admin)
     */
    public void newCat(String categorie_produit) throws SQLException {
        try {
            new ParamQuery(data, "insert into CATEGORIE_PRODUIT values(?, ?)", categorie_produit, "nouvelle categorie");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Insertion d'un nouveau produit
     **/
    public ParamQuery insertIntoProduit( int idProduit, String nom, int prix, int stock, int id_salle) {
        if (stock <= 0) {
            throw new IllegalArgumentException("Le stock doit être strictement positif!");

        } else {

            try {
                return (new ParamQuery(data, "insert into PRODUIT values(?, ?, ?, ?, ?)", idProduit, nom, prix, stock, id_salle));
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
     * Renvoie l'idProduit à partir de l'idVente
     */
    public int getIdProduit(int idVente) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select id_produit from vente where id_vente = ?", idVente);
        return (sreq.getSimpleResult(sreq.getResult()));
    }
    /**
     * Renvoie l'idVente à partir de l'idProduit
     */
    public int getIdVenteProduit(int idProduit) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select id_vente from vente where id_produit = ?", idProduit);
        return (sreq.getSimpleResult(sreq.getResult()));
    }
    /**
     * Renvoie le prix courant pour une enchère donnée (max)
     */
    public int prixCourant(int idVente) throws SQLException {
        int idTypeEnchere = getIdTypeEnchere(getIdSalleVente(idVente));
        ParamQuery sreq;
        if(enchereMont(idTypeEnchere)){
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
        return (new ParamQuery(data, "select * from CARACTERISTIQUES where id_produit = ?", idProduit));

    }

    /**
     * Renvoie le stock disponible d'un produit
     *
     * @return int
     */
    public int getStock(int id_produit) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select stock from produit where id_produit = ?", id_produit);
        return (sreq.getSimpleResult(sreq.getResult()));
    }

    /**
     * Retourne true si la vente est révocable
     */
    public boolean getRevocable(int idTypeEnchere) throws SQLException {
        ParamQuery sq = new ParamQuery(data, "select revocable from type_enchere  where id_type_enchere = ?", idTypeEnchere);
        if (sq.getStrResult(sq.getResult()) == null){
            return false;
        }
        return true;
    }

    /**
     * Retourne le prix de revient à partir de l'idProduit
     */
    public int getPrixRevient(int idProduit) throws SQLException {
        ParamQuery sreq;
        sreq = new ParamQuery(data, "select prix_revient from produit where id_produit = ?", idProduit);
        return (sreq.getSimpleResult(sreq.getResult()));

    }

    /**
     * Renfvoie l'id type vente à partir de l'id vente
     */
    public int getIdTypeVente(int idVente) throws SQLException {
        ParamQuery req;
        req = new ParamQuery(data, "select id_type_vente from vente where id_vente=?", idVente);
        return (req.getSimpleResult(req.getResult()));

    }





    /**
     * Renvoie la requete Set autocommit of
     */

    public SimpleQuery setAutocommitOff() throws SQLException {
        return (new SimpleQuery(data, "set autocommit off"));
    }

    /**
     * Renvoie la requete qui permet de faire un savepoint
     */
    public ParamQuery savePoint(String nom) throws SQLException {
        return(new ParamQuery(data, "savepoint ?", nom));
    }

    /**
     * Renvoie la requête Rollback to savepoint souhaité
     */
    public ParamQuery rollback(String savepoint) throws SQLException {
        return new ParamQuery(data, "rollback to ?", savepoint);
    }

	public void getDataEnchere(Vector<Integer> idEnchere, Vector<String> charactEnchere){
		// TODO Auto-generated method stub
		try {
			SimpleQuery req = new SimpleQuery(data, "select * from type_enchere");
			while (req.getResult().next()) {
				idEnchere.add(req.getResult().getInt(1));
				String type = "(";
	        	if(req.getResult().getString(2) == null) {
	        		type = type +  "descendante /";
	        	}
	        	else {
	        		type = type + "montante /";
	        	}
	        	if(req.getResult().getString(3) != null) {
	        		type = type + "plusieurs enchères /";
	        	}
	        	else {
	        		type = type + "une enchère /";
	        	}
	        	if(req.getResult().getString(4) != null) {
	        		type = type + "revocable)";
	        	}
	        	else {
	        		type = type + "non revocable)";
	        	}
	        	charactEnchere.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}