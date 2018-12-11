
insert into CATEGORIE_PRODUIT values('velo', 'pour se déplacer');
--insert into CATEGORIE_PRODUIT values('electroménager', 'lave-vaisselle, micro-ondes, aspirateur...');
--insert into CATEGORIE_PRODUIT values('ordinateur', 'avec ecran et clavier');
--insert into CATEGORIE_PRODUIT values('jouets', 'pour Noel');

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
insert into SALLE_VENTE values(1, 'velo', 1);


--Ajout d'une vente
insert into PRODUIT values(1, 'velo decathlon', 150, 10, 1);
insert into TYPE_VENTE values(1, 100, sysdate+15/(24*60), sysdate, sysdate+15/(24*60));
insert into VENTE values(1, 1, 1);
commit;
