
--Script permettant de peupler la base données

--Ajout de catégories de produits

--insert into CATEGORIE_PRODUIT values('vélo', 'pour se déplacer');
--insert into CATEGORIE_PRODUIT values('électroménager', 'lave-vaisselle, micro-ondes, aspirateur...');
--insert into CATEGORIE_PRODUIT values('ordinateur', 'avec écran et clavier');
--insert into CATEGORIE_PRODUIT values('jouets', 'pour Noël');

  --Ajout de tous les types d'enchères
  insert into TYPE_ENCHERE values(1, 'montante', 'oui', 'revocable');
  insert into TYPE_ENCHERE values(2, 'montante', 'oui', NULL);
  insert into TYPE_ENCHERE values(3, 'montante', NULL, 'revocable');
  insert into TYPE_ENCHERE values(4, 'montante', NULL, NULL);

  insert into TYPE_ENCHERE values(5, NULL, 'oui', 'revocable' );
  insert into TYPE_ENCHERE values(6, NULL, 'oui', NULL);
  insert into TYPE_ENCHERE values(7, NULL, NULL, 'revocable');
  insert into TYPE_ENCHERE values(8, NULL, NULL, NULL);


--Ajout de salles de ventes
insert into SALLE_VENTE values(1, 'vélo', 1);
insert into SALLE_VENTE values (2, 'électroménager', 2);
insert into SALLE_VENTE values (3, 'ordinateur', 3);
insert into SALLE_VENTE values (4, 'jouets', 4);



--catégorie vélo
insert into PRODUIT values(1, 'vélo décathlon', 150, 10, 1);
insert into PRODUIT values(2, 'VTT', 250, 3, 1);
insert into PRODUIT values(3, 'tricycle', 50, 5, 1);
--catégorie électroménager
insert into PRODUIT values(4, 'grille-pain', 50, 4, 2);
insert into PRODUIT values(5, 'aspirateur', 125, 12, 2);
insert into PRODUIT values(6, 'micro-ondes', 145, 50, 2);
--catégorie ordinateur
insert into PRODUIT values(7, 'asus', 1000, 20, 3);
insert into PRODUIT values(8, 'dell', 1500, 30, 3);
insert into PRODUIT values(9, 'hp', 800, 25, 3);
--catégorie jouets
insert into PRODUIT values(10, 'puzzle', 20, 35, 4);
insert into PRODUIT values(11, 'LEGO', 100, 10, 4);
insert into PRODUIT values(12, 'peluche', 30, 5, 1);


--Ajout des types de ventes
insert into TYPE_VENTE values(1, 100, null, sysdate+5, sysdate + 10/(24*60));
insert into TYPE_VENTE values(2, 100, sysdate + 6, sysdate+5, sysdate + 6);


--Insertion dans la table vente (une vente par produit)
insert into VENTE values(1, 1, 1);
insert into VENTE values(2, 2, 2);
insert into VENTE values(3, 1, 3);
insert into VENTE values(4, 2, 4);
insert into VENTE values(5, 1, 5);
insert into VENTE values(6, 2, 6);
insert into VENTE values(7, 1, 7);
insert into VENTE values(8, 2, 8);
insert into VENTE values(9, 1, 9);
insert into VENTE values(10, 2, 10);
insert into VENTE values(11, 1, 11);
insert into VENTE values(12, 2, 12);
