--Script permettant de peupler la base données

--Ajout de catégories de produits
insert into CATEGORIE_PRODUIT values('vélo', 'pour se déplacer');
insert into CATEGORIE_PRODUIT values('électroménager', 'lave-vaisselle, micro-ondes, aspirateur...');
insert into CATEGORIE_PRODUIT values('ordinateur', 'avec écran et clavier');
insert into CATEGORIE_PRODUIT values('jouets', 'pour Noël');

--Ajout de tous les types d'enchères
insert into TYPE_ENCHERE(id_type_enchere.nextval, 'montante', 'oui', 'revocable');
insert into TYPE_ENCHERE(id_type_enchere.nextval, 'montante', 'oui', NULL);
insert into TYPE_ENCHERE(id_type_enchere.nextval, 'montante', NULL, 'revocable');
insert into TYPE_ENCHERE(id_type_enchere.nextval, 'montante', NULL, NULL);

insert into TYPE_ENCHERE(id_type_enchere.nextval, NULL, 'oui', 'revocable' );
insert into TYPE_ENCHERE(id_type_enchere.nextval, NULL, 'oui', NULL);
insert into TYPE_ENCHERE(id_type_enchere.nextval, NULL, NULL, 'revocable');
insert into TYPE_ENCHERE(id_type_enchere.nextval, NULL, NULL, NULL);


--Ajout de salles de ventes
insert into SALLE_VENTE values(id_salle_vente.nextval, 'vélo', 1);
insert into SALLE_VENTE values (id_salle_vente.nextval, 'électroménager', 2);
insert into SALLE_VENTE values (id_salle_vente.nextval, 'ordinateur', 3);
insert into SALLE_VENTE values (id_salle_vente.nextval, 'jouets', 4);

--Ajout de produits
--Insertion dans la table PRODUIT
--catégorie vélo
insert into PRODUIT values(id_produit.nextval, 'vélo décathlon', 150, 10, 1);
insert into PRODUIT values(id_produit.nextval, 'VTT', 250, 3, 1);
insert into PRODUIT values(id_produit.nextval, 'tricycle', 50, 5, 1);
--catégorie électroménager
insert into PRODUIT values(id_produit.nextval, 'grille-pain', 50, 4, 2);
insert into PRODUIT values(id_produit.nextval, 'aspirateur', 125, 12, 2);
insert into PRODUIT values(id_produit.nextval, 'micro-ondes', 145, 50, 2);
--catégorie ordinateur
insert into PRODUIT values(id_produit.nextval, 'asus', 1000, 20, 3);
insert into PRODUIT values(id_produit.nextval, 'dell', 1500, 30, 3);
insert into PRODUIT values(id_produit.nextval, 'hp', 800, 25, 3);
--catégorie jouets
insert into PRODUIT values(id_produit.nextval, 'puzzle', 20, 35, 4);
insert into PRODUIT values(id_produit.nextval, 'LEGO', 100, 10, 4);
insert into PRODUIT values(id_produit.nextval, 'peluche', 30, 5, 1);

--Ajout des types de ventes
insert into TYPE_VENTE values(id_type_vente.nextval, 100, null, sysdate+5, sysdate + 10/(24*60));
insert into TYPE_VENTE values(id_type_vente.nextval, 100, sysdate + 6, sysdate+5, sysdate + 6;


--Insertion dans la table vente (une vente par produit)
insert into VENTE values(id_vente.nextval, 1, 1)
insert into VENTE values(id_vente.nextval, 2, 2)
insert into VENTE values(id_vente.nextval, 1, 3)
insert into VENTE values(id_vente.nextval, 2, 4)
insert into VENTE values(id_vente.nextval, 1, 5)
insert into VENTE values(id_vente.nextval, 2, 6)
insert into VENTE values(id_vente.nextval, 1, 7)
insert into VENTE values(id_vente.nextval, 2, 8)
insert into VENTE values(id_vente.nextval, 1, 9)
insert into VENTE values(id_vente.nextval, 2, 10)
insert into VENTE values(id_vente.nextval, 1, 11)
insert into VENTE values(id_vente.nextval, 2, 12)
insert into VENTE values(id_vente.nextval, 1, 13)
