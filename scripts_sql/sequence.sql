
insert into CATEGORIE_PRODUIT values('vélo', 'pour se déplacer');
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
commit;
