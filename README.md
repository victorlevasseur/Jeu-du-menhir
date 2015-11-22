# Jeu-du-menhir
Le jeu du menhir. Un jeu de société informatisé. Projet de LO02.

## Tests unitaires
Les résultats en direct des tests unitaires réalisé à distance par un service de Continuous Integration (Travis CI) sont disponibles ici : https://travis-ci.org/victorlevasseur/Jeu-du-menhir

**Branche ```master``` :** [![Build Status](https://travis-ci.org/victorlevasseur/Jeu-du-menhir.svg?branch=master)](https://travis-ci.org/victorlevasseur/Jeu-du-menhir)

**Branche ```develop``` :** [![Build Status](https://travis-ci.org/victorlevasseur/Jeu-du-menhir.svg?branch=develop)](https://travis-ci.org/victorlevasseur/Jeu-du-menhir)

### Insérer un nouveau test unitaire
 - Créer un nouveau test unitaire en utilisant New > JUnit Test Case (bien choisir JUnit **4** dans la fenêtre suivante et choisir la classe que l'on veut tester dans l'assistant). Bien le placer dans le package ```fr.utt.girardguittard.levasseur.menhir.test```
 - Réaliser le test (composé de méthodes qui sont précédées par l'annotation ```@Test```
 - Ajouter le test dans le fichier ```build.xml``` (pour que Travis CI l'exécute) de cette manière : dans la balise ```<target name="test">...</target>```, copier l'ensemble d'une balise ```<junit ...>...</junit>``` et la coller juste en dessous en remplacant la valeur de l'attribut ```name``` de la sous-balise ```<test .../>``` par le nom complet (avec package donc) de la classe de test
 - Commit le nouveau fichier build.xml ainsi que le test
 - Travis CI devrait maintenant pouvoir exécuter le test ! :)

**Note :** A l'intérieur du test, la méthode ```assertEquals(a, b)``` permet de vérifier que deux objets/valeurs sont égaux (si ce n'est pas le cas, le test échoue).
